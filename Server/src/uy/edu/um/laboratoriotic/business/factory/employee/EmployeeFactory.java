package uy.edu.um.laboratoriotic.business.factory.employee;

import uy.edu.um.laboratoriotic.business.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.business.manager.employee.EmployeeMgr;

/**
 * This class is the connection that adds separation to the modules
 * 
 * @author sblanco1
 * 
 */
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
