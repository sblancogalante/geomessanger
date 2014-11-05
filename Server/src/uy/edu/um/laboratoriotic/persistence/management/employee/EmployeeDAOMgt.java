package uy.edu.um.laboratoriotic.persistence.management.employee;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;

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
	 * @throws RemoteException
	 */
	public void addEmployee(Employee oEmployee) throws DataBaseConnection;

	/**
	 * This method removes an employee from the database
	 * 
	 * @param oUserName
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void removeEmployee(String oUserName) throws DataBaseConnection;

	/**
	 * This method searches an employee in the database
	 * 
	 * @param oUserName
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public Employee searchEmployee(String oUserName) throws DataBaseConnection;

	/**
	 * This method return a list with all the employees of the database
	 * 
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public ArrayList<Employee> getEmployees() throws DataBaseConnection;

	/**
	 * This method checks the login
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public boolean checkLogin(String oUserName, String oPassword)
			throws DataBaseConnection;

	/**
	 * This method gets the user of the login
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public Employee getLoginEmployee(String oUserName, String oPassword)
			throws DataBaseConnection;

}
