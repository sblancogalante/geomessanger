package uy.edu.um.laboratoriotic.persistence.management.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;

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
	 * This method obtains the sent files from the database
	 * 
	 * @param oReceiver
	 * @param oSender
	 * @return 
	 * 
	 * @throws DataBaseConnection
	 * @throws RemoteException
	 * @throws EmployeeDoesNotExist 
	 * @throws EmployeeAlreadyExists 
	 */
	public ArrayList<FileMessage> getFileMessages(Employee oSender, Employee oReceiver)
			throws DataBaseConnection, RemoteException, EmployeeDoesNotExist, EmployeeAlreadyExists;

}
