package uy.edu.um.laboratoriotic.communication.factory.general;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.communication.factory.employee.EmployeeRemoteFactory;
import uy.edu.um.laboratoriotic.communication.manager.employee.EmployeeRemoteMgr;
import uy.edu.um.laboratoriotic.communication.manager.general.GeneralRemoteMgr;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.management.general.GeneralRemoteMgt;

public class GeneralRemoteFactory {

	private static GeneralRemoteFactory instance = null;

	private GeneralRemoteFactory() {

	}

	public static GeneralRemoteFactory getInstance() {

		if (instance == null) {
			instance = new GeneralRemoteFactory();
		} 

		return instance;
	}

	public GeneralRemoteMgt getGeneralRemoteMgt() throws RemoteException {
		return GeneralRemoteMgr.getInstance();
	}
	
}
