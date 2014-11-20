package uy.edu.um.laboratoriotic.communication.manager.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.helper.Helper;
import uy.edu.um.laboratoriotic.business.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

/**
 * This class is the implementation of TextMessageRemoteMgt
 * 
 * @author sblanco1
 * 
 */
public class TextMessageRemoteMgr implements TextMessageRemoteMgt {

	/*
	 * Attributes of the class
	 */
	private static TextMessageRemoteMgr instance = null;

	/*
	 * Constructor
	 */
	private TextMessageRemoteMgr() throws RemoteException {

	}

	public static TextMessageRemoteMgr getInstance() throws RemoteException {

		if (instance == null) {
			instance = new TextMessageRemoteMgr();
		}

		return instance;
	}

	/*
	 * Management implementation methods
	 */
	@Override
	public void addTextMessage(TextMessageVO oTextMessageVO)
			throws RemoteException {
		// TODO Auto-generated method stub

		TextMessageMgt oTextMessageMgt = BusinessFacade.getInstance()
				.getTextMessageFactory().getTextMessageMgt();

		try {
			oTextMessageMgt.addTextMessage(oTextMessageVO);
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

	}

	@Override
	public ArrayList<TextMessageVO> getTextMessages(EmployeeVO oSenderVO,
			EmployeeVO oReceiverVO) throws RemoteException {
		// TODO Auto-generated method stub

		ArrayList<TextMessageVO> oListToReturn = new ArrayList<>();

		TextMessageMgt oTextMessageMgt = BusinessFacade.getInstance()
				.getTextMessageFactory().getTextMessageMgt();

		try {
			oListToReturn = oTextMessageMgt.getTextMessages(oSenderVO,
					oReceiverVO);

		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		return oListToReturn;
	}

	@Override
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub

		int returnCount = 0;

		TextMessageMgt oTextMessageMgt = BusinessFacade.getInstance()
				.getTextMessageFactory().getTextMessageMgt();

		try {

			Employee oEmployee = Helper.modularizeEmployee(oEmployeeVO);
			returnCount = oTextMessageMgt.countTextCharacters(oEmployee);

		} catch (DataBaseConnection e) {

		}

		return returnCount;
	}

	@Override
	public void clearHistory(EmployeeVO oSender, EmployeeVO oReceiver)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

}
