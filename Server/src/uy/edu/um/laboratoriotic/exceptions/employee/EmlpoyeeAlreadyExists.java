package uy.edu.um.laboratoriotic.exceptions.employee;

/**
 * This exception represents the case of trying to add an employee that already
 * exists on the database
 * 
 * @author sblanco1
 *
 */
public class EmlpoyeeAlreadyExists extends Exception {

	/*
	 * Constructor
	 */
	public EmlpoyeeAlreadyExists(String msg) {
		super(msg);
	}

}
