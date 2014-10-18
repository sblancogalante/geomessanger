package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.io.Serializable;
import java.util.Date;

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
	private byte[] file;
	private String name;
	private Date date;
	private boolean isConference;

	/*
	 * Constructor
	 */
	public FileMessageVO(int oIDMessage, byte[] oFile, String oName,
			Date oDate, boolean oIsConference) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.setFile(oFile);
		this.setName(oName);
		this.setDate(oDate);
		this.setConference(oIsConference);
	}

	/*
	 * Getters & Setters
	 */
	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] oFile) {
		this.file = oFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String oName) {
		this.name = oName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date oDate) {
		this.date = oDate;
	}

	public boolean isConference() {
		return isConference;
	}

	public void setConference(boolean oIsConference) {
		this.isConference = oIsConference;
	}

}
