package uy.edu.um.laboratoriotic.business.entities.general;

public class Type {

	/*
	 * Attributes of the class
	 */
	private String country, workSector;

	/*
	 * Constructors
	 */
	public Type(String country, String workSector) {

		this.country = country;
		this.workSector = workSector;
	}
	
	public Type(){
		
	}

	/*
	 * Getters & setters
	 */
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWorkSector() {
		return workSector;
	}

	public void setWorkSector(String workSector) {
		this.workSector = workSector;
	}	
	
}
