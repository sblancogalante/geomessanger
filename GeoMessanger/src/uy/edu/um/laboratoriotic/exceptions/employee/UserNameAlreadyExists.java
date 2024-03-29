package uy.edu.um.laboratoriotic.exceptions.employee;

import java.io.Serializable;

/**
 * This exception represents the case of trying to add an employee with the same
 * user name of another that already exists on the database
 * 
 * @author sblanco1
 *
 */
public class UserNameAlreadyExists extends Exception implements Serializable  {

	/*
	 * Constructor
	 */
	public UserNameAlreadyExists(String msg) {
		super(msg);
	}

	public UserNameAlreadyExists() {
		// TODO Auto-generated constructor stub
	}

}
