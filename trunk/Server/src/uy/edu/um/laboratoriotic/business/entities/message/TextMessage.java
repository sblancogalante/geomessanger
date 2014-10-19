package uy.edu.um.laboratoriotic.business.entities.message;

import java.util.Date;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
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
	private HashSet<Employee> receivers;
	private Date date;
	private boolean isConference;

	/*
	 * Constructor
	 */
	public TextMessage(int oIDMessage, String oTextMessage, Employee oSender,
			HashSet<Employee> oReceivers, Date oDate, boolean oIsConference) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.textMessage = oTextMessage;
		this.sender = oSender;
		this.receivers = oReceivers;
		this.date = oDate;
		this.isConference = oIsConference;

	}

	/*
	 * Helping methods
	 */
	public TextMessageVO toVO() {

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

		return new TextMessageVO(this.getIDMessage(), textMessage, sender.toVO(),
				oReceiversToReturn, date, isConference);
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

	public HashSet<Employee> getReceivers() {
		return receivers;
	}

	public void setReceivers(HashSet<Employee> oReceivers) {
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
