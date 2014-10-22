package uy.edu.um.laboratoriotic.services.valueobject.message;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

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
	private Timestamp date;
	private boolean isConference;

	/*
	 * Constructor
	 */
	public FileMessageVO(int oIDMessage, Blob oFile, String oName,
			Timestamp oDate, boolean oIsConference) {
		// TODO Auto-generated constructor stub
		super(oIDMessage);

		this.file = oFile;
		this.name = oName;
		this.date = oDate;
		this.isConference = oIsConference;
	}

	/*
	 * Getters & Setters
	 */
	public Blob getFile() {
		return file;
	}

	public void setFile(Blob oFile) {
		this.file = oFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String oName) {
		this.name = oName;
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

	public void setConference(boolean oIsConference) {
		this.isConference = oIsConference;
	}

}
