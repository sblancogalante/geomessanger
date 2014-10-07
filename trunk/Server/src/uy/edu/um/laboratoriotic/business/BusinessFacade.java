package uy.edu.um.laboratoriotic.business;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.communication.EmployeeRemoteFactory;

/**
 * This class recognizes all interfaces of communication module and creates
 * instances to isolate the business module of the communication module
 * 
 * @author sblanco1
 * 
 */
public class BusinessFacade {

	/*
	 * Attributes of the class
	 */
	private static BusinessFacade instance = null;

	private BusinessFacade() {

	}

	public static BusinessFacade getInstance() {

		if (instance == null) {
			instance = new BusinessFacade();
		}

		return instance;

	}

	/*
	 * All the instances
	 */
	public EmployeeFactory getEmployeeFactory() {
		return EmployeeFactory.getInstance();
	}

	public EmployeeRemoteFactory getEmployeeRemoteFactory() throws RemoteException{
		
		EmployeeRemoteFactory oEmployee = null;
		
		oEmployee = EmployeeRemoteFactory.getInstance();
		
		return oEmployee;
	}
	
	
}
