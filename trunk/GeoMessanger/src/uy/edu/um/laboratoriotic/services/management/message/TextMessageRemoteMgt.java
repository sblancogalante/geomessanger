package uy.edu.um.laboratoriotic.services.management.message;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

/**
 * This interface is the one that communicates the client module with the server
 * module
 * 
 * @author sblanco1
 * 
 */
public interface TextMessageRemoteMgt extends Remote {

	/**
	 * This method communicates the server that the client is trying to add a
	 * text message
	 * 
	 * @param oTextMessageVO
	 * @return
	 * @throws RemoteException
	 * @throws EmployeeDoesNotExist 
	 * @throws EmployeeAlreadyExists 
	 */
	public void addTextMessage(TextMessageVO oTextMessageVO)
			throws RemoteException, EmployeeDoesNotExist, EmployeeAlreadyExists;

	/**
	 * This method communicates the server that the client is trying to get a
	 * list of all the messages
	 * 
	 * @param oDate
	 * @return
	 * @throws RemoteException
	 * @throws EmployeeDoesNotExist 
	 * @throws EmployeeAlreadyExists 
	 */
	public ArrayList<TextMessageVO> getTextMessages(EmployeeVO oSender, EmployeeVO oReceiver) throws RemoteException, EmployeeDoesNotExist, EmployeeAlreadyExists;

	/**
	 * This method communicates the server that the client is trying to get a
	 * count of all the characters sent by an employee
	 * 
	 * @return
	 * @throws RemoteException
	 * @throws EmployeeDoesNotExist 
	 * @throws EmployeeAlreadyExists 
	 */
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws RemoteException, EmployeeDoesNotExist, EmployeeAlreadyExists;

	/**
	 * This method communicates the server that the client is trying to clear
	 * the chat window for an employee
	 * 
	 * @param oSender
	 * @param oReceiver
	 * @throws RemoteException
	 */
	public void clearHistory(EmployeeVO oSender, EmployeeVO oReceiver)
			throws RemoteException;

}