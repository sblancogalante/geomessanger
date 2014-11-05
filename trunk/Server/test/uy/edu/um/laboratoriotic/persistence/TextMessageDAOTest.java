package uy.edu.um.laboratoriotic.persistence;

import java.rmi.RemoteException;
import java.sql.Timestamp;

import org.junit.Test;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
import uy.edu.um.laboratoriotic.persistence.factory.message.TextMessageDAOFactory;

public class TextMessageDAOTest {

	@Test
	public void test() throws RemoteException {

		Type oTypeDocument = new Type("Documento", "Cedula");
		Type oTypeLocation = new Type("Location", "Uruguay");
		Type oTypeSector = new Type("Sector", "Desarrollo");

		Employee oSender = new Employee(oTypeDocument, "5.062.081-0",
				"Santiago", "Blanco", "sblanco", "asdfg", oTypeLocation,
				oTypeSector, "sblanco1@correo.um.edu.uy", "Programador", null,
				null, false, true);

		Employee oReceiver = new Employee(oTypeDocument, "1.814.930-7",
				"Antonio", "Blanco", "ablanco", "qwerty", oTypeLocation,
				oTypeSector, "ablancoa@correo.um.edu.uy", "Programador", null,
				null, false, false);

		TextMessage oTextMessage = new TextMessage(1, "Hola Mundo", oSender,
				oReceiver, new Timestamp(System.currentTimeMillis()));

		try {

			// EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oSender);
			// EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oReceiver);

			EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();
			TextMessageDAOFactory.getTextMessageDAOMgt().addTextMessage(
					oTextMessage);
			TextMessageDAOFactory.getTextMessageDAOMgt().getTextMessages(
					oSender, oReceiver);

		} catch (DataBaseConnection e) {
			test();
		}

	}

}
