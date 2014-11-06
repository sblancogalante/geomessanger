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
		
		Employee oEmployee2 = new Employee(oTypeDocument, "1.814.930-7", "Antonio", "Blanco",
				"ablanco", "qwerty", oTypeLocation, oTypeSector,
				"ablanco@correo.um.edu.uy", "Gerente", null, null, false, true);

		try {			
			
			EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee);
			
			EmployeeDAOFactory.getEmployeeDAOMgt().addEmployee(oEmployee2);

			EmployeeDAOFactory.getEmployeeDAOMgt().getEmployees();

			EmployeeDAOFactory.getEmployeeDAOMgt().searchEmployee(
					oEmployee.getUserName());

			EmployeeDAOFactory.getEmployeeDAOMgt().removeEmployee(
					oEmployee.getUserName());
			
			oEmployee2.setAdmin(false);
			
			EmployeeDAOFactory.getEmployeeDAOMgt().modifyEmployee(oEmployee2);
			
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
			test();
		}

	}
}
