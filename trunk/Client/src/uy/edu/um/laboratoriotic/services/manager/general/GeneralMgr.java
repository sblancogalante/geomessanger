package uy.edu.um.laboratoriotic.services.manager.general;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import uy.edu.um.laboratoriotic.services.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.services.management.general.GeneralRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This is the implementation of GeneralMgt
 * @author sblanco1
 *
 */
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

	/*
	 * Management implementation methods
	 */
	@Override
	public void addType(TypeVO oTypeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

		GeneralRemoteMgt oGeneralRemoteMgt = lookUp("GeneralRemoteMgr", 1099);; 
		oGeneralRemoteMgt.addType(oTypeVO);
		
	}

	@Override
	public void removeType(TypeVO oTypeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub
		
		GeneralRemoteMgt oGeneralRemoteMgt = lookUp("GeneralRemoteMgr", 1099);; 
		oGeneralRemoteMgt.removeType(oTypeVO);

	}

	@Override
	public TypeVO getType(TypeVO oTypeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * Helping methods
	 */
	private GeneralRemoteMgt lookUp(String sObjectService, int oPortNumber)
			throws RemoteException, NotBoundException {
		
		GeneralRemoteMgt oReturn;

		Registry oRegistry = LocateRegistry.getRegistry(oPortNumber);
		oReturn = (GeneralRemoteMgt) oRegistry.lookup(sObjectService);

		return oReturn;

	}
	

}
