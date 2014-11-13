package uy.edu.um.laboratoriotic.persistence;

import java.rmi.RemoteException;

import org.junit.Test;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.general.GeneralDAOFactory;

public class GeneralDAOTest {

	@Test
	public void test() throws RemoteException {

		Type oTypePrueba1 = new Type("Prueba", "prueba");
		Type oTypePrueba2 = new Type("Prueba", "test");
		Type oTypePrueba3 = new Type("Prueba", "proba");

		try {

			GeneralDAOFactory.getGeneralDAOMgt().addType(oTypePrueba1);
			GeneralDAOFactory.getGeneralDAOMgt().addType(oTypePrueba2);
			GeneralDAOFactory.getGeneralDAOMgt().addType(oTypePrueba3);

			GeneralDAOFactory.getGeneralDAOMgt().getTypes("Prueba");
			GeneralDAOFactory.getGeneralDAOMgt().searchType(
					oTypePrueba1);			

			GeneralDAOFactory.getGeneralDAOMgt().removeType(oTypePrueba1.getValue());

		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
			test();
		}

	}

}
