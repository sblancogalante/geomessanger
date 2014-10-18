package uy.edu.um.laboratoriotic.persistence.factory.message;

import uy.edu.um.laboratoriotic.persistence.management.message.TextMessageDAOMgt;
import uy.edu.um.laboratoriotic.persistence.manager.message.TextMessageDAOMgr;

/**
 * This class is the connection that adds separation to the modules
 * 
 * @author sblanco1
 * 
 */
public class TextMessageDAOFactory {

	/*
	 * Attributes of the class
	 */
	private static TextMessageDAOFactory instance = null;

	/*
	 * Constructor
	 */
	private TextMessageDAOFactory() {

	}

	public static TextMessageDAOFactory getInstance() {
		if (instance == null) {
			instance = new TextMessageDAOFactory();
		}

		return instance;
	}

	public static TextMessageDAOMgt getTextMessageDAOMgt() {
		return TextMessageDAOMgr.getInstance();
	}

}
