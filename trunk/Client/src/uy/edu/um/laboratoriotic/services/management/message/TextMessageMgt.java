package uy.edu.um.laboratoriotic.services.management.message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashSet;

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
	 * @return
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public TextMessageVO addTextMessage(TextMessageVO oTextMessageVO)
			throws RemoteException, NotBoundException;

	/**
	 * This method is the one that communicates with the commons interface to
	 * get all the messages
	 * 
	 * @return
	 * @throws NotBoundException
	 * @throws RemoteException
	 */
	public HashSet<TextMessageVO> getTextMessages(EmployeeVO oSender, HashSet<EmployeeVO> oReceiver) throws RemoteException,
			NotBoundException;

}
