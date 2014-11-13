package uy.edu.um.laboratoriotic.business.entities.message;

import java.sql.Timestamp;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

/**
 * This class represents a message that only contains text
 * 
 * @author sblanco1
 * 
 */
public class TextMessage extends Message {

	/*
	 * Attributes of the class
	 */
	private String textMessage;
	private Employee sender;
	private Employee receiver;
	private Timestamp date;

	/*
	 * Constructor
	 */
	public TextMessage(int oIDMessage, String oTextMessage, Employee oSender,
			Employee oReceiver, Timestamp oDate) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.textMessage = oTextMessage;
		this.sender = oSender;
		this.receiver = oReceiver;
		this.date = oDate;

	}

	public TextMessage(int oIDMessage, String oTextMessage, Employee oSender,
			Employee oReceiver) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.textMessage = oTextMessage;
		this.sender = oSender;
		this.receiver = oReceiver;

	}

	/*
	 * Helping methods
	 */
	public TextMessageVO toVO() {

		return new TextMessageVO(this.getIDMessage(), textMessage,
				sender.toVO(), receiver.toVO());
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

	public Employee getSender() {
		return sender;
	}

	public void setSender(Employee oSender) {
		this.sender = oSender;
	}

	public Employee getReceiver() {
		return receiver;
	}

	public void setReceiver(Employee oReceiver) {
		this.receiver = oReceiver;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp oDate) {
		this.date = oDate;
	}

}
