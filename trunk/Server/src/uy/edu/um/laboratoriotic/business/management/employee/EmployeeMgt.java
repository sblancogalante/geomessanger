package uy.edu.um.laboratoriotic.business.management.employee;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
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
	public void addEmployee(EmployeeVO oEmployeeVO) throws DataBaseConnection;

	/**
	 * This method makes sure that the database removes an employee
	 * 
	 * @param oEmployeeID
	 * @throws DataBaseConnection
	 */
	public void removeEmployee(int oEmployeeID) throws DataBaseConnection;

	/**
	 * This method makes sure that the database modifies the information of
	 * oEmployee
	 * 
	 * @param oEmployee
	 * @return
	 */
	public Employee modifyEmployee(EmployeeVO oEmployeeVO);

	/**
	 * This method makes sure that the database searches an employee
	 * 
	 * @param oemployeeID
	 * @return
	 * @throws DataBaseConnection
	 */
	public Employee searchEmployee(int oemployeeID) throws DataBaseConnection;

	/**
	 * This method makes sure that the database returns a list with all the
	 * current employees
	 * 
	 * @return
	 * @throws DataBaseConnection
	 */
	public ArrayList<EmployeeVO> getEmployees() throws DataBaseConnection;

	/**
	 * This is an auxiliary method
	 * 
	 * @param oEmployeeVO
	 * @return
	 * @throws DataBaseConnection
	 */
	public Employee getEmployee(EmployeeVO oEmployeeVO)
			throws DataBaseConnection;

	/**
	 * This method makes sure that the database returns a boolean with the
	 * assertion of the login
	 * 
	 * @param oEmployee
	 * @return
	 * @throws DataBaseConnection
	 */
	public boolean checkLogin(EmployeeFilterVO oEmployeeFilterVO)
			throws DataBaseConnection;

	/**
	 * This method makes sure that the database returns an employee after the
	 * assertion of the login
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws DataBaseConnection
	 */
	public Employee getLoginEmployee(String oUserName, String oPassword)
			throws DataBaseConnection;

}
