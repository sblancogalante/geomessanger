package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.business.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.message.TextMessageDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.message.TextMessageDAOMgt;

/**
 * This class is the implementation of TextMessageMgt
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
	public void addTextMessage(TextMessage oTextMessage)
			throws DataBaseConnection {
		// TODO Auto-generated method stub
		TextMessageDAOMgt oNewDAOTextMessage = TextMessageDAOFactory
				.getTextMessageDAOMgt();
		TextMessage oNewTextMessage = oTextMessage;
		try {
			oNewDAOTextMessage.addTextMessage(oNewTextMessage);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<TextMessage> getTextMessages(Employee oSender,
			HashSet<Employee> oReceivers) throws DataBaseConnection {
		// TODO Auto-generated method stub

		ArrayList<TextMessage> list = new ArrayList<TextMessage>();
		
		TextMessageDAOMgt oDAOTextMessage = TextMessageDAOFactory
				.getTextMessageDAOMgt();
				
		try {
			list = oDAOTextMessage.getTextMessages(oSender, oReceivers);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}
