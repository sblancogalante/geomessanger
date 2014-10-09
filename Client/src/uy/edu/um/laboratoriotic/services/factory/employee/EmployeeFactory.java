package uy.edu.um.laboratoriotic.services.factory.employee;

import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.manager.employee.EmployeeMgr;

public class EmployeeFactory {

	private static EmployeeFactory instance = null;

	private EmployeeFactory() {

	}

	public static EmployeeFactory getInstance() {
		if (instance == null) {
			instance = new EmployeeFactory();
		}

		return instance;
	}

	public EmployeeMgt getEmployeeMgt() {
		return EmployeeMgr.getInstance();
	}

}
