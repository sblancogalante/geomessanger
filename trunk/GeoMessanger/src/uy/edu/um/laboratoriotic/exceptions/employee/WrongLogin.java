package uy.edu.um.laboratoriotic.exceptions.employee;

import java.io.Serializable;

/**
 * This exception represents the case of a combination of user name and password
 * that does not exist on the database
 * 
 * @author sblanco1
 *
 */
public class WrongLogin extends Exception implements Serializable  {

	/*
	 * Constructor
	 */
	public WrongLogin(String msg) {
		super(msg);
	}

	public WrongLogin() {
		// TODO Auto-generated constructor stub
	}

}
