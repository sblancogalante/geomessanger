package uy.edu.um.laboratoriotic.persistence.manager.message;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.management.message.TextMessageDAOMgt;

/**
 * This is the implementation of TextMessageDAOMgt
 * 
 * @author sblanco1
 * 
 */
public class TextMessageDAOMgr implements TextMessageDAOMgt {

	/*
	 * Attributes of the class
	 */
	private static TextMessageDAOMgr instance = null;
	private static final String DRIVER_JDBC = "org.hsqldb.jdbc.JDBCDriver";
	private static final String URL_MEM_JDBC = "jdbc:hsqldb:mem:Server";
	private static final String CREATE_TABLE_TEXT_MESSAGES = "CREATE TABLE TextMessages (textMessageID int IDENTITY NOT NULL, text VARCHAR(300), employeeID int NOT NULL FOREIGN KEY REFERENCES Employees, date TIMESTAMP NOT NULL)";

	/*
	 * Constructor
	 */
	private TextMessageDAOMgr() {

	}

	/*
	 * Methods
	 */
	public static TextMessageDAOMgr getInstance() {

		if (instance == null) {
			instance = new TextMessageDAOMgr();
		}

		return instance;

	}

	/*
	 * Management implementation methods
	 */
	@Override
	public void addTextMessage(TextMessage oTextMessage)
			throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		Connection oConnection = null;
		PreparedStatement oPrepStatement = null;
		PreparedStatement oPrepStatement2 = null;
		
		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			
			String sText = oTextMessage.getTextMessage();
			int sIDSender = oTextMessage.getSender().getEmployeeID();
			int sIDReceiver = oTextMessage.getReceiver().getEmployeeID();
			// FIXME
			Timestamp sDate = new Timestamp(System.currentTimeMillis());			

			String sInsert1 = "INSERT INTO TextMessages (text, employeeID, date) VALUES (?,?,?)";
			String sInsert2 = "INSERT INTO TextMessages (text, employeeID, date) VALUES (?,?,?)";
			
			oPrepStatement = oConnection.prepareStatement(sInsert1);
			
			oPrepStatement.setString(1, sText);
			oPrepStatement.setInt(2, sIDSender);			
			oPrepStatement.setTimestamp(3, sDate);	
			
			oPrepStatement.execute();
			
			oPrepStatement2 = oConnection.prepareStatement(sInsert2);
			
			oPrepStatement2.setString(1, sText);
			oPrepStatement2.setInt(2, sIDReceiver);			
			oPrepStatement2.setTimestamp(3, sDate);		

			oPrepStatement2.execute();	
			
			System.out.println("Se agrego con exito a la tabla de mensajes con el texto: " + sText + " enviado por "
					+ sIDSender + " para " + sIDReceiver + ", con la fecha "
					+ sDate);

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
	public ArrayList<TextMessage> getTextMessages(Employee oSender,
			Employee oReceiver) throws RemoteException {
		// TODO Auto-generated method stub

		ArrayList<TextMessage> oList = new ArrayList<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			ResultSet oResultSet = null;
			String sQuery = null;

			sQuery = "SELECT DISTINCT e.employeeID, e.iD, e.name, e.lastName, e.location, e.sector, e.position, tm.textMessageID, tm.text, tm.date"
					+ " FROM Employees e, TextMessages tm"
					+ "WHERE tm.employeeID = e.employeeID AND tm.employeeID = "
					+ oSender.getEmployeeID()
					+ " UNION "
					+ "SELECT DISTINCT e.employeeID, e.iD, e.name, e.lastName, e.location, e.sector, e.position, tm.textMessageID, tm.text, tm.date"
					+ " FROM Employees e, TextMessages tm, MessagesEmployees m "
					+ "WHERE tm.employeeID = e.employeeID AND tm.employeeID = "
					+ oReceiver.getEmployeeID()
					+ " ORDER BY tm.date ASC;";
			
			oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sID = oResultSet.getInt(8);
				String sTextMessage = oResultSet.getString(9);
				Employee sSender = oSender;
				Employee sReceiver = oReceiver;
				Timestamp sDate = oResultSet.getTimestamp(10);				

				TextMessage oTextMessages = new TextMessage(sID, sTextMessage,
						sSender, sReceiver, sDate);

				oList.add(oTextMessages);
			}

			oResultSet.close();

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

		return oList;
	}

	@Override
	public void createTable() throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		Connection oConnection = null;
		Statement oStatement;		
		boolean bValue = false;		

		oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

		try {

			oStatement = oConnection.createStatement();			
			bValue = oStatement.execute(CREATE_TABLE_TEXT_MESSAGES);			

			if (!bValue) {
				System.out.println("Se ejecuto con exito");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// throw new RemoteException();
			e.printStackTrace();
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
