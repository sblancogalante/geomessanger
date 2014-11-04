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
	private String type, value;	

	/*
	 * Constructors
	 */
	public Type(int oTypeID, String oType, String oValue) {

		this.typeID = oTypeID;
		this.type = oType;
		this.value = oValue;		
	}
	
	public Type(String oType, String oValue) {

		this.type = oType;
		this.value = oValue;		
	}
	
	/*
	 * Helping methods
	 */
	public TypeVO toVO() {

		return new TypeVO(typeID, type, value);

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

	public void setType(String oType) {
		this.type = oType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String oValue) {
		this.value = oValue;
	}	

}