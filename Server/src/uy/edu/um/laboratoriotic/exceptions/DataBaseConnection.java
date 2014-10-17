package uy.edu.um.laboratoriotic.exceptions;

/**
 * This class represents a problem with the connection with the database
 * 
 * @author sblanco1
 *
 */
public class DataBaseConnection extends Exception{

	/*
	 * Constructor
	 */
	public DataBaseConnection(String msg) {
		super(msg);
	}

}
