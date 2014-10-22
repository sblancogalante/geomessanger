package uy.edu.um.laboratoriotic.business.management.message;

import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;

public interface FileMessageMgt {
	
	/**
	 * This method makes sure that the database adds a message
	 * 
	 * @param oTextMessage
	 * @throws DataBaseConnection
	 */
	public void addFileMessage(FileMessage oFileMessage)
			throws DataBaseConnection;

	/**
	 * This method makes sure that the database returns a list with all the
	 * current messages
	 * 
	 * @param oTextMessage
	 * @return
	 * @throws DataBaseConnection
	 */
//	public ArrayList<FileMessage> getFileMessages(Employee oSender, HashSet<Employee> oReceivers)
//			throws DataBaseConnection;


}
