package uy.edu.um.laboratoriotic.services.manager.message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.services.ServiceFacade;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

/**
 * This is the implementation of TextMessageMgt
 * 
 * @author sblanco1
 * 
 */
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
			throws RemoteException, NotBoundException, EmployeeDoesNotExist, EmployeeAlreadyExists {
		// TODO Auto-generated method stub

		TextMessageRemoteMgt oTextMessageRemoteMgt = newLookUp("TextMessageRemoteMgr");

		try {
			oTextMessageRemoteMgt.addTextMessage(oTextMessageVO);
		} catch (EmployeeDoesNotExist e) {
			// TODO Auto-generated catch block
			throw new EmployeeDoesNotExist();
		} catch (EmployeeAlreadyExists e) {
			// TODO Auto-generated catch block
			throw new EmployeeAlreadyExists();
		}

	}

	@Override
	public ArrayList<TextMessageVO> getTextMessages(EmployeeVO oSender,
			EmployeeVO oReceiver) throws RemoteException, NotBoundException, EmployeeDoesNotExist, EmployeeAlreadyExists {
		// TODO Auto-generated method stub

		ArrayList<TextMessageVO> oArrayListToReturn = new ArrayList<>();

		TextMessageRemoteMgt oTextMessageRemoteMgt = newLookUp("TextMessageRemoteMgr");

		try {
			oArrayListToReturn = oTextMessageRemoteMgt.getTextMessages(oSender,
					oReceiver);
		} catch (EmployeeDoesNotExist e) {
			// TODO Auto-generated catch block
			throw new EmployeeDoesNotExist();
		} catch (EmployeeAlreadyExists e) {
			// TODO Auto-generated catch block
			throw new EmployeeAlreadyExists();
		}

		return oArrayListToReturn;
	}

	@Override
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws RemoteException, NotBoundException, EmployeeDoesNotExist, EmployeeAlreadyExists {
		// TODO Auto-generated method stub

		int returnCount = 0;

		TextMessageRemoteMgt oTextMessageRemoteMgt = newLookUp("TextMessageRemoteMgr");

		try {
			returnCount = oTextMessageRemoteMgt.countTextCharacters(oEmployeeVO);
		} catch (EmployeeDoesNotExist e) {
			// TODO Auto-generated catch block
			throw new EmployeeDoesNotExist();
		} catch (EmployeeAlreadyExists e) {
			// TODO Auto-generated catch block
			throw new EmployeeAlreadyExists();
		}

		return returnCount;
	}

	/*
	 * Helping methods
	 */
	private TextMessageRemoteMgt newLookUp(String sObjectService)
			throws RemoteException, NotBoundException {

		TextMessageRemoteMgt oTextMessageRemoteMgtToReturn;

		Registry oRegistry = LocateRegistry
				.getRegistry(ServiceFacade.getInstance().getHost(),
						ServiceFacade.getInstance().getPort());
		oTextMessageRemoteMgtToReturn = (TextMessageRemoteMgt) oRegistry
				.lookup(sObjectService);

		return oTextMessageRemoteMgtToReturn;
	}

}