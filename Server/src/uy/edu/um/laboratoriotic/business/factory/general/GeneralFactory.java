package uy.edu.um.laboratoriotic.business.factory.general;

import uy.edu.um.laboratoriotic.business.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.business.manager.general.GeneralMgr;

/**
 * This class is the general connection that add separation to the modules
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
	 * Constructor
	 */
	private GeneralFactory() {

	}

	public static GeneralFactory getInstance() {

		if (instance == null) {
			instance = new GeneralFactory();
		}

		return instance;
	}

	public GeneralMgt getTypeMgt() {

		return GeneralMgr.getInstance();
	}

}
