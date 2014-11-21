package uy.edu.um.laboratoriotic.persistence.management.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

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
			throws DataBaseConnection;

	/**
	 * This method returns a list with all the messages of the database
	 * 
	 * @param oTextMessage
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public ArrayList<TextMessage> getTextMessages(Employee oSender,
			Employee oReceiverEmployee) throws DataBaseConnection;

	/**
	 * This method returns an int that represents the number of characters
	 * sent by an employee
	 * 
	 * @param oEmployee
	 * @return
	 * @throws DataBaseConnection
	 */
	public int countTextCharacters(Employee oEmployee)
			throws DataBaseConnection;

}