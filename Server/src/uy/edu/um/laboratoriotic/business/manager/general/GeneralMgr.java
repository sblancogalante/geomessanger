package uy.edu.um.laboratoriotic.business.manager.general;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
import uy.edu.um.laboratoriotic.persistence.factory.general.GeneralDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.employee.EmployeeDAOMgt;
import uy.edu.um.laboratoriotic.persistence.management.general.GeneralDAOMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This class is the implementation of GeneralMgt
 * 
 * @author sblanco1
 *
 */
public class GeneralMgr implements GeneralMgt {

	/*
	 * Attributes of the class
	 */
	private static GeneralMgr instance = null;

	/*
	 * Constructor of the class
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
	 * This are the management implementation methods
	 */
	@Override
	public void addType(TypeVO oTypeVO) {
		// TODO Auto-generated method stub

		GeneralDAOMgt oNewDAOGeneral = GeneralDAOFactory.getGeneralDAOMgt();
		Type oNewType = null;

		if (oTypeVO.isType()) {
			oNewType = new Type(oTypeVO.getTypeID(), oTypeVO.getTypeCountry(),
					oTypeVO.getTypeSector(), true);
		} else {
			oNewType = new Type(oTypeVO.getTypeID(), oTypeVO.getTypeCountry(),
					oTypeVO.getTypeSector(), false);
		}

		oNewDAOGeneral.addType(oNewType);

	}

	@Override
	public void removeType(TypeVO oTypeVO) {
		// TODO Auto-generated method stub

		GeneralDAOMgt oNewDAOGeneral = GeneralDAOFactory.getGeneralDAOMgt();
		Type oConvert = null;

		if (oTypeVO.isType()) {
			oConvert = new Type(oTypeVO.getTypeID(), oTypeVO.getTypeCountry(),
					oTypeVO.getTypeSector(), false);
		} else {
			oConvert = new Type(oTypeVO.getTypeID(), oTypeVO.getTypeCountry(),
					oTypeVO.getTypeSector(), true);
		}

		oNewDAOGeneral.removeType(oConvert);

	}

	@Override
	public Type modifyType(TypeVO oTypeVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type searchType(TypeVO oTypeVO) {
		// TODO Auto-generated method stub

		Type oToReturn = null;
		Type oConvert = null;

		GeneralDAOMgt oNewDAOGeneral = GeneralDAOFactory.getGeneralDAOMgt();

		if (oTypeVO.isType()) {
			oConvert = new Type(oTypeVO.getTypeID(), oTypeVO.getTypeCountry(),
					oTypeVO.getTypeSector(), true);
		} else {
			oConvert = new Type(oTypeVO.getTypeID(), oTypeVO.getTypeCountry(),
					oTypeVO.getTypeSector(), false);
		}

		Type oTypeDAO = oNewDAOGeneral.searchType(oConvert);

		if (oTypeVO.isType()) {
			oToReturn = new Type(oTypeDAO.getTypeID(),
					oTypeDAO.getTypeCountry(), oTypeDAO.getTypeSector(), true);
		} else {
			oToReturn = new Type(oTypeDAO.getTypeID(),
					oTypeDAO.getTypeCountry(), oTypeDAO.getTypeSector(), false);
		}

		return oToReturn;
	}

	@Override
	public ArrayList<TypeVO> getTypes(String oType) {
		// TODO Auto-generated method stub

		TypeVO oTypeVO;
		ArrayList<TypeVO> oListToReturn = new ArrayList<>();
		ArrayList<Type> oList = new ArrayList<>();

		GeneralDAOMgt oDAOType = GeneralDAOFactory.getGeneralDAOMgt();

		oList = oDAOType.getTypes(oType);

		for (Type iType : oList) {
			oTypeVO = iType.toVO();
			oListToReturn.add(oTypeVO);
		}

		return oListToReturn;
	}

}
