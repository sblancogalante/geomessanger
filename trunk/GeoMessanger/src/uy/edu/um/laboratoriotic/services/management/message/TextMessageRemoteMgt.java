package uy.edu.um.laboratoriotic.services.management.message;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

/**
 * This interface is the one that communicates the client module with the server
 * module
 * 
 * @author sblanco1
 * 
 */
public interface TextMessageRemoteMgt {

	/**
	 * This method communicates the server that the client is trying to add a
	 * text message
	 * 
	 * @param oTextMessageVO
	 * @return
	 * @throws RemoteException
	 */
	public TextMessageVO addTextMessage(TextMessageVO oTextMessageVO)
			throws RemoteException;

	/**
	 * This method communicates the server that the client is trying to get a
	 * list of all the messages
	 * 
	 * @param oDate
	 * @return
	 * @throws RemoteException
	 */
	public HashSet<TextMessageVO> getTextMessages(Date oDate)
			throws RemoteException;

	/**
	 * This method communicates the server that the client is trying to get a
	 * count of all the characters sent by an employee
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws RemoteException;

	/**
	 * This method communicates the server that the client is trying to clear
	 * the chat window for an employee
	 * 
	 * @param oSender
	 * @param oReceiver
	 */
	public void clearHistory(EmployeeVO oSender, EmployeeVO oReceiver);

}