package uy.edu.um.laboratoriotic.persistance.factory.employee;

import uy.edu.um.laboratoriotic.persistance.management.employee.EmployeeDAOMgt;
import uy.edu.um.laboratoriotic.persistance.manager.employee.EmployeeDAOMgr;

/**
 * This class is the connection that adds separation to the modules
 * @author sblanco1
 *
 */
public class EmployeeDAOFactory {

	/*
	 * Attributes of the class
	 */
	private static EmployeeDAOFactory instance = null;
	
	/*
	 * Constructor
	 */
	private EmployeeDAOFactory(){
		
	}
	
	/*
	 * Methods
	 */
	public static EmployeeDAOFactory getInstance(){
		if(instance == null){
			instance = new EmployeeDAOFactory();
		}
		
		return instance;
	}
	
	public static EmployeeDAOMgt getEmployeeDAOMgt(){
		return EmployeeDAOMgr.getInstance();
	}
	
}
