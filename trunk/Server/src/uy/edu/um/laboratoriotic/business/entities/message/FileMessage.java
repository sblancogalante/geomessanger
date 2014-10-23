package uy.edu.um.laboratoriotic.business.entities.message;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
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
	private HashSet<Employee> receivers;
	private Timestamp date;
	private boolean isConference;

	/*
	 * Constructor
	 */
	public FileMessage(int oIDMessage, Blob oFile, String oName,
			Employee oSender, HashSet<Employee> oReceivers, Timestamp oDate,
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
	 * Helping methods
	 */
	public FileMessageVO toVO() {

		HashSet<EmployeeVO> oReceiversToReturn = new HashSet<>();

		for (Employee iEmployee : receivers) {
			EmployeeVO oEmployeeVO = new EmployeeVO(iEmployee.getEmployeeID(),
					iEmployee.getID(), iEmployee.getName(),
					iEmployee.getLastName(), iEmployee.getUserName(),
					iEmployee.getPassword(), iEmployee.getLocation(),
					iEmployee.getSector(), iEmployee.getMail(),
					iEmployee.getPosition(), iEmployee.getWorkingHour(),
					iEmployee.getProfilePicture(), iEmployee.getStatus());
			oReceiversToReturn.add(oEmployeeVO);
		}

		return new FileMessageVO(this.getIDMessage(), file, name,
				sender.toVO(), oReceiversToReturn, date, isConference);
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

	public HashSet<Employee> getReceivers() {
		return receivers;
	}

	public void setReceivers(HashSet<Employee> oReceivers) {
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
