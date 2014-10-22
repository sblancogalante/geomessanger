package uy.edu.um.laboratoriotic.communication.factory.message;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.communication.manager.message.FileMessageRemoteMgr;
import uy.edu.um.laboratoriotic.services.management.message.FileMessageRemoteMgt;

/**
 * This class is the connection that adds separation to the modules
 * 
 * @author sblanco1
 * 
 */
public class FileMessageRemoteFactory {

	/*
	 * Attributes of the class
	 */
	private static FileMessageRemoteFactory instance = null;

	/*
	 * Constructor
	 */
	private FileMessageRemoteFactory() {

	}

	public static FileMessageRemoteFactory getInstance() {

		if (instance == null) {
			instance = new FileMessageRemoteFactory();
		}

		return instance;
	}

	public FileMessageRemoteMgt getFileMessageRemoteMgt() throws RemoteException {
		
		return FileMessageRemoteMgr.getInstance();
	}
	
	
}
