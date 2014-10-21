package uy.edu.um.laboratoriotic.services.factory.message;

import uy.edu.um.laboratoriotic.services.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.services.manager.message.FileMessageMgr;

public class FileMessageFactory {
	
	/*
	 * Attributes of the class
	 */
	private static FileMessageFactory instance = null;
	
	/*
	 * Constructor
	 */
	private FileMessageFactory(){
		
	}
	
	public FileMessageFactory getInstance(){
		
		if (instance == null) {
			instance = new FileMessageFactory();
		}

		return instance;
	}
	
	public FileMessageMgt getFileMessageMgt(){
		
		return FileMessageMgr.getInstance();
	}
	

}
