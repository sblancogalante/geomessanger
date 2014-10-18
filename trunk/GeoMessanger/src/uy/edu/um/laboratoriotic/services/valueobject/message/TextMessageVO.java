package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

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
	private HashSet<EmployeeVO> receivers;
	private Date date;
	private boolean isConference;

	/*
	 * Constructor
	 */
	public TextMessageVO(int oIDMessage, String oTextMessage,
			EmployeeVO oSender, HashSet<EmployeeVO> oReceivers, Date oDate,
			boolean oIsConference) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.textMessage = oTextMessage;
		this.sender = oSender;
		this.receivers = oReceivers;
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

	public HashSet<EmployeeVO> getReceivers() {
		return receivers;
	}

	public void setReceivers(HashSet<EmployeeVO> oReceivers) {
		this.receivers = oReceivers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date oDate) {
		this.date = oDate;
	}

	public boolean getIsConference() {
		return isConference;
	}

	public void setIsconference(boolean oIsConference) {
		this.isConference = oIsConference;
	}

}
