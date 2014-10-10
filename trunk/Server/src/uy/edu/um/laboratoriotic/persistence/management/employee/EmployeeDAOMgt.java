package uy.edu.um.laboratoriotic.persistence.management.employee;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;

/**
 * This is the persistence interface
 * @author sblanco1
 *
 */
public interface EmployeeDAOMgt {

	/**
	 * This method adds an employee to the data base
	 * @param oEmployee
	 */
	public void addEmployee(Employee oEmployee);
	
	/**
	 * This method removes an employee from the data base
	 * @param oEmployeeID
	 */
	public void removeEmployee(int oEmployeeID);
	
	/**
	 * This method searches an employee in the data base
	 * @param oEmployeeID
	 * @return
	 */
	public Employee searchEmployee(int oEmployeeID);
	
	/**
	 * This method return a list with all the employees of the data base
	 * @return
	 */
	public ArrayList<Employee> getEmployees();
	
	/**
	 * This method creates the data base tables
	 */
	public void createTable();
	
}
