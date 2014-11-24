package uy.edu.um.laboratoriotic.exceptions.employee;

/**
 * This exception represents the case of trying to remove an employee that does
 * not exist on the database
 * 
 * @author sblanco1
 *
 */
public class EmployeeDoesNotExist extends Exception {

	/*
	 * Constructor
	 */
	public EmployeeDoesNotExist(String msg) {
		super(msg);
	}

	public EmployeeDoesNotExist() {
		// TODO Auto-generated constructor stub
	}
}
