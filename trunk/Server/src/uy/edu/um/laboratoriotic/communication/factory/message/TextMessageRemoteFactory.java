package uy.edu.um.laboratoriotic.communication.factory.message;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.communication.manager.TextMessageRemoteMgr;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageRemoteMgt;

public class TextMessageRemoteFactory {

	/*
	 * Attributes of the class
	 */
	private static TextMessageRemoteFactory instance = null;

	/*
	 * Constructor
	 */
	private TextMessageRemoteFactory() {

	}

	public static TextMessageRemoteFactory getInstance() {

		if (instance == null) {
			instance = new TextMessageRemoteFactory();
		}

		return instance;
	}

	public TextMessageRemoteMgt getTextMessageRemoteMgt() throws RemoteException {
		return TextMessageRemoteMgr.getInstance();
	}
	
}
