package uy.edu.um.laboratoriotic.business.entities.employee;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;
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
	public Employee(String oID, String oName, String oLastName,
			String oUserName, String oPassword, String oLocation,
			String oSector, String oMail, String oPosition,
			String oWorkingHour, Blob oProfilePicture, boolean oStatus) {

		this.iD = oID;
		this.name = oName;
		this.lastName = oLastName;
		this.userName = oUserName;
		this.password = this.hashEncriptation(oPassword);
		this.location = oLocation;
		this.sector = oSector;
		this.mail = oMail;
		this.position = oPosition;
		this.workingHour = oWorkingHour;
		this.profilePicture = oProfilePicture;
		this.status = oStatus;

	}

	public Employee(String oUserName, String oPassword, String oLocation,
			String oSector, boolean oStatus) {

		this.userName = oUserName;
		this.password = this.hashEncriptation(oPassword);
		this.location = oLocation;
		this.sector = oSector;
		this.status = oStatus;

	}

	public Employee(String oUserName, String oPassword) {

		this.userName = oUserName;
		this.password = this.hashEncriptation(oPassword);

	}

	/*
	 * Helping methods
	 */
	public EmployeeVO toVO() {

		return new EmployeeVO(iD, name, lastName, userName, password, location,
				sector, mail, position, workingHour, profilePicture, status);

	}

	public EmployeeFilterVO toFilterVO() {

		return new EmployeeFilterVO(userName, password);
	}

	public String hashEncriptation(String oPassword) {

		String newPassword = null;

		try {

			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(oPassword.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			newPassword = hash.toString(16);

		} catch (NoSuchAlgorithmException e) {
			// No hacer nada
		}

		return newPassword;
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
		return this.hashEncriptation(password);
	}

	public void setPassword(String oPassword) {
		this.password = hashEncriptation(oPassword);
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
