package uy.edu.um.laboratoriotic.persistence.management.employee;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.exceptions.employee.WrongLogin;

/**
 * This is the persistence interface
 * 
 * @author sblanco1
 * 
 */
public interface EmployeeDAOMgt {

	/**
	 * This method adds an employee to the database
	 * 
	 * @param oEmployee
	 * @throws DataBaseConnection
	 */
	public void addEmployee(Employee oEmployee) throws DataBaseConnection;

	/**
	 * This method removes an employee from the database
	 * 
	 * @param oUserName
	 * @throws DataBaseConnection
	 */
	public void removeEmployee(String oUserName) throws DataBaseConnection;

	/**
	 * This method searches an employee in the database
	 * 
	 * @param oUserName
	 * @return
	 * @throws DataBaseConnection
	 * @throws EmployeeDoesNotExist
	 * @throws EmployeeAlreadyExists 
	 */
	public Employee searchEmployee(String oUserName) throws DataBaseConnection, EmployeeDoesNotExist, EmployeeAlreadyExists;

	/**
	 * This method searches an employee in the database
	 * 
	 * @param oUserName
	 * @return
	 * @throws DataBaseConnection
	 * @throws EmployeeDoesNotExist 
	 */
	public Employee searchEmployee(int oEmployeeID) throws DataBaseConnection, EmployeeDoesNotExist;
	
	/**
	 * This method returns an employee modified if the employee exists
	 * 
	 * @param oEmployee
	 * @return
	 * @throws DataBaseConnection
	 * @throws EmployeeDoesNotExist 	
	 */
	public Employee modifyEmployee(Employee oEmployee) throws DataBaseConnection, EmployeeDoesNotExist;
	
	/**
	 * This method return a list with all the employees of the database
	 * 
	 * @return
	 * @throws DataBaseConnection
	 */
	public ArrayList<Employee> getEmployees() throws DataBaseConnection;

	/**
	 * This method checks the login
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws DataBaseConnection
	 * @throws WrongLogin
	 */
	public boolean checkLogin(String oUserName, String oPassword)
			throws DataBaseConnection, WrongLogin;

	/**
	 * This method gets the user of the login
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws DataBaseConnection
	 * @throws EmployeeDoesNotExist 
	 */
	public Employee getLoginEmployee(String oUserName, String oPassword)
			throws DataBaseConnection, EmployeeDoesNotExist;
	
}
