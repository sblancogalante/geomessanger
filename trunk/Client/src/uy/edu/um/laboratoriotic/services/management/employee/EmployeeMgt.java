package uy.edu.um.laboratoriotic.services.management.employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
	 */
	public void addEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			NotBoundException;

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
	 */
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			NotBoundException;

	/**
	 * This method is the one that communicates with the commons interface to
	 * check the login of an employee
	 * 
	 * @param oEmployeeVO
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public boolean checkLogin(EmployeeFilterVO oEmployeeVO)throws RemoteException,
	NotBoundException;

	/**
	 * This method is the one that communicates with the commons interface to
	 * enter the menu of an asserted log in employee
	 * 
	 * @param oUserName
	 * @param oPassword
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public EmployeeVO getLoginEmployee(String oUserName, String oPassword)
			throws RemoteException, NotBoundException;

}
