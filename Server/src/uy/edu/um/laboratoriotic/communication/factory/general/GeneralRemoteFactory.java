package uy.edu.um.laboratoriotic.communication.factory.general;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.communication.manager.general.GeneralRemoteMgr;
import uy.edu.um.laboratoriotic.services.management.general.GeneralRemoteMgt;

/**
 * This class is the connection that adds separation to the modules
 * @author sblanco1
 * 
 */
public class GeneralRemoteFactory {

	/*
	 * Attributes of the class
	 */
	private static GeneralRemoteFactory instance = null;

	/*
	 * Constructor
	 */
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
