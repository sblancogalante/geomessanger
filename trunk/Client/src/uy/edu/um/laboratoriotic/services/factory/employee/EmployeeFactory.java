package uy.edu.um.laboratoriotic.services.factory.employee;

import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.manager.employee.EmployeeMgr;

/**
 * This is the class that we use to get the instances of the others
 * 
 * @author sblanco1
 * 
 */
public class EmployeeFactory {

	/*
	 * Attributes of the class
	 */
	private static EmployeeFactory instance = null;

	/*
	 * Constructors
	 */
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
