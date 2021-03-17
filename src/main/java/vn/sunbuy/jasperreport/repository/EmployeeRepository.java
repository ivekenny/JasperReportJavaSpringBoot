package vn.sunbuy.jasperreport.repository;

import org.springframework.stereotype.Repository;

import vn.sunbuy.jasperreport.entity.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	List<Employee> findAll();
}
