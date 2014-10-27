package uy.edu.um.laboratoriotic.business.entities.general;

import uy.edu.um.laboratoriotic.services.valueobject.general.TypeVO;

/**
 * This class represents a type
 * 
 * @author sblanco1
 *
 */
public class Type {

	/*
	 * Attributes of the class
	 */
	private int typeID;
	private String typeCountry, typeSector;
	private boolean type; // true is is country, false if is sector

	/*
	 * Constructors
	 */
	public Type(int oTypeID, String oTypeCountry, String oTypeSector,
			boolean oType) {

		this.typeID = oTypeID;
		this.typeCountry = oTypeCountry;
		this.typeSector = oTypeSector;
		this.type = oType;
	}

	public Type(String oValue) {

		if (type) {
			this.typeCountry = oValue;
		} else {
			this.typeSector = oValue;
		}
	}

	/*
	 * Helping methods
	 */
	public TypeVO toVO() {

		if (type) {
			return new TypeVO(typeID, typeCountry, typeSector, true);
		} else {
			return new TypeVO(typeID, typeCountry, typeSector, false);
		}

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
