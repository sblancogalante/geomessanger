package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public class TextMessageVO extends MessageVO implements Serializable{

	/*
	 * Attributes of the class
	 */
	private boolean isConference;
	private String text;

	/*
	 * Constructor
	 */
	public TextMessageVO(String oText, boolean oIsConference, int oID,
			EmployeeVO oSender, HashSet<EmployeeVO> oReceiver, Date date) {

		super(oID, oSender, oReceiver, date);

		this.text = oText;
		this.isConference = oIsConference;

	}

	/*
	 * MessageVO implementation methods
	 */
	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return super.getDate();
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		super.setDate(date);
	}

	/*
	 * Getters & Setters
	 */
	public boolean isConference() {
		return isConference;
	}

	public void setConference(boolean isConference) {
		this.isConference = isConference;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
