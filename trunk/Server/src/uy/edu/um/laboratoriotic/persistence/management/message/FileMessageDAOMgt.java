package uy.edu.um.laboratoriotic.persistence.management.message;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;

/**
 * This is the persistence interface
 * 
 * @author sblanco1
 * 
 */
public interface FileMessageDAOMgt {

	/**
	 * This method adds a file to the database
	 * 
	 * @param oTextMessage
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void addFileMessage(FileMessage oFileMessage)
			throws DataBaseConnection, RemoteException;
	
	/**
	 * This method creates the database tables
	 * 
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void createTable() throws DataBaseConnection, RemoteException;
	
}
