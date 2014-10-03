package uy.edu.um.laboratoriotic.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.EmployeeVO;

/**
 * This is the RMI interface
 * @author sblanco1
 *
 */
public interface EmployeeRemoteMgt extends Remote{

	public void addEmployee(EmployeeVO oEmployee) throws RemoteException;
	
	public void removeEmployee(EmployeeVO oEmployeeID) throws RemoteException;
	
	public String msg(String name) throws RemoteException;
	
	public EmployeeVO getEmployee(EmployeeVO oEmployee) throws RemoteException;
	
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException;
	
}
