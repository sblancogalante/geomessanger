package uy.edu.um.laboratoriotic.persistence.manager.general;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.persistence.management.general.GeneralDAOMgt;

public class GeneralDAOMgr implements GeneralDAOMgt {

	/*
	 * Attributes of the class
	 */
	private static GeneralDAOMgr instance = null;

	/*
	 * Constructor of the class
	 */
	private GeneralDAOMgr() {

	}

	public static GeneralDAOMgr getInstance() {

		if (instance == null) {
			instance = new GeneralDAOMgr();
		}

		return instance;

	}
	
	/*
	 * Management interface method implementations
	 */
	@Override
	public void addType(Type oType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeType(Type oType) {
		// TODO Auto-generated method stub
		
	}

}
