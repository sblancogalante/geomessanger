package uy.edu.um.laboratoriotic.services.management.message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;


public interface FileMessageMgt {
	
	/**
	 * This method is the one that communicates with the commons interface to
	 * add a text message
	 * 
	 * @param oTextMessageVO
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public void addFileMessage(FileMessageVO oFileMessageVO)
			throws RemoteException, NotBoundException;

	/**
	 * This method is the one that communicates with the commons interface to
	 * get all the messages
	 * 
	 * @return
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public HashSet<FileMessageVO> getFileMessages(EmployeeVO oSender, HashSet<EmployeeVO> oReceiver) throws RemoteException,
			NotBoundException;


}
