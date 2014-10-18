package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.util.Date;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;

public abstract class MessageVO {

	/*
	 * Attributes of the class
	 */
	private int iD;
	private Employee sender;
	private HashSet<Employee> receivers;
	private Date date;

	/*
	 * Constructor
	 */
	public MessageVO(int oID, Employee oSender, HashSet<Employee> oReceivers,
			Date oDate) {

		this.sender = oSender;
		this.receivers = oReceivers;
		this.date = oDate;
		this.iD = oID;

	}

	/*
	 * Getters & Setters
	 */
	public int getID() {
		return iD;
	}

	public void setID(int oID) {
		this.iD = oID;
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

}
