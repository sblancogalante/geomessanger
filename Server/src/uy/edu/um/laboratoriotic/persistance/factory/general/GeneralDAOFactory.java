package uy.edu.um.laboratoriotic.persistance.factory.general;

import uy.edu.um.laboratoriotic.persistance.management.general.GeneralDAOMgt;
import uy.edu.um.laboratoriotic.persistance.manager.general.GeneralDAOMgr;

/**
 * This class is the connection that adds separation to the modules
 * @author sblanco1
 *
 */
public class GeneralDAOFactory {

	/*
	 * Attributes of the class
	 */
	private static GeneralDAOFactory instance = null;
	
	/*
	 * Constructor
	 */
	private GeneralDAOFactory(){
		
	}
	
	public static GeneralDAOFactory getInstance(){
		if(instance == null){
			instance = new GeneralDAOFactory();
		}
		
		return instance;
	}
	
	public static GeneralDAOMgt getGeneralDAOMgt(){
		return GeneralDAOMgr.getInstance();
	}
	
}
