package uy.edu.um.laboratoriotic.services.management.employee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

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
	public void addEmployee(EmployeeVO oEmployee) throws RemoteException;

	/**
	 * This method communicates the server that the client is trying to remove
	 * an employee
	 * 
	 * @param oEmployeeVO
	 * @throws RemoteException
	 */
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException;

	/**
	 * This method communicates the server that the client is searching an
	 * employee
	 * 
	 * @param oEmployee
	 * @return
	 * @throws RemoteException
	 */
	public EmployeeVO getEmployee(EmployeeVO oEmployee) throws RemoteException;

	/**
	 * This method communicates the server that the client wants to obtain all
	 * the employees
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException;

	/**
	 * This method communicates the server that the client wants to assert
	 * a login
	 * 
	 * @param oEmployeeVO
	 * @return
	 * @throws RemoteException
	 */
	boolean checkLogin(EmployeeVO oEmployeeVO) throws RemoteException;

}
