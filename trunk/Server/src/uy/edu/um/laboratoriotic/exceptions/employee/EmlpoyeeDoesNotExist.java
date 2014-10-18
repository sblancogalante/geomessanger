package uy.edu.um.laboratoriotic.exceptions.employee;

/**
 * This exception represents the case of trying to remove an employee that does
 * not exist on the database
 * 
 * @author sblanco1
 *
 */
public class EmlpoyeeDoesNotExist extends Exception {

	/*
	 * Constructor
	 */
	public EmlpoyeeDoesNotExist(String msg) {
		super(msg);
	}
}
