package uy.edu.um.laboratoriotic.exceptions.employee;

/**
 * This exception represents the case of a combination of user name and password
 * that does not exist on the database
 * 
 * @author sblanco1
 *
 */
public class WrongLogin extends Exception {

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
