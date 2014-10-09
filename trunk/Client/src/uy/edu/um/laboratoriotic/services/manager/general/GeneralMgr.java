package uy.edu.um.laboratoriotic.services.manager.general;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.services.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

public class GeneralMgr implements GeneralMgt {

	/*
	 * Attributes of the class
	 */
	private static GeneralMgr instance = null;

	/*
	 * Constructors
	 */
	private GeneralMgr() {

	}

	public static GeneralMgr getInstance() {
		if (instance == null) {
			instance = new GeneralMgr();
		}

		return instance;
	}

	@Override
	public void addType(TypeVO oTypeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeType(TypeVO oTypeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

	}

}
