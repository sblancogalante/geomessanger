package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;
import java.util.HashSet;

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
	public TextMessage addTextMessage(TextMessage oTextMessage)
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
		return null;

	}

	@Override
	public HashSet<TextMessage> getTextMessages()
			throws DataBaseConnection {
		// TODO Auto-generated method stub

		TextMessageDAOMgt oDAOTextMessage = TextMessageDAOFactory
				.getTextMessageDAOMgt();
		HashSet<TextMessage> list = new HashSet<TextMessage>();
		try {
			list = oDAOTextMessage.getTextMessages();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}
