package uy.edu.um.laboratoriotic.business.manager.general;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.management.general.GeneralMgt;
import uy.edu.um.laboratoriotic.persistence.factory.general.GeneralDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.general.GeneralDAOMgt;
import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

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

		GeneralDAOMgt oNewDAOGeneral = GeneralDAOFactory.getGeneralDAOMgt();
		Type oNewType = oType;
		oNewDAOGeneral.addType(oNewType);

	}

	@Override
	public void removeType(String oType) {
		// TODO Auto-generated method stub

		GeneralDAOMgt oNewDAOGeneral = GeneralDAOFactory.getGeneralDAOMgt();
		oNewDAOGeneral.removeType(oType);

	}

	@Override
	public Type modifyType(Type oType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type searchType(String oValue) {
		// TODO Auto-generated method stub

		Type oResult = null;

		GeneralDAOMgt oNewDAOGeneral = GeneralDAOFactory.getGeneralDAOMgt();
		Type oType = oNewDAOGeneral.searchType(oValue);

		if (oType != null) {
			oResult = oType;
		} else {
			System.out.println("No se encontro al Type con valor " + oValue);
		}

		Type oToReturn = new Type(oResult.getTypeID(), oResult.getType(),
				oResult.getValue());

		return oToReturn;

	}

	@Override
	public Type getType(TypeVO oTypeVO) {
		// TODO Auto-generated method stub

		GeneralDAOMgt oDAOGeneral = GeneralDAOFactory.getGeneralDAOMgt();
		Type oNewType = oDAOGeneral.searchType(oTypeVO.getType());

		Type oTypeToReturn = oNewType;

		return oTypeToReturn;

	}

}
