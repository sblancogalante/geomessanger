package uy.edu.um.laboratoriotic.exceptions;

import java.io.Serializable;

/**
 * This exception represents a problem on the connection with the database
 * 
 * @author sblanco1
 *
 */
public class DataBaseConnection extends Exception implements Serializable  {

	/*
	 * Constructor
	 */
	public DataBaseConnection(String msg) {
		super(msg);
	}

}
