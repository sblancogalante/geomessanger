package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.io.Serializable;
import java.sql.Timestamp;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This is the entity that we use to travel from the server to the client and
 * backwards
 * 
 * @author sblanco1
 *
 */
public class TextMessageVO extends MessageVO implements Serializable {

	/*
	 * Attributes of the class
	 */
	private String textMessage;
	private EmployeeVO sender;
	private EmployeeVO receiver;
	private Timestamp date;
	private boolean isConference;

	/*
	 * Constructor
	 */
	public TextMessageVO(int oIDMessage, String oTextMessage,
			EmployeeVO oSender, EmployeeVO oReceiver, Timestamp oDate,
			boolean oIsConference) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.textMessage = oTextMessage;
		this.sender = oSender;
		this.receiver = oReceiver;
		this.date = oDate;
		this.isConference = oIsConference;

	}

	/*
	 * Getters & Setters
	 */

	public String getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String oTextMessage) {
		this.textMessage = oTextMessage;
	}

	public EmployeeVO getSender() {
		return sender;
	}

	public void setSender(EmployeeVO oSender) {
		this.sender = oSender;
	}

	public EmployeeVO getReceiver() {
		return receiver;
	}

	public void setReceiver(EmployeeVO oReceiver) {
		this.receiver = oReceiver;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp oDate) {
		this.date = oDate;
	}

	public boolean getIsConference() {
		return isConference;
	}

	public void setIsconference(boolean oIsConference) {
		this.isConference = oIsConference;
	}

}
