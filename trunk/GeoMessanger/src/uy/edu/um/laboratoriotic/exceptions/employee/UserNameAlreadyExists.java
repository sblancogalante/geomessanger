package uy.edu.um.laboratoriotic.exceptions.employee;

/**
 * This exception represents the case of trying to add an employee with the same
 * user name of another that already exists on the database
 * 
 * @author sblanco1
 *
 */
public class UserNameAlreadyExists extends Exception {

	/*
	 * Constructor
	 */
	public UserNameAlreadyExists(String msg) {
		super(msg);
	}

}
