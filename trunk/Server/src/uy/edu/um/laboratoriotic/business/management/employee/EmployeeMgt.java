package uy.edu.um.laboratoriotic.business.management.employee;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.exceptions.employee.WrongLogin;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This interface is the one in charge of establishing communication between the
 * modules
 * 
 * @author sblanco1
 * 
 */
public interface EmployeeMgt {

	/**
	 * This method makes sure that the database adds an employee
	 * 
	 * @param oEmployee
	 * @throws DataBaseConnection
	 */
	public void addEmployee(EmployeeVO oEmployeeVO) throws DataBaseConnection, RemoteException;

	/**
	 * This method makes sure that the database removes an employee
	 * 
	 * @param oUserName
	 * @throws DataBaseConnection
	 */
	public void removeEmployee(String oUserName) throws DataBaseConnection, RemoteException;

	/**
	 * This method makes sure that the database modifies the information of
	 * oEmployee
	 * 
	 * @param oEmployee
	 * @return
	 * @throws EmployeeDoesNotExist 
	 */
	public Employee modifyEmployee(EmployeeVO oEmployeeVO) throws DataBaseConnection, RemoteException, EmployeeDoesNotExist;

	/**
	 * This method makes sure that the database searches an employee
	 * 
	 * @param oUserName
	 * @return
	 * @throws DataBaseConnection
	 * @throws EmployeeDoesNotExist 
	 */
	public Employee searchEmployee(String oUserName) throws DataBaseConnection, RemoteException, EmployeeDoesNotExist;

	/**
	 * This method makes sure that the database returns a list with all the
	 * current employees
	 * 
	 * @return
	 * @throws DataBaseConnection
	 */
	public ArrayList<EmployeeVO> getEmployees() throws DataBaseConnection, RemoteException;

	/**
	 * This method makes sure that the database returns a boolean with the
	 * assertion of the login
	 * 
	 * @param oEmployee
	 * @return
	 * @throws DataBaseConnection
	 * @throws WrongLogin 
	 */
	public boolean checkLogin(EmployeeFilterVO oEmployeeFilterVO)
			throws DataBaseConnection, RemoteException, WrongLogin;

	/**
	 * This method makes sure that the database returns an employee after the
	 * assertion of the login
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws DataBaseConnection
	 * @throws EmployeeDoesNotExist 
	 */
	public Employee getLoginEmployee(EmployeeFilterVO oEmployeeFilterVO)
			throws DataBaseConnection, RemoteException, EmployeeDoesNotExist;

}
