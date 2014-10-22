package uy.edu.um.laboratoriotic.business.factory.message;

import uy.edu.um.laboratoriotic.business.manager.message.FileMessageMgr;

public class FileMessageFactory {
	
	/*
	 * Attributes of the class
	 */
	private static FileMessageFactory instance = null;

	/*
	 * Constructor
	 */
	private FileMessageFactory() {

	}

	public static FileMessageFactory getInstance() {

		if (instance == null) {
			instance = new FileMessageFactory();
		}

		return instance;
	}

	public FileMessageMgr getFileMessageMgt() {

		return FileMessageMgr.getInstance();
	}

}
