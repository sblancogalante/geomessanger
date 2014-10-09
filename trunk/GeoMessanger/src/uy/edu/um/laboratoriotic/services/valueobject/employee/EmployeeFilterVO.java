package uy.edu.um.laboratoriotic.services.valueobject.employee;

/**
 * This class represents the filters that we are using to get the results of the
 * search
 * 
 * @author sblanco1
 * 
 */
public class EmployeeFilterVO {

	/*
	 * Attributes of the class
	 */
	private boolean status;
	private String location, sector, userName;

	/*
	 * Constructors
	 */
	public EmployeeFilterVO(String oUserName, String oLocation, String oSector,
			boolean oStatus) {

		this.userName = oUserName;
		this.location = oLocation;
		this.sector = oSector;
		this.status = oStatus;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

}
