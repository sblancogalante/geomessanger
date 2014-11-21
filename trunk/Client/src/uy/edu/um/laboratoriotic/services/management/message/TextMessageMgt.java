package uy.edu.um.laboratoriotic.services.management.message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

/**
 * This is the interface on the client side
 * 
 * @author sblanco1
 * 
 */
public interface TextMessageMgt {

	/**
	 * This method is the one that communicates with the commons interface to
	 * add a text message
	 * 
	 * @param oTextMessageVO
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public void addTextMessage(TextMessageVO oTextMessageVO)
			throws RemoteException, NotBoundException;

	/**
	 * This method is the one that communicates with the commons interface to
	 * get all the messages
	 * 
	 * @return
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public ArrayList<TextMessageVO> getTextMessages(EmployeeVO oSender,
			EmployeeVO oReceiver) throws RemoteException, NotBoundException;

	/**
	 * This method is the one that communicates with the commons interface to
	 * get a count of all the characters sent by an employee
	 * 
	 * @param oEmployeeVO
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws RemoteException, NotBoundException;
}
