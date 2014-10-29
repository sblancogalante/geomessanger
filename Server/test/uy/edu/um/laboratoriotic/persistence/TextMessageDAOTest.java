package uy.edu.um.laboratoriotic.persistence;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.HashSet;

import org.junit.Test;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
import uy.edu.um.laboratoriotic.persistence.factory.message.TextMessageDAOFactory;

public class TextMessageDAOTest {

	@Test
	public void test() {

		Employee oSender = new Employee("5.062.081-0", "Santiago", "Blanco",
				"sblanco", "asdfg", "Uruguay", "Desarrollo",
				"sblanco1@correo.um.edu.uy", "Programador", null, null, false);

		Employee oReceiver = new Employee("1.814.930-7", "Antonio",
				"Blanco", "ablanco", "qwerty", "Uruguay", "Soporte",
				"ablancoa@correo.um.edu.uy", "Programador", null, null, false);

		HashSet<Employee> oReceivers = new HashSet<>();

		oReceivers.add(oReceiver);

		TextMessage oTextMessage = new TextMessage(1, "Hola Mundo", oSender,
				oReceivers, new Timestamp(System.currentTimeMillis()), false);

		try {
			
			EmployeeDAOFactory.getEmployeeDAOMgt().createTable();
			
			EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oSender);
			EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oReceiver);

			TextMessageDAOFactory.getTextMessageDAOMgt().createTable();

			TextMessageDAOFactory.getTextMessageDAOMgt().addTextMessage(
					oTextMessage);
			TextMessageDAOFactory.getTextMessageDAOMgt().getTextMessages(
					oSender, oReceivers);

		} catch (DataBaseConnection e) {
			test();
		} catch (RemoteException r) {
			// TODO Auto-generated catch block
			r.printStackTrace();
		}

	}

}
