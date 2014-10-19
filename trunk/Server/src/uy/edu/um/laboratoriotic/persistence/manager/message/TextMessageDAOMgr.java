package uy.edu.um.laboratoriotic.persistence.manager.message;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;

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
	private static final String CREATE_TABLE_TEXT_MESSAGES = "CREATE TABLE textMessages (iDTextMessage int PRIMARY KEY, text VARCHAR(300) NOT NULL, iDEmployeeSender int NOT NULL, date DATE NOT NULL, isConference boolean NOT NULL)";
	private static final String CREATE_TABLE_EMPLOYEES_MESSAGES = "CREATE TABLE employeesMessages (iDTextMessage int FOREIGN KEY REFERENCES textMessages, iDEmployeeReceiver int FOREIGN KEY REFERENCES Employees)";

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
	public TextMessage addTextMessage(TextMessage oTextMessage)
			throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub
		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

			oStatement = oConnection.createStatement();

			long sID = oTextMessage.getIDMessage();
			int sIDSender = oTextMessage.getSender().getEmployeeID();
			Date sDate = oTextMessage.getDate();
			String sText = oTextMessage.getTextMessage();
			boolean sConf = oTextMessage.getIsConference();

			System.out.println("Se agrego con exito a la tabla de mensajes:"
					+ sID + "que con el texto: " + sText + "enviado por "
					+ sIDSender + " , con la fecha " + sDate);

			String sInsert1 = "INSERT INTO textMessages (iDTextMessage, text, iDEmployeeSender, date, isConference) VALUES ("
					+ sID
					+ ","
					+ sIDSender
					+ ","
					+ sDate
					+ ",'"
					+ sText
					+ "',"
					+ sConf + ")";

			oStatement.execute(sInsert1);

			String sInsert2 = "INSERT INTO messageEmployee (idTextMessage,idEmployeeReceiver) VALUES ("
					+ sID + ",'" + ")";

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

		return oTextMessage;
	}

	@Override
	public HashSet<TextMessage> getTextMessages() throws RemoteException {
		// TODO Auto-generated method stub

		HashSet<TextMessage> oList = new HashSet<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM TextMessage ) ;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sID = oResultSet.getInt(1);
				String sTextMessage = oResultSet.getString(2);
				Employee sSender = (Employee) oResultSet.getObject(3);
				HashSet<Employee> sReceivers = (HashSet<Employee>) oResultSet
						.getObject(4);
				Date sDate = oResultSet.getDate(5);
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
			bValue2 = oStatement2.execute(CREATE_TABLE_EMPLOYEES_MESSAGES);
			
			if (!bValue && !bValue2) {
				System.out.println("Se ejecuto con exito");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//throw new RemoteException();
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