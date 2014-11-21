package uy.edu.um.laboratoriotic.business.entities.employee;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
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
	private String iD, name, lastName, userName, password, mail, position,
			workingHour;
	private Type document, location, sector;
	private byte[] profilePicture;
	private boolean status, admin;

	/*
	 * Constructors
	 */
	public Employee(int oEmployeeID, Type oDocument, String oID, String oName,
			String oLastName, String oUserName, String oPassword,
			Type oLocation, Type oSector, String oMail, String oPosition,
			String oWorkingHour, byte[] oProfilePicture, boolean oStatus,
			boolean oAdmin) {

		this.employeeID = oEmployeeID;
		this.setDocument(oDocument);
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
		this.setAdmin(oAdmin);

	}

	public Employee(Type oDocument, String oID, String oName, String oLastName,
			String oUserName, String oPassword, Type oLocation, Type oSector,
			String oMail, String oPosition, String oWorkingHour,
			byte[] oProfilePicture, boolean oStatus, boolean oAdmin) {

		this.document = oDocument;
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
		this.admin = oAdmin;

	}

	public Employee(String oUserName, String oPassword, Type oLocation,
			Type oSector, boolean oStatus) {

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

		return new EmployeeVO(employeeID, document.toVO(), iD, name, lastName,
				userName, password, location.toVO(), sector.toVO(), mail,
				position, workingHour, profilePicture, status, admin);

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

	public Type getDocument() {
		return document;
	}

	public void setDocument(Type document) {
		this.document = document;
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

	public Type getLocation() {
		return location;
	}

	public void setLocation(Type oLocation) {
		this.location = oLocation;
	}

	public Type getSector() {
		return sector;
	}

	public void setSector(Type oSector) {
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

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] oProfilePicture) {
		this.profilePicture = oProfilePicture;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean oStatus) {
		this.status = oStatus;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}
