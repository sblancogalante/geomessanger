package uy.edu.um.laboratoriotic.exceptions;

/**
 * This exception represents the case of trying to do some action but sending
 * the incorrect arguments to do so
 * 
 * @author sblanco1
 *
 */
public class WrongArguments extends Exception {

	/*
	 * Constructor
	 */
	public WrongArguments(String msg) {
		super(msg);
	}
}
