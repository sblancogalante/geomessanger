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
	public void addEmployee(Employee oEmployee) throws DataBaseConnection,
			RemoteException;

	/**
	 * This method removes an employee from the database
	 * 
	 * @param oEmployeeID
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void removeEmployee(int oEmployeeID) throws DataBaseConnection,
			RemoteException;

	/**
	 * This method searches an employee in the database
	 * 
	 * @param oEmployeeID
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public Employee searchEmployee(int oEmployeeID) throws DataBaseConnection,
			RemoteException;

	/**
	 * This method return a list with all the employees of the database
	 * 
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public ArrayList<Employee> getEmployees() throws DataBaseConnection,
			RemoteException;

	/**
	 * This method creates the database tables
	 * 
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void createTable() throws DataBaseConnection, RemoteException;

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
			throws DataBaseConnection, RemoteException;

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
			throws DataBaseConnection, RemoteException;

}
