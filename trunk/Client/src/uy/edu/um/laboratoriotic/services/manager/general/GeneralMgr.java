package uy.edu.um.laboratoriotic.services.manager.general;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.ServiceFacade;
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

		GeneralRemoteMgt oGeneralRemoteMgt = newLookUp("GeneralRemoteMgr");; 
		oGeneralRemoteMgt.addType(oTypeVO);
		
	}

	@Override
	public void removeType(TypeVO oTypeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub
		
		GeneralRemoteMgt oGeneralRemoteMgt = newLookUp("GeneralRemoteMgr");; 
		oGeneralRemoteMgt.removeType(oTypeVO);

	}

	@Override
	public TypeVO getType(TypeVO oTypeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<TypeVO> getTypes(String oType) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

		ArrayList<TypeVO> oListToReturn = new ArrayList<>();

		GeneralRemoteMgt oGeneralRemoteMgt = newLookUp("GeneralRemoteMgr");
		oListToReturn = oGeneralRemoteMgt.getTypes(oType);

		return oListToReturn;		
	}
	
	/*
	 * Helping methods
	 */	
	private GeneralRemoteMgt newLookUp(String sObjectService)
			throws RemoteException, NotBoundException {

		GeneralRemoteMgt oGeneralRemoteMgtToReturn;

		Registry oRegistry = LocateRegistry
				.getRegistry(ServiceFacade.getInstance().getHost(),
						ServiceFacade.getInstance().getPort());
		oGeneralRemoteMgtToReturn = (GeneralRemoteMgt) oRegistry
				.lookup(sObjectService);

		return oGeneralRemoteMgtToReturn;
	}

}