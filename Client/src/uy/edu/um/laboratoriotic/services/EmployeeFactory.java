package uy.edu.um.laboratoriotic.services;

import uy.edu.um.laboratoriotic.services.EmployeeMgt;

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
