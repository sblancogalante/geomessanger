package uy.edu.um.laboratoriotic.persistance.manager.general;

import uy.edu.um.laboratoriotic.persistance.management.general.GeneralDAOMgt;

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

}
