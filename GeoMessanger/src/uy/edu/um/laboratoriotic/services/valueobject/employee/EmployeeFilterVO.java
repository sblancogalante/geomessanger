package uy.edu.um.laboratoriotic.services.valueobject.employee;

import java.io.Serializable;

/**
 * This class represents the filters that we are using to get the results of the
 * search
 * 
 * @author sblanco1
 * 
 */
public class EmployeeFilterVO implements Serializable {

	/*
	 * Attributes of the class
	 */
	private String userName, password;

	/*
	 * Constructors
	 */
	public EmployeeFilterVO(String oUserName, String oPassword) {

		this.userName = oUserName;
		this.password = oPassword;

	}

	/*
	 * Getters & setters
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String oUserName) {
		this.userName = oUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String oPassword) {
		this.password = oPassword;
	}

}
