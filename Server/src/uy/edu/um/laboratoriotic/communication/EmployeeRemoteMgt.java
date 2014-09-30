package uy.edu.um.laboratoriotic.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.business.EmployeeVO;

/**
 * this is the rmi interface
 * @author sblanco1
 *
 */
public interface EmployeeRemoteMgt extends Remote{

	public void addEmployee(EmployeeVO oEmployee) throws RemoteException;
	
}
