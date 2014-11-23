package uy.edu.um.laboratoriotic.services.valueobject.employee;

import java.io.Serializable;

import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This is the entity that we use to travel from the server to the client and
 * backwards
 * 
 * @author sblanco1
 * 
 */
public class EmployeeVO implements Serializable, Comparable<EmployeeVO> {

	/*
	 * Attributes of the class
	 */
	private int employeeID;
	private String iD, name, lastName, userName, password, mail, position,
			workingHour;
	private TypeVO document, location, sector;
	private byte[] profilePicture;
	private boolean status, admin;

	/*
	 * Constructors
	 */

	public EmployeeVO(int oEmployeeID, TypeVO oDocument, String oID, String oName,
			String oLastName, String oUserName, String oPassword,
			TypeVO oLocation, TypeVO oSector, String oMail, String oPosition,
			String oWorkingHour, byte[] oProfilePicture, boolean oStatus, boolean oAdmin) {

		this.employeeID = oEmployeeID;
		this.document = oDocument;
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
		this.admin = oAdmin;

	}
	
	public EmployeeVO(TypeVO oDocument, String oID, String oName, String oLastName,
			String oUserName, String oPassword, TypeVO oLocation,
			TypeVO oSector, String oMail, String oPosition,
			String oWorkingHour, byte[] oProfilePicture, boolean oStatus, boolean oAdmin) {

		this.setDocument(oDocument);
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
		this.setAdmin(oAdmin);

	}

	public EmployeeVO(String oUserName, String oPassword, TypeVO oLocation,
			TypeVO oSector, boolean oStatus) {

		this.userName = oUserName;
		this.password = oPassword;
		this.location = oLocation;
		this.sector = oSector;
		this.status = oStatus;

	}

	public EmployeeVO(String oUserName, String oPassword) {

		this.userName = oUserName;
		this.password = oPassword;

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

	public TypeVO getLocation() {
		return location;
	}

	public void setLocation(TypeVO oLocation) {
		this.location = oLocation;
	}

	public TypeVO getSector() {
		return sector;
	}

	public void setSector(TypeVO oSector) {
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

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] oProfilePicture) {
		this.profilePicture = oProfilePicture;
	}

	public String getWorkingHour() {
		return workingHour;
	}

	public void setWorkingHour(String oWorkingHour) {
		this.workingHour = oWorkingHour;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean oStatus) {
		this.status = oStatus;
	}

	public TypeVO getDocument() {
		return document;
	}

	public void setDocument(TypeVO oDocument) {
		this.document = oDocument;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean oAdmin) {
		this.admin = oAdmin;
	}

	@Override
	/**
	 * This method compares Employees
	 *
	 * @param o
	 * @return 0 employees have same status. 1 if this EmployeeVO status is false. -1 if this EmployeeVO status is true.
	 */
	public int compareTo(EmployeeVO oEmployee) {
		
		int oReturn=0;
		if(this.status==true &&  oEmployee.getStatus()==false){
			
			oReturn = -1;
			
		}else if(this.status==false &&  oEmployee.getStatus()==true){
			
			oReturn = 1;
		}
		
		return oReturn;
	}

}
