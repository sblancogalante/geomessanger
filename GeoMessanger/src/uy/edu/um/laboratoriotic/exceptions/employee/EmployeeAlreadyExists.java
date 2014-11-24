package uy.edu.um.laboratoriotic.exceptions.employee;

/**
 * This exception represents the case of trying to add an employee that already
 * exists on the database
 * 
 * @author sblanco1
 *
 */
public class EmployeeAlreadyExists extends Exception {

	/*
	 * Constructor
	 */
	public EmployeeAlreadyExists(String msg) {
		super(msg);
	}

	public EmployeeAlreadyExists() {
		// TODO Auto-generated constructor stub
	}

}
