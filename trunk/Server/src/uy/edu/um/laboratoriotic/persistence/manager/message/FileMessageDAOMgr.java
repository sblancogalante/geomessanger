package uy.edu.um.laboratoriotic.persistence.manager.message;

import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.management.message.FileMessageDAOMgt;

/**
 * This is the implementation of FileMessageDAOMgt
 * 
 * @author sblanco1
 * 
 */
public class FileMessageDAOMgr implements FileMessageDAOMgt {

	/*
	 * Attributes of the class
	 */
	private static FileMessageDAOMgr instance = null;
	private static final String DRIVER_JDBC = "org.hsqldb.jdbc.JDBCDriver";
	private static final String URL_MEM_JDBC = "jdbc:hsqldb:mem:Server";
	private static final String CREATE_TABLE_FILE_MESSAGES = "CREATE TABLE FileMessages (fileMessageID int PRIMARY KEY, file BLOB, employeeID int NOT NULL FOREIGN KEY REFERENCES Employees, date DATE NOT NULL, isConference boolean NOT NULL)";
	private static final String CREATE_TABLE_FILES_EMPLOYEES = "CREATE TABLE FilesEmployees (fileMessageID int FOREIGN KEY REFERENCES FileMessages, EmployeeID int FOREIGN KEY REFERENCES Employees)";;

	private static int identifierNumber = 0;
	
	/*
	 * Constructor
	 */
	private FileMessageDAOMgr() {

	}

	public static FileMessageDAOMgr getInstance() {

		if (instance == null) {
			instance = new FileMessageDAOMgr();
		}

		return instance;

	}

	@Override
	public void addFileMessage(FileMessage oFileMessage)
			throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		Connection oConnection = null;
		PreparedStatement oPrepStatement = null;
		Statement oStatement = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			
			int sID = FileMessageDAOMgr.identifierNumber++;
			Blob sFile = oFileMessage.getFileMessage();
			int sIDSender = oFileMessage.getSender().getEmployeeID();
			//FIXME
			Timestamp sDate = new Timestamp(System.currentTimeMillis());			
			boolean sIsConf = oFileMessage.getIsConference();
			
			String sInsert1 = "INSERT INTO FileMessages (fileMessageID, text, employeeID, date, isConference) VALUES (?,?,?,?,?)";
//					+ sID + ","	+ sIDSender	+ "," + sDate + ",'"
//					+ sFile	+ "'," + sConf + ")";

			oPrepStatement= oConnection.prepareStatement(sInsert1);

			oPrepStatement.setInt(1, sID);
			oPrepStatement.setBlob(2, sFile);
			oPrepStatement.setInt(3, sIDSender);
			oPrepStatement.setTimestamp(4, sDate);
			oPrepStatement.setBoolean(5, sIsConf);

			System.out.println("Se agrego con exito a la tabla de mensajes:"
					+ sID + "que con el texto: " + sFile + "enviado por "
					+ sIDSender + " , con la fecha " + sDate);

			

			oPrepStatement.execute();

			String sInsert2 = "INSERT INTO FilesEmployees (fileMessageID,EmployeeID) VALUES ("
					+ sID + "," + sIDSender + ")";

			oStatement = oConnection.createStatement();
			
			oStatement.execute(sInsert2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RemoteException();
		} finally {
			if (oConnection != null) {
				try {
					oConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

	@Override
	public void createTable() throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		Connection oConnection = null;
		Statement oStatement;
		Statement oStatement2;
		boolean bValue = false;
		boolean bValue2 = false;

		oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

		try {

			oStatement = oConnection.createStatement();
			oStatement2 = oConnection.createStatement();
			bValue = oStatement.execute(CREATE_TABLE_FILE_MESSAGES);
			bValue2 = oStatement2.execute(CREATE_TABLE_FILES_EMPLOYEES);

			if (!bValue && !bValue2) {
				System.out.println("Se ejecuto con exito");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RemoteException();

		} finally {
			if (oConnection != null) {
				try {
					oConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Helping methods
	 */
	/**
	 * This method establishes the connection with the data base
	 * 
	 * @param oDBDriver
	 * @param dBDirection
	 * @return
	 */
	private Connection connect(String oDBDriver, String dBDirection) {

		Connection oResult = null;

		try {

			Class.forName(oDBDriver);
			oResult = DriverManager.getConnection(dBDirection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return oResult;
	}

}
