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

	public void addEmployee(EmployeeVO oEmployee) throws RemoteException;

	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException;

	public EmployeeVO getEmployee(EmployeeVO oEmployee) throws RemoteException;

	public ArrayList<EmployeeVO> getEmployees() throws RemoteException;

}
