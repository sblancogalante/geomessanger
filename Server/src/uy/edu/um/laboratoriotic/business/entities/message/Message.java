package uy.edu.um.laboratoriotic.business.entities.message;

/**
 * This class is the father of what represents both the normal message(only
 * text) and file message
 * 
 * @author sblanco1
 * 
 */
public abstract class Message {

	/*
	 * Attributes of the class
	 */
	private int iDMessage;

	/*
	 * Constructor
	 */
	public Message(int oIDMessage) {
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
