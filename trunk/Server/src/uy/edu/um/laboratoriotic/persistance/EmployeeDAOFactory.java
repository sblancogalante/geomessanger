package uy.edu.um.laboratoriotic.persistance;


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
	
	private EmployeeDAOFactory(){
		
	}
	
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
