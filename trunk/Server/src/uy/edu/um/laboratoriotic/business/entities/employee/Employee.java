package uy.edu.um.laboratoriotic.business.entities.employee;

import java.sql.Blob;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This class represents an employee
 * 
 * @author sblanco1
 * 
 */
public class Employee {

	/*
	 * Attributes of the class
	 */
	private int employeeID;
	private String iD, name, lastName, userName, password, location, sector,
			mail, position, workingHour;
	private Blob profilePicture;
	private boolean status;

	/*
	 * Constructors
	 */
	public Employee(int oEmployeeID, String oID, String oName,
			String oLastName, String oUserName, String oPassword,
			String oLocation, String oSector, String oMail, String oPosition,
			String oWorkingHour, Blob oProfilePicture, boolean oStatus) {

		this.employeeID = oEmployeeID;
		this.iD = oID;
		this.name = oName;
		this.lastName = oLastName;
		this.userName = oUserName;
		this.password = oPassword;
		this.location = oLocation;
		this.sector = oSector;
		this.mail = oMail;
		this.position = oPosition;
		this.workingHour = oWorkingHour;
		this.profilePicture = oProfilePicture;
		this.status = oStatus;

	}

	public Employee(String oName, String oLastName, int oEmployeeID,
			String oLocation, String oSector, boolean oStatus) {

		this.name = oName;
		this.lastName = oLastName;
		this.employeeID = oEmployeeID;
		this.location = oLocation;
		this.sector = oSector;
		this.status = oStatus;

	}

	public Employee(int oEmployeeID, String oUserName, String oLocation,
			String oSector, boolean oStatus) {
		// TODO Auto-generated constructor stub

		this.employeeID = oEmployeeID;
		this.userName = oUserName;
		this.location = oLocation;
		this.sector = oSector;
		this.status = oStatus;
	}

	/*
	 * Auxiliary methods
	 */
	public EmployeeVO toVO() {

		return new EmployeeVO(employeeID, iD, name, lastName, userName,
				password, location, sector, mail, position, workingHour,
				profilePicture, status);

	}

	/*
	 * Getters & setters
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int oEmployeeID) {
		this.employeeID = oEmployeeID;
	}

	public String getID() {
		return iD;
	}

	public void setID(String oID) {
		this.iD = oID;
	}

	public String getName() {
		return name;
	}

	public void setName(String oName) {
		this.name = oName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String oLastName) {
		this.lastName = oLastName;
	}

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

	public String getLocation() {
		return location;
	}

	public void setLocation(String oLocation) {
		this.location = oLocation;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String oSector) {
		this.sector = oSector;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String oMail) {
		this.mail = oMail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String oPosition) {
		this.position = oPosition;
	}

	public String getWorkingHour() {
		return workingHour;
	}

	public void setWorkingHour(String oWorkingHour) {
		this.workingHour = oWorkingHour;
	}
	
	public Blob getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Blob oProfilePicture) {
		this.profilePicture = oProfilePicture;
	}	

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean oStatus) {
		this.status = oStatus;
	}

}
