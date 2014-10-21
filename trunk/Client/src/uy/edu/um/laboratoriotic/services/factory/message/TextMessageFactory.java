package uy.edu.um.laboratoriotic.services.factory.message;

import uy.edu.um.laboratoriotic.services.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.services.manager.message.TextMessageMgr;

/**
 * This is the class that we use to get the instances of the others
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
	private TextMessageFactory(){
		
	}
	
	public static TextMessageFactory getInstance(){
		
		if (instance == null) {
			instance = new TextMessageFactory();
		}

		return instance;
	}
	
	public TextMessageMgt getTextMessageMgt(){
		
		return TextMessageMgr.getInstance();
	}
	
}
