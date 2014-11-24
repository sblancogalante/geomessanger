package uy.edu.um.laboratoriotic.services.management.employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.exceptions.employee.MissingArguments;
import uy.edu.um.laboratoriotic.exceptions.employee.PasswordTooShort;
import uy.edu.um.laboratoriotic.exceptions.employee.UserNameAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.WrongLogin;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This is the interface on the client side
 * 
 * @author sblanco1
 * 
 */
public interface EmployeeMgt {

	/**
	 * This method is the one that communicates with the commons interface to
	 * add an employee
	 * 
	 * @param oEmployeeVO
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws MissingArguments 
	 * @throws UserNameAlreadyExists 
	 * @throws PasswordTooShort 
	 * @throws EmployeeAlreadyExists 
	 */
	public void addEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			NotBoundException, EmployeeAlreadyExists, PasswordTooShort, UserNameAlreadyExists, MissingArguments;

	/**
	 * This method is the one that communicates with the commons interface to
	 * get a list of all the employees
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException,
			NotBoundException;

	/**
	 * This method is the one that communicates with the commons interface to
	 * remove an employee
	 * 
	 * @param oEmployeeVO
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws EmployeeDoesNotExist 
	 */
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			NotBoundException, EmployeeDoesNotExist;
	
	/**
	 * This method is the one that communicates with the commons interface to
	 * search an employee
	 * 
	 * @param oUserName
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws EmployeeDoesNotExist 
	 * @throws EmployeeAlreadyExists 
	 */
	public EmployeeVO searchEmployee(String oUserName) throws RemoteException,
			NotBoundException, EmployeeDoesNotExist, EmployeeAlreadyExists;
	
	/**
	 * This method is the one that communicates with the commons interface to 
	 * modify an employee
	 * 
	 * @param oEmployeeVO
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws EmployeeDoesNotExist 
	 */
	public EmployeeVO modifyEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
	NotBoundException, EmployeeDoesNotExist;

	/**
	 * This method is the one that communicates with the commons interface to
	 * check the login of an employee
	 * 
	 * @param oEmployeeVO
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws WrongLogin 
	 */
	public boolean checkLogin(EmployeeFilterVO oEmployeeVO)throws RemoteException,
	NotBoundException, WrongLogin;

	/**
	 * This method is the one that communicates with the commons interface to
	 * enter the menu of an asserted log in employee
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 * @throws EmployeeDoesNotExist 
	 */
	public EmployeeVO getLoginEmployee(EmployeeFilterVO oEmployeeFIlterVO)
			throws RemoteException, NotBoundException, EmployeeDoesNotExist;

}
