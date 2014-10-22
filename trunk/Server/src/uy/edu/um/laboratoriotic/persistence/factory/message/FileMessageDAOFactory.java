package uy.edu.um.laboratoriotic.persistence.factory.message;

import uy.edu.um.laboratoriotic.persistence.management.message.FileMessageDAOMgt;
import uy.edu.um.laboratoriotic.persistence.manager.message.FileMessageDAOMgr;

/**
 * This class is the connection that adds separation to the modules
 * 
 * @author sblanco1
 * 
 */
public class FileMessageDAOFactory {

	/*
	 * Attributes of the class
	 */
	private static FileMessageDAOFactory instance = null;

	/*
	 * Constructor
	 */
	private FileMessageDAOFactory() {

	}

	public static FileMessageDAOFactory getInstance() {
		if (instance == null) {
			instance = new FileMessageDAOFactory();
		}

		return instance;
	}

	public static FileMessageDAOMgt getFileMessageDAOMgt() {
		return FileMessageDAOMgr.getInstance();
	}

	
}
