package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This is the entity that we use to travel from the server to the client and
 * backwards
 * 
 * @author sblanco1
 * 
 */
public class FileMessageVO extends MessageVO implements Serializable{

	/*
	 * Attributes of the class
	 */
	private Blob file;
	private String name;
	private EmployeeVO sender;
	private HashSet<EmployeeVO> receivers;
	private Timestamp date;
	private boolean isConference;

	/*
	 * Constructor
	 */
	public FileMessageVO(int oIDMessage, Blob oFile, String oName,
			EmployeeVO oSender, HashSet<EmployeeVO> oReceivers, Timestamp oDate,
			boolean oIsConference) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.file = oFile;
		this.name = oName;
		this.sender = oSender;
		this.receivers = oReceivers;
		this.date = oDate;
		this.isConference = oIsConference;
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
