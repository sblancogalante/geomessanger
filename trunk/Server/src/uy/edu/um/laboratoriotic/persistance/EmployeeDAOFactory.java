package uy.edu.um.laboratoriotic.persistance;


/**
 * 
 * @author sblanco1
 *
 */
public class EmployeeDAOFactory {

	/*
	 * Attributes of the class
	 */
	private static EmployeeDAOFactory instance = null;
	
	private EmployeeDAOFactory(){
		
	}
	
	private static EmployeeDAOFactory getInstance(){
		if(instance == null){
			instance = new EmployeeDAOFactory();
		}
		
		return instance;
	}
	
	public static EmployeeDAOMgt getEmployeeDAOMgt(){
		return EmployeeDAOMgr.getInstance();
	}
	
}
