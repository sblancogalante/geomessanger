package uy.edu.um.laboratoriotic.services.management.employee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.exceptions.employee.MissingArguments;
import uy.edu.um.laboratoriotic.exceptions.employee.PasswordTooShort;
import uy.edu.um.laboratoriotic.exceptions.employee.UserNameAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.WrongArguments;
import uy.edu.um.laboratoriotic.exceptions.employee.WrongLogin;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This interface is the one that communicates the client module with the server
 * module
 * 
 * @author sblanco1
 * 
 */
public interface EmployeeRemoteMgt extends Remote {

	/**
	 * This method communicates the server that the client is trying to add a
	 * new employee
	 * 
	 * @param oEmployee
	 * @throws RemoteException
	 */
	public void addEmployee(EmployeeVO oEmployee) throws RemoteException,
			EmployeeAlreadyExists, PasswordTooShort, UserNameAlreadyExists,
			MissingArguments;

	/**
	 * This method communicates the server that the client is trying to remove
	 * an employee
	 * 
	 * @param oEmployeeVO
	 * @throws RemoteException
	 * @throws EmployeeAlreadyExists 
	 */
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			EmployeeDoesNotExist, EmployeeAlreadyExists;

	/**
	 * This method communicates the server that the client is searching an
	 * employee
	 * 
	 * @param oUserName
	 * @return
	 * @throws RemoteException
	 */
	public EmployeeVO searchEmployee(String oUserName) throws RemoteException,
			EmployeeDoesNotExist, EmployeeAlreadyExists;

	/**
	 * This method communicates the server that the client wants to modify an
	 * employee
	 * 
	 * @param oEmployeeVO
	 * @return
	 * @throws RemoteException
	 * @throws EmployeeAlreadyExists 
	 */
	public EmployeeVO modifyEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException, EmployeeDoesNotExist, EmployeeAlreadyExists;

	/**
	 * This method communicates the server that the client wants to obtain all
	 * the employees
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException;

	/**
	 * This method communicates the server that the client wants to assert a
	 * login
	 * 
	 * @param oEmployeeVO
	 * @return
	 * @throws RemoteException
	 */
	public boolean checkLogin(EmployeeFilterVO oEmployeeFilterVO)
			throws RemoteException, WrongLogin;

	/**
	 * This method communicates the server that the client wants to obtain the
	 * user that just log in
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws RemoteException
	 */
	public EmployeeVO getLoginEmployee(EmployeeFilterVO oEmployeeFilterVO)
			throws RemoteException, EmployeeDoesNotExist;

}
