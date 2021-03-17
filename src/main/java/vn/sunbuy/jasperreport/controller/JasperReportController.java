package vn.sunbuy.jasperreport.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vn.sunbuy.jasperreport.entity.Employee;
import vn.sunbuy.jasperreport.repository.EmployeeRepository;
import vn.sunbuy.jasperreport.service.ReportService;


@RestController
public class JasperReportController {
	@Autowired 
	EmployeeRepository repository;
	
	@Autowired
	ReportService reportService;
	

	@GetMapping("/getEmployees")
	public List<Employee> getEmployees() {
		return repository.findAll();
	}
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) {
		return reportService.exportReport(format);
	}
}
