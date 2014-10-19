package uy.edu.um.laboratoriotic.services.management.employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

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

}