package uy.edu.um.laboratoriotic.services.manager.message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.services.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

public class TextMessageMgr implements TextMessageMgt {

	/*
	 * Attributes of the class
	 */
	private static TextMessageMgr instance = null;

	/*
	 * Constructor
	 */
	private TextMessageMgr() {

	}

	public static TextMessageMgr getInstance() {

		if (instance == null) {
			instance = new TextMessageMgr();
		}

		return instance;
	}

	/*
	 * Management implementation methods
	 */
	@Override
	public void addTextMessage(TextMessageVO oTextMessageVO)
			throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub

		TextMessageRemoteMgt oTextMessageRemoteMgt = lookUp(
				"TextMessageRemoteMgr", 1099);

		oTextMessageRemoteMgt.addTextMessage(oTextMessageVO);
		
	}

	@Override
	public ArrayList<TextMessageVO> getTextMessages(EmployeeVO oSender, HashSet<EmployeeVO> oReceivers) throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		
		ArrayList<TextMessageVO> oArrayListToReturn = new ArrayList<>();
		
		TextMessageRemoteMgt oTextMessageRemoteMgt = lookUp(
				"TextMessageRemoteMgr", 1099);
		
		oArrayListToReturn = oTextMessageRemoteMgt.getTextMessages(oSender, oReceivers);
		
		return oArrayListToReturn;
	}

	/*
	 * Helping methods
	 */
	private TextMessageRemoteMgt lookUp(String sObjectService, int oPortNumber)
			throws RemoteException, NotBoundException {

		TextMessageRemoteMgt oReturn;

		Registry oRegistry = LocateRegistry.getRegistry(oPortNumber);
		oReturn = (TextMessageRemoteMgt) oRegistry.lookup(sObjectService);

		return oReturn;
	}

}
