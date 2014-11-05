package uy.edu.um.laboratoriotic.persistence;

import java.rmi.RemoteException;

import org.junit.Test;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;

public class EmployeeDAOTest {

	@Test
	public void test() throws RemoteException {

		Type oTypeDocument = new Type("Document", "Cedula");
		Type oTypeLocation = new Type("Location", "Uruguay");
		Type oTypeSector = new Type("Sector", "Desarrollo");

		Employee oEmployee = new Employee(oTypeDocument, "5.062.081-0", "Santiago", "Blanco",
				"sblanco", "asdfg", oTypeLocation, oTypeSector,
				"sblanco1@correo.um.edu.uy", "Programador", null, null, false, true);

		/*
		 * Employee oEmployee1 = new Employee("Luis", 48963214, "Chile",
		 * "Testing", true);
		 * 
		 * Employee oEmployee2 = new Employee("Paul", 42735684, "Uruguay",
		 * "Testing", true);
		 */

		try {
			
			System.out
					.println("//////////////////////Test de addEmployee()//////////////////////");

			EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee);

			// EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee1);

			// EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee2);

			System.out
					.println("//////////////////////Test de getEmployees()/////////////////////");

			EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();

			System.out
					.println("//////////////////////Test de searchEmployee()/////////////////////");

			EmployeeDAOFactory.getEmployeeDAOMgt().searchEmployee(
					oEmployee.getUserName());

			System.out
					.println("//////////////////////Test de removeEmployee()/////////////////////");

			EmployeeDAOFactory.getEmployeeDAOMgt().removeEmployee(
					oEmployee.getUserName());
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
			test();
		}

	}
}
