package uy.edu.um.laboratoriotic.business.factory.message;

import uy.edu.um.laboratoriotic.business.manager.message.TextMessageMgr;

/**
 * This class is the connection that adds separation to the modules
 * 
 * @author sblanco1
 * 
 */
public class TextMessageFactory {

	/*
	 * Attributes of the class
	 */
	private static TextMessageFactory instance = null;

	/*
	 * Constructor
	 */
	private TextMessageFactory() {

	}

	public static TextMessageFactory getInstance() {

		if (instance == null) {
			instance = new TextMessageFactory();
		}

		return instance;
	}

	public TextMessageMgr getTextMessageMgt() {

		return TextMessageMgr.getInstance();
	}

}
