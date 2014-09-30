package uy.edu.um.laboratoriotic.persistance;

import uy.edu.um.laboratoriotic.business.Employee;

/**
 * This is the persistance interface
 * @author sblanco1
 *
 */
public interface EmployeeDAOMgt {

	public void addEmployee(Employee oEmployee);
	
	public void createTable();
	
}
