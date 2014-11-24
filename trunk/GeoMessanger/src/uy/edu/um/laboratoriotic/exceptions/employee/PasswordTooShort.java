package uy.edu.um.laboratoriotic.exceptions.employee;

import java.io.Serializable;

/**
 * This exception represents the case of trying to create a password that has
 * less than 8 characters long
 * 
 * @author sblanco1
 *
 */
public class PasswordTooShort extends Exception implements Serializable  {

	/*
	 * Constructor
	 */
	public PasswordTooShort(String msg) {
		super(msg);
	}

	public PasswordTooShort() {
		// TODO Auto-generated constructor stub
	}

}
