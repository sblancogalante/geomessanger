package uy.edu.um.laboratoriotic.persistence.manager.message;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashSet;

import org.junit.FixMethodOrder;

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
	private static final String CREATE_TABLE_TEXT_MESSAGES = "CREATE TABLE TextMessages (textMessageID int PRIMARY KEY, text VARCHAR(300), employeeID int NOT NULL FOREIGN KEY REFERENCES Employees, date DATE NOT NULL, isConference boolean NOT NULL)";
	private static final String CREATE_TABLE_MESSAGES_EMPLOYEES = "CREATE TABLE MessagesEmployees (textMessageID int FOREIGN KEY REFERENCES TextMessages, EmployeeID int FOREIGN KEY REFERENCES Employees)";

	private static int identifierNumber = 0;
	
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
		Statement oStatement = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			
			int sID = TextMessageDAOMgr.identifierNumber++;
			String sText = oTextMessage.getTextMessage();
			int sIDSender = oTextMessage.getSender().getEmployeeID();
			//FIXME
			Timestamp sDate = new Timestamp(System.currentTimeMillis());			
			boolean sIsConf = oTextMessage.getIsConference();
			
			String sInsert1 = "INSERT INTO TextMessages (textMessageID, text, employeeID, date, isConference) VALUES (?,?,?,?,?)";
//					+ sID + ","	+ sIDSender	+ "," + sDate + ",'"
//					+ sText	+ "'," + sConf + ")";

			oPrepStatement= oConnection.prepareStatement(sInsert1);

			oPrepStatement.setInt(1, sID);
			oPrepStatement.setString(2, sText);
			oPrepStatement.setInt(3, sIDSender);
			oPrepStatement.setTimestamp(4, sDate);
			oPrepStatement.setBoolean(5, sIsConf);

			System.out.println("Se agrego con exito a la tabla de mensajes:"
					+ sID + "que con el texto: " + sText + "enviado por "
					+ sIDSender + " , con la fecha " + sDate);

			

			oPrepStatement.execute(sInsert1);

			String sInsert2 = "INSERT INTO MessagesEmployees (textMessageID,EmployeeID) VALUES ("
					+ sID + ",'" + ")";

			oStatement = oConnection.createStatement();
			
			oStatement.execute(sInsert2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public HashSet<TextMessage> getTextMessages(Employee oSender,
			HashSet<Employee> oReceivers) throws RemoteException {
		// TODO Auto-generated method stub

		HashSet<TextMessage> oList = new HashSet<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			ResultSet oResultSet = null;
			String sQuery = null;

			for (Employee iEmployee : oReceivers) {
				sQuery = "SELECT DISTINCT e.employeeID, e.iD, e.name, e.lastName, e.location, e.sector, e.position"
						+ " FROM Employees e, TextMessage tm, MessagesEmployees m "
						+ "WHERE t.receivers = e.employeeID AND m.textMessageID = tm.textMessageID AND tm.sender="
						+ oSender.getEmployeeID()
						+ "AND tm.receivers="
						+ iEmployee.getEmployeeID()
						+ ""
						+ "UNION "
						+ "SELECT DISTINCT e.employeeID, e.iD, e.name, e.lastName, e.location, e.sector, e.position"
						+ " FROM Employees e, TextMessage tm, MessagesEmployees m "
						+ "WHERE t.receivers = e.employeeID AND m.textMessageID = tm.textMessageID AND tm.sender="
						+ iEmployee.getEmployeeID()
						+ "AND tm.receivers="
						+ oSender.getEmployeeID() + ") ;";

			}			
			
			oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sID = oResultSet.getInt(1);
				String sTextMessage = oResultSet.getString(2);
				Employee sSender = oSender;
				HashSet<Employee> sReceivers = oReceivers;
				Timestamp sDate = oResultSet.getTimestamp(5);
				boolean sIsConference = oResultSet.getBoolean(6);

				TextMessage oTextMessages = new TextMessage(sID, sTextMessage,
						sSender, sReceivers, sDate, sIsConference);

				oList.add(oTextMessages);
			}

			oResultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		Statement oStatement2;
		boolean bValue = false;
		boolean bValue2 = false;

		oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

		try {

			oStatement = oConnection.createStatement();
			oStatement2 = oConnection.createStatement();
			bValue = oStatement.execute(CREATE_TABLE_TEXT_MESSAGES);
			bValue2 = oStatement2.execute(CREATE_TABLE_MESSAGES_EMPLOYEES);

			if (!bValue && !bValue2) {
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
