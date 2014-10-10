package uy.edu.um.laboratoriotic.communication.manager.general;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.services.management.general.GeneralRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This class is the implementation of GeneralRemoteMgt
 * @author sblanco1
 *
 */
public class GeneralRemoteMgr implements GeneralRemoteMgt {

	/*
	 * Attributes of the class
	 */
	private static GeneralRemoteMgr instance = null;

	/*
	 * Constructors
	 */
	private GeneralRemoteMgr() throws RemoteException {

	}
	
	public static GeneralRemoteMgr getInstance() throws RemoteException {

		if (instance == null) {
			instance = new GeneralRemoteMgr();
		}

		return instance;
	}

	/*
	 * Management interface method implementations
	 */
	@Override
	public void addType(TypeVO oTypeVO) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeType(TypeVO oTypeVO) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
	
}
