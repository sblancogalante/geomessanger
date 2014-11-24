package uy.edu.um.laboratoriotic.exceptions.employee;

import java.io.Serializable;

/**
 * This exception represents the case of adding an employee without filling all
 * the obligatory fields
 * 
 * @author sblanco1
 *
 */
public class MissingArguments extends Exception implements Serializable  {

	/*
	 * Constructor
	 */
	public MissingArguments(String msg) {
		super(msg);
	}

	public MissingArguments() {
		// TODO Auto-generated constructor stub
	}

}
