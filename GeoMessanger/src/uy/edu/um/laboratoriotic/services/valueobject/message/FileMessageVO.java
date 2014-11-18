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
public class FileMessageVO extends MessageVO implements Serializable {

	/*
	 * Attributes of the class
	 */
	private byte[] file;
	private String name;
	private EmployeeVO sender;
	private EmployeeVO receiver;
	private Timestamp date;

	/*
	 * Constructor
	 */
	public FileMessageVO(int oIDMessage, byte[] oFile, String oName,
			EmployeeVO oSender, EmployeeVO oReceiver, Timestamp oDate) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.file = oFile;
		this.name = oName;
		this.sender = oSender;
		this.receiver = oReceiver;
		this.date = oDate;

	}

	public FileMessageVO(int oIDMessage, byte[] oFile, String oName,
			EmployeeVO oSender, EmployeeVO oReceiver) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.file = oFile;
		this.name = oName;
		this.sender = oSender;
		this.receiver = oReceiver;

	}

	/*
	 * Getters & Setters
	 */
	public byte[] getFileMessage() {
		return file;
	}

	public void setFileMessage(byte[] oFileMessage) {
		this.file = oFileMessage;
	}

	public String getFileMessageName() {
		return name;
	}

	public void setFileMessageName(String oName) {
		this.name = oName;
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

}