package uy.edu.um.laboratoriotic.services;

import java.util.Date;

public class EmployeeVO {

	/*
	 * Attributes of the class
	 */
	private int employeeID;
	private String iD, name, lastName, userName, password, location, sector,
			mail, position;
	private byte profilePicture;
	private Date workingHour;
	private boolean status;

	/*
	 * Constructors	
	 */
	public EmployeeVO(int oEmployeeID, String oID, String oName, String oLastName,
			String oUserName, String oPassword, String oLocation, String oSector,
			String oMail, String oPosition, byte oProfilePicture,
			Date oWorkingHour, boolean oStatus) {
		
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
		this.profilePicture = oProfilePicture;
		this.workingHour = oWorkingHour;
		this.status = oStatus;
		
	}
	
	/*
	 * Getters & setters
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public byte getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Date getWorkingHour() {
		return workingHour;
	}

	public void setWorkingHour(Date workingHour) {
		this.workingHour = workingHour;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
}