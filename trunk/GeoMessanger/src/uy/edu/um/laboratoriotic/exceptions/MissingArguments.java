package uy.edu.um.laboratoriotic.exceptions;

/**
 * This exception represents the case of adding an employee without filling all
 * the obligatory fields
 * 
 * @author sblanco1
 *
 */
public class MissingArguments extends Exception {

	/*
	 * Constructor
	 */
	public MissingArguments(String msg) {
		super(msg);
	}

}
