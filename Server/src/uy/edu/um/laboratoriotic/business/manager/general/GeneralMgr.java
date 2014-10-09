package uy.edu.um.laboratoriotic.business.manager.general;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.management.general.GeneralMgt;

public class GeneralMgr implements GeneralMgt {

	private static GeneralMgr instance = null;

	private GeneralMgr() {

	}

	public static GeneralMgr getInstance() {
		if (instance == null) {
			instance = new GeneralMgr();
		}

		return instance;
	}

	@Override
	public void addType(Type oType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeType(String oType) {
		// TODO Auto-generated method stub

	}

}
