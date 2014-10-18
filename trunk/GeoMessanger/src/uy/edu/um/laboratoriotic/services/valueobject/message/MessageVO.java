package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public abstract class MessageVO implements Serializable{

	/*
	 * Attributes of the class
	 */
	private int iD;
	private EmployeeVO sender;
	private HashSet<EmployeeVO> receivers;
	private Date date;

	/*
	 * Constructor
	 */
	public MessageVO(int oID, EmployeeVO oSender, HashSet<EmployeeVO> oReceivers,
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

}
