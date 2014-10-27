package uy.edu.um.laboratoriotic.communication.manager.general;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.factory.general.GeneralFactory;
import uy.edu.um.laboratoriotic.business.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.business.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.services.management.general.GeneralRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This class is the implementation of GeneralRemoteMgt
 * 
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
	 * Management method implementations
	 */
	@Override
	public void addType(TypeVO oTypeVO) throws RemoteException {
		// TODO Auto-generated method stub

		GeneralMgt oGeneralMgt = BusinessFacade.getInstance()
				.getGeneralFactory().getGeneralMgt();

		oGeneralMgt.addType(oTypeVO);

	}

	@Override
	public void removeType(TypeVO oTypeVO) throws RemoteException {
		// TODO Auto-generated method stub

		GeneralMgt oRemoteGeneral = GeneralFactory.getInstance()
				.getGeneralMgt();
		// Type oType = oRemoteGeneral.getType(oTypeVO);
		oRemoteGeneral.removeType(oTypeVO);

	}

	@Override
	public TypeVO getType(TypeVO oTypeVO) throws RemoteException {
		// TODO Auto-generated method stub

		GeneralMgt oGeneralMgt = (GeneralMgt) BusinessFacade.getInstance()
				.getGeneralRemoteFactory();

		return oGeneralMgt.searchType(oTypeVO).toVO();
	}

	@Override
	public ArrayList<TypeVO> getTypes(String oType) throws RemoteException {
		// TODO Auto-generated method stub

		ArrayList<TypeVO> oListToReturn = new ArrayList<>();

		GeneralMgt oGeneralMgt = BusinessFacade.getInstance()
				.getGeneralFactory().getGeneralMgt();

		oListToReturn = oGeneralMgt.getTypes(oType);

		return oListToReturn;
	}

}
