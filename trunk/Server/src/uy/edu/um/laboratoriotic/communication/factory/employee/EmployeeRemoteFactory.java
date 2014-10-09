package uy.edu.um.laboratoriotic.communication.factory.employee;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.communication.manager.employee.EmployeeRemoteMgr;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;

/**
 * This class is the connection that adds separation to the modules
 * @author sblanco1
 * 
 */
public class EmployeeRemoteFactory {

	private static EmployeeRemoteFactory instance = null;

	private EmployeeRemoteFactory() {

	}

	public static EmployeeRemoteFactory getInstance() {

		if (instance == null) {
			instance = new EmployeeRemoteFactory();
		} 

		return instance;
	}

	public EmployeeRemoteMgt getEmployeeRemoteMgt() throws RemoteException {
		return EmployeeRemoteMgr.getInstance();
	}

}
