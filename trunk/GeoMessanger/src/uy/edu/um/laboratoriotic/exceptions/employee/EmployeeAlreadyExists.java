package uy.edu.um.laboratoriotic.exceptions.employee;

import java.io.Serializable;

/**
 * This exception represents the case of trying to add an employee that already
 * exists on the database
 * 
 * @author sblanco1
 *
 */
public class EmployeeAlreadyExists extends Exception implements Serializable {

	/*
	 * Constructor
	 */
	public EmployeeAlreadyExists(String msg) {
		super(msg);
	}

	public EmployeeAlreadyExists() {
		// TODO Auto-generated constructor stub
	}

}
