package uy.edu.um.laboratoriotic.exceptions.employee;

/**
 * This exception represents the case of trying to create a password that has
 * less than 8 characters long
 * 
 * @author sblanco1
 *
 */
public class PasswordTooShort extends Exception {

	/*
	 * Constructor
	 */
	public PasswordTooShort(String msg) {
		super(msg);
	}

}
