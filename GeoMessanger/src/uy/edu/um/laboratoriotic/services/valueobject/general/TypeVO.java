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
	private String typeCountry, typeSector;
	private boolean type; // true is is country, false if is sector

	/*
	 * Constructors
	 */
	public TypeVO(int oTypeID, String oTypeCountry, String oTypeSector,
			boolean oType) {

		this.typeID = oTypeID;
		this.typeCountry = oTypeCountry;
		this.typeSector = oTypeSector;
		this.type = oType;
	}

	public TypeVO() {

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

	public String getTypeCountry() {
		return typeCountry;
	}

	public void setTypeCountry(String oTypeCountry) {
		this.typeCountry = oTypeCountry;
	}

	public String getTypeSector() {
		return typeSector;
	}

	public void setTypeSector(String oTypeSector) {
		this.typeSector = oTypeSector;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean oType) {
		this.type = oType;
	}

}