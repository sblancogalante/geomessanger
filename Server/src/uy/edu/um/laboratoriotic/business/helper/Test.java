package uy.edu.um.laboratoriotic.business.helper;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

public class Test {

	public static void main(String[] args) {

		Type oTypeDocument = new Type("Document", "Cedula");
		Type oTypeLocation = new Type("Location", "Uruguay");
		Type oTypeSector = new Type("Sector", "Desarrollo");

		EmployeeVO oEmployee = new EmployeeVO(6, oTypeDocument.toVO(),
				"5.062.081-0", "Santiago", "Blanco", "sblanco", "asdfg",
				oTypeLocation.toVO(), oTypeSector.toVO(),
				"sblanco1@correo.um.edu.uy", "Programador", null, null, false,
				true);

		EmployeeVO oEmployee2 = new EmployeeVO(7, oTypeDocument.toVO(),
				"1.814.930-7", "Antonio", "Blanco", "ablanco", "qwerty",
				oTypeLocation.toVO(), oTypeSector.toVO(),
				"ablanco@correo.um.edu.uy", "Gerente", null, null, false, true);

		TextMessageVO oTextMessage = new TextMessageVO(0, "Hola", oEmployee,
				oEmployee2);

		Helper.modularizeTextMessage(oTextMessage);

		System.out.println(oTextMessage.getSender().getEmployeeID() + "  "
				+ oTextMessage.getReceiver().getEmployeeID());
	}

}
