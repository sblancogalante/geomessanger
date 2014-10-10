package uy.edu.um.laboratoriotic.business.management.employee;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This interface is the one in charge of establishing communication between the modules
 * @author sblanco1
 *
 */
public interface EmployeeMgt {

	/**
	 * This method makes sure that the data base adds an employee
	 * @param oEmployee
	 */
	public void addEmployee(Employee oEmployee);
	
	/**
	 * This method makes sure that the data base removes an employee
	 * @param oEmployeeID
	 */
	public void removeEmployee(int oEmployeeID);
	
	/**
	 * This method makes sure that the data base modifies the information
	 * of the user
	 * @param oEmployee
	 * @return
	 */
	public Employee modifyEmployee(Employee oEmployee);
	
	/**
	 * This method makes sure that the data base searches an employee
	 * @param oemployeeID
	 * @return
	 */
	public Employee searchEmployee(int oemployeeID);
	
	/**
	 * This method makes sure that the data base returns a list with all the 
	 * current employees
	 * @return
	 */
	public ArrayList<Employee> getEmployees();	

	/**
	 * This is an auxiliary method	
	 * @param oEmployeeVO
	 * @return
	 */
	public Employee getEmployee(EmployeeVO oEmployeeVO);

	
	
	
}
