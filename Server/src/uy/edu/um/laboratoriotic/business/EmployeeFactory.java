package uy.edu.um.laboratoriotic.business;

import uy.edu.um.laboratoriotic.business.EmployeeMgt;

/**
 * This class is the connection that adds separation to the modules
 * @author sblanco1
 *
 */
public class EmployeeFactory {

	private static EmployeeFactory instance = null;
	
	private EmployeeFactory(){
		
	}
	
	private static EmployeeFactory getInstance(){
		if(instance == null){
			instance = new EmployeeFactory();
		}
		
		return instance;
	}
	
	private static EmployeeMgt getEmployeeMgt(){
		return EmployeeMgr.getInstance();
	}
}
