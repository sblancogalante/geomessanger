package uy.edu.um.laboratoriotic.business.manager.general;

import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.general.GeneralDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.general.GeneralDAOMgt;
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

		oNewType = new Type(oTypeVO.getTypeID(), oTypeVO.getType(),
				oTypeVO.getValue());

		try {
			oNewDAOGeneral.addType(oNewType);
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
			
		}

	}

	@Override
	public void removeType(TypeVO oTypeVO) {
		// TODO Auto-generated method stub

		GeneralDAOMgt oNewDAOGeneral = GeneralDAOFactory.getGeneralDAOMgt();		

		try {
			oNewDAOGeneral.removeType(oTypeVO.getValue());
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
			
		}

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

		oConvert = new Type(oTypeVO.getTypeID(), oTypeVO.getType(),
				oTypeVO.getValue());

		Type oTypeDAO;
		
		try {
			
			oTypeDAO = oNewDAOGeneral.searchType(oConvert);
			oToReturn = new Type(oTypeDAO.getTypeID(), oTypeDAO.getType(),
					oTypeDAO.getValue());
			
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
			
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

		try {
			
			oList = oDAOType.getTypes(oType);
			
			for (Type iType : oList) {
				oTypeVO = iType.toVO();
				oListToReturn.add(oTypeVO);
			}

		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
			
		}
		
		return oListToReturn;
	}

}
