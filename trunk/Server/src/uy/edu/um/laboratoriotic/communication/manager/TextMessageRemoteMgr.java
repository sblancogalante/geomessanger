package uy.edu.um.laboratoriotic.communication.manager;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import uy.edu.um.laboratoriotic.services.management.message.TextMessageRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

public class TextMessageRemoteMgr implements TextMessageRemoteMgt{

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
	public TextMessageVO addTextMessage(EmployeeVO oSender,
			TextMessageVO oTextMessageVO, ArrayList<EmployeeVO> receivers)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<TextMessageVO> getTextMessages(Date oDate)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clearHistory(EmployeeVO oSender, EmployeeVO oReceiver) {
		// TODO Auto-generated method stub
		
	}

}
