package uy.edu.um.laboratoriotic.communication;

import java.rmi.RemoteException;

/**
 * This class is the connection that adds separation to the modules
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
