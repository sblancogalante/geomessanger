package uy.edu.um.laboratoriotic.services.factory.general;

import uy.edu.um.laboratoriotic.services.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.services.manager.general.GeneralMgr;

/**
 * This is the class that we use to get the instances of the others
 * 
 * @author sblanco1
 * 
 */
public class GeneralFactory {

	/*
	 * Attributes of the class
	 */
	private static GeneralFactory instance = null;

	/*
	 * Constructors
	 */
	private GeneralFactory() {

	}

	public static GeneralFactory getInstance() {
	
		if (instance == null) {
			instance = new GeneralFactory();
		}

		return instance;
	}
	
	public GeneralMgt getGeneralMgt(){
		
		return GeneralMgr.getInstance();
	}

}
