package uy.edu.um.laboratoriotic.business.management.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

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
	public void addTextMessage(TextMessageVO oTextMessageVO)
			throws DataBaseConnection, RemoteException;

	/**
	 * This method makes sure that the database returns a list with all the
	 * current messages
	 * 
	 * @param oTextMessage
	 * @return
	 * @throws DataBaseConnection
	 */
	public ArrayList<TextMessageVO> getTextMessages(EmployeeVO oSender,
			EmployeeVO oReceiver) throws DataBaseConnection, RemoteException;

	/**
	 * This method makes sure that the database returns a character count of 
	 * all the messages sent from an employee.
	 * 
	 * @param oEmployee
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 */
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws DataBaseConnection, RemoteException;

}
