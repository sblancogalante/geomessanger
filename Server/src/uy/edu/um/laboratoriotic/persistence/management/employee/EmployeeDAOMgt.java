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
	 * This method adds an employee to the data base
	 * 
	 * @param oEmployee
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void addEmployee(Employee oEmployee) throws DataBaseConnection,
			RemoteException;

	/**
	 * This method removes an employee from the data base
	 * 
	 * @param oEmployeeID
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void removeEmployee(int oEmployeeID) throws DataBaseConnection,
			RemoteException;

	/**
	 * This method searches an employee in the data base
	 * 
	 * @param oEmployeeID
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public Employee searchEmployee(int oEmployeeID) throws DataBaseConnection,
			RemoteException;

	/**
	 * This method return a list with all the employees of the data base
	 * 
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public ArrayList<Employee> getEmployees() throws DataBaseConnection,
			RemoteException;

	/**
	 * This method creates the data base tables
	 * 
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void createTable() throws DataBaseConnection, RemoteException;

}
