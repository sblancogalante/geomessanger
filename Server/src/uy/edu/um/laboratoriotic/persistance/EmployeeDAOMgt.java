package uy.edu.um.laboratoriotic.persistance;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.Employee;

/**
 * This is the persistance interface
 * @author sblanco1
 *
 */
public interface EmployeeDAOMgt {

	public void addEmployee(Employee oEmployee);
	
	public ArrayList<Employee> getEmployees();
	
	public void createTable();
	
}
