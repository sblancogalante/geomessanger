package uy.edu.um.laboratoriotic.business.entities.message;

import java.sql.Blob;
import java.sql.Timestamp;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;

/**
 * This class represents a message that has an attachment
 * 
 * @author sblanco1
 * 
 */
public class FileMessage extends Message {

	/*
	 * Attributes of the class
	 */
	private Blob file;
	private String name;
	private Employee sender;
	private Employee receiver;
	private Timestamp date;	

	/*
	 * Constructor
	 */
	public FileMessage(int oIDMessage, Blob oFile, String oName,
			Employee oSender, Employee oReceiver, Timestamp oDate) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.file = oFile;
		this.name = oName;
		this.sender = oSender;
		this.receiver = oReceiver;
		this.date = oDate;
	
	}

	/*
	 * Helping methods
	 */
	public FileMessageVO toVO() {

		return new FileMessageVO(this.getIDMessage(), file, name,
				sender.toVO(), receiver.toVO(), date);
	}

	/*
	 * Getters & Setters
	 */
	public Blob getFileMessage() {
		return file;
	}

	public void setFileMessage(Blob oFileMessage) {
		this.file = oFileMessage;
	}

	public String getFileMessageName() {
		return name;
	}

	public void setFileMessageName(String oName) {
		this.name = oName;
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