package uy.edu.um.laboratoriotic.business.management.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;

public interface FileMessageMgt {
	
	/**
	 * This method makes sure that the database adds a message
	 * 
	 * @param oTextMessage
	 * @throws DataBaseConnection
	 */
	public void addFileMessage(FileMessageVO oFileMessageVO)
			throws DataBaseConnection;

	/**
	 * This method makes sure that the database returns a list with all the
	 * current messages
	 * 
	 * @param oTextMessage
	 * @return
	 * @throws DataBaseConnection
	 * @throws RemoteException 
	 * @throws EmployeeDoesNotExist 
	 */
	public ArrayList<FileMessageVO> getFileMessages(EmployeeVO oSender, EmployeeVO oReceiver)
			throws DataBaseConnection, RemoteException, EmployeeDoesNotExist;


}
