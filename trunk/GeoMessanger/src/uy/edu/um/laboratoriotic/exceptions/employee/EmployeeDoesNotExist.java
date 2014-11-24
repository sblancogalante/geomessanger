package uy.edu.um.laboratoriotic.exceptions.employee;

import java.io.Serializable;

/**
 * This exception represents the case of trying to remove an employee that does
 * not exist on the database
 * 
 * @author sblanco1
 *
 */
public class EmployeeDoesNotExist extends Exception implements Serializable  {

	/*
	 * Constructor
	 */
	public EmployeeDoesNotExist(String msg) {
		super(msg);
	}

	public EmployeeDoesNotExist() {
		// TODO Auto-generated constructor stub
	}
}
