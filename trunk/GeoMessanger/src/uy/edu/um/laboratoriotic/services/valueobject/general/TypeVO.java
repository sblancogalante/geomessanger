package uy.edu.um.laboratoriotic.services.valueobject.general;

public class TypeVO {

	/*
	 * Attributes of the class
	 */
	private String country, workSector;

	/*
	 * Constructors
	 */
	public TypeVO(String country, String workSector) {

		this.country = country;
		this.workSector = workSector;
	}
	
	public TypeVO(){
		
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
