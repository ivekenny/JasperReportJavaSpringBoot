package vn.sunbuy.jasperreport.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
@Table(name="EMPLOYEE")
public class Employee {
	@Id
	private int id;
	private String name;
	private String designation;
	private double salary;
	private Date doj;
}
