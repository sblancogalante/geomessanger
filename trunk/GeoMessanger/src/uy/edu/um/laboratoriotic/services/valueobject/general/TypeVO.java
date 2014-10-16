package uy.edu.um.laboratoriotic.services.valueobject.general;

import java.io.Serializable;

/**
 * This is the entity that we use to travel from the server to the client and backwards
 * @author sblanco1
 *
 */
public class TypeVO implements Serializable{

	/*
	 * Attributes of the class
	 */
	private String type, value;
	private int typeID;

	/*
	 * Constructors
	 */
	public TypeVO(int oTypeID, String oType, String oValue) {

		this.typeID = oTypeID;
		this.type = oType;
		this.value = oValue;
	}

	public TypeVO() {

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
