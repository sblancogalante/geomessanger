package uy.edu.um.laboratoriotic.persistance.management.employee;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This is the persistance interface
 * @author sblanco1
 *
 */
public interface EmployeeDAOMgt {

	public void addEmployee(Employee oEmployee);
	
	public void removeEmployee(int oEmployeeID);
	
	public Employee searchEmployee(int oEmployeeID);
	
	public ArrayList<Employee> getEmployees();
	
	public void createTable();
	
}
