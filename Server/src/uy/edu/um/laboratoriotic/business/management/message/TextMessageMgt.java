package uy.edu.um.laboratoriotic.business.management.message;

import java.util.ArrayList;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;

/**
 * This interface is the one in charge of establishing communication between the
 * modules
 * 
 * @author sblanco1
 * 
 */
public interface TextMessageMgt {

	/**
	 * This method makes sure that the database adds a message
	 * 
	 * @param oTextMessage
	 * @throws DataBaseConnection
	 */
	public void addTextMessage(TextMessage oTextMessage)
			throws DataBaseConnection;

	/**
	 * This method makes sure that the database returns a list with all the
	 * current messages
	 * 
	 * @param oTextMessage
	 * @return
	 * @throws DataBaseConnection
	 */
	public ArrayList<TextMessage> getTextMessages(Employee oSender, HashSet<Employee> oReceivers)
			throws DataBaseConnection;

}
