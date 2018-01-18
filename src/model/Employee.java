package model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

/**
 * Employee Object class used to copy the details from ResultSet to this Object
 * & use toString method to print the Employee Info
 * 
 * @author Kranthi Kumar
 *
 */

/**
 * @Data annotation is lombok dependency which will make our life's easier No
 *       need to create setters and Getters, Just annotations are enough
 * 
 *       (Refer from): https://projectlombok.org/features/all about the features
 */
@Getter
@Setter
public class Employee {

	private Integer employeeId;
	private String employeeName;
	private String employeeRole;
	private BigDecimal employeeSalary;
	private String employeeCompany;

	public Employee() {
		// Default Constructor
	}

	/**
	 * Prints the Employee Object in below format
	 */
	@Override
	public String toString() {
		return "Employee [" + this.employeeName + "] with employeeId [" + this.employeeId + "] having role ["
				+ this.employeeRole + "] working for company [" + this.employeeCompany + "] is drawing a salary of ["
				+ this.employeeSalary + "]";
	}

	/**
	 * Copy constructor to copy data from resultSet to Employee Object
	 * 
	 * @throws SQLException [if any type conversion error]
	 */
	public Employee(ResultSet resultSet) throws SQLException {
		this.employeeId = resultSet.getInt(1);
		this.employeeName = resultSet.getString(2);
		this.employeeRole = resultSet.getString(3);
		this.employeeCompany = resultSet.getString(4);
		this.employeeSalary = resultSet.getBigDecimal(5);
	}
}
