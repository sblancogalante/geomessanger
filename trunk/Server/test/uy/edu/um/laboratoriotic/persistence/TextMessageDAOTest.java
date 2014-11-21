package uy.edu.um.laboratoriotic.persistence;

import java.rmi.RemoteException;

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
				"Gabriela", "Galante", "ggalante", "asdfg12345", oTypeLocation,
				oTypeSector, "ggalante@correo.um.edu.uy", "Secretaria", null,
				null, false, true);

		Employee oReceiver = new Employee(oTypeDocument, "1.814.930-7",
				"Antonio", "Blanco", "ablanco", "qwerty123456", oTypeLocation,
				oTypeSector, "ablancoa@correo.um.edu.uy", "Administrativo",
				null, null, false, false);

		TextMessage oTextMessage = new TextMessage(1, "Hola Mundo", oSender,
				oReceiver);
		TextMessage oTextMessage2 = new TextMessage(1, "Hello World",
				oReceiver, oSender);
		TextMessage oTextMessage3 = new TextMessage(1, "Aloha", oSender,
				oReceiver);
		TextMessage oTextMessage4 = new TextMessage(1, "Aloha ranurado",
				oSender, oReceiver);

		try {

			EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oSender);
			EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oReceiver);

			EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();
			TextMessageDAOFactory.getTextMessageDAOMgt().addTextMessage(
					oTextMessage);
			EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();
			TextMessageDAOFactory.getTextMessageDAOMgt().addTextMessage(
					oTextMessage2);
			EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();
			TextMessageDAOFactory.getTextMessageDAOMgt().addTextMessage(
					oTextMessage3);
			EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();
			TextMessageDAOFactory.getTextMessageDAOMgt().addTextMessage(
					oTextMessage4);
			TextMessageDAOFactory.getTextMessageDAOMgt().getTextMessages(
					oSender, oReceiver);
			System.out.println(TextMessageDAOFactory.getTextMessageDAOMgt()
					.countTextCharacters(oSender));

		} catch (DataBaseConnection e) {
			test();
		}

	}

}
