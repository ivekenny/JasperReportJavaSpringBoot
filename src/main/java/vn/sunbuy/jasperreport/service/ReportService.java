package vn.sunbuy.jasperreport.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import vn.sunbuy.jasperreport.entity.Employee;
import vn.sunbuy.jasperreport.repository.EmployeeRepository;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ReportService {

	@Autowired
	EmployeeRepository repository;
	
	public String exportReport(String reportFormat) {
		List<Employee> employees = repository.findAll();
		// we will map objects in employees to file jasperreport employees.jrxml
		
		// Load file and compile it
		try {
			
			File file = ResourceUtils.getFile("classpath:employees.jrxml");
			//File file = new ClassPathResource("countries.xml").getFile();
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("createdBy", "vienvt");
			JasperPrint jasperSprint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
			
			String projectPath = System.getProperty("user.dir");
			
			if(reportFormat.equalsIgnoreCase("html")) {
				JasperExportManager.exportReportToHtmlFile(jasperSprint, projectPath + "employees.html");
			}
			
			if(reportFormat.equalsIgnoreCase("pdf")) {
				JasperExportManager.exportReportToPdfFile(jasperSprint, projectPath + "employees.pdf");
			}
			
			return "report generated in path: " + projectPath;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "an exception occured: " + e.getMessage();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "an exception occured: " + e.getMessage();
		}		
	}
}
