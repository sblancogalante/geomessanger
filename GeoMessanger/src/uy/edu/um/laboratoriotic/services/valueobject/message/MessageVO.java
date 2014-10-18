package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.io.Serializable;

/**
 * This is the entity that we use to travel from the server to the client and
 * backwards
 * 
 * @author sblanco1
 *
 */
public abstract class MessageVO implements Serializable{

	/*
	 * Attributes of the class
	 */
	private int iDMessage;

	/*
	 * Constructor
	 */
	public MessageVO(int oIDMessage) {
		this.iDMessage = oIDMessage;
	}

	/*
	 * Getters & Setters
	 */
	public int getIDMessage() {
		return iDMessage;
	}

	public void setIDMessage(int oIDMessage) {
		this.iDMessage = oIDMessage;
	}

}
