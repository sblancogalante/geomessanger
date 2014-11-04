package uy.edu.um.laboratoriotic.services.valueobject.general;

import java.io.Serializable;

/**
 * This is the entity that we use to travel from the server to the client and
 * backwards
 * 
 * @author sblanco1
 *
 */
public class TypeVO implements Serializable {

	/*
	 * Attributes of the class
	 */
	private int typeID;
	private String type, value;

	/*
	 * Constructors
	 */
	public TypeVO(int oTypeID, String oType, String oValue) {

		this.typeID = oTypeID;
		this.type = oType;
		this.value = oValue;		
	}	
	
	public TypeVO(String oType, String oValue) {
		
		this.type = oType;
		this.value = oValue;		
	}	

	/*
	 * Getters & setters
	 */
	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int oTypeID) {
		this.typeID = oTypeID;
	}

	public String getType() {
		return type;
	}

	public void setTypeCountry(String oType) {
		this.type = oType;
	}

	public String getValue() {
		return value;
	}

	public void setTypeSector(String oValue) {
		this.value = oValue;
	}
	
}