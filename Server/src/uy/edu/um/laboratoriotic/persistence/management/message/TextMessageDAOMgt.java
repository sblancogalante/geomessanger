package uy.edu.um.laboratoriotic.persistence.management.message;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;

/**
 * This is the persistence interface
 * 
 * @author sblanco1
 * 
 */
public interface TextMessageDAOMgt {

	/**
	 * This method adds a message to the database
	 * 
	 * @param oTextMessage
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void addTextMessage(TextMessage oTextMessage)
			throws DataBaseConnection, RemoteException;

	/**
	 * This method return a list with all the messages of the database
	 * 
	 * @param oTextMessage
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public ArrayList<TextMessage> getTextMessages(Employee oSender, HashSet<Employee> oReceivers)
			throws DataBaseConnection, RemoteException;

	/**
	 * This method creates the database tables
	 * 
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public void createTable() throws DataBaseConnection, RemoteException;

}
