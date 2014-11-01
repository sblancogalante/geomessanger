package uy.edu.um.laboratoriotic.services.management.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;


public interface FileMessageRemoteMgt {
	
	/**
	 * This method communicates the server that the client is trying to add a
	 * text message
	 * 
	 * @param oTextMessageVO
	 * @return
	 * @throws RemoteException
	 */
	public void addFileMessage(FileMessageVO oFileMessageVO)
			throws RemoteException;

	/**
	 * This method communicates the server that the client is trying to get a
	 * list of all the messages
	 * 
	 * @param oDate
	 * @return
	 * @throws RemoteException
	 */
	public ArrayList<FileMessageVO> getFileMessages(EmployeeVO oSender, EmployeeVO oReceiver) throws RemoteException;



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

