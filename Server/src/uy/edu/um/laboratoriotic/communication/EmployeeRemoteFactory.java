package uy.edu.um.laboratoriotic.communication;

import java.rmi.RemoteException;

/**
 * 
 * @author sblanco1
 * 
 */
public class EmployeeRemoteFactory {

	private static EmployeeRemoteFactory instance = null;

	private EmployeeRemoteFactory() {

	}

	private static EmployeeRemoteFactory getInstance() {

		if (instance == null) {
			instance = new EmployeeRemoteFactory();
		} // metodo getInstance()

		return instance;
	}

	private static EmployeeRemoteMgt getEmployeeMgt() throws RemoteException {
		return EmployeeRemoteMgr.getInstance();
	}

}
