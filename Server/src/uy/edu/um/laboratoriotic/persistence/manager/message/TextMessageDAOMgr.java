package uy.edu.um.laboratoriotic.persistence.manager.message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.persistence.DataBaseConnectionMgr;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
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
			throws DataBaseConnection, EmployeeDoesNotExist,
			EmployeeAlreadyExists {
		// TODO Auto-generated method stub

		Connection oConnection = null;
		PreparedStatement oPrepStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();

			String sText = oTextMessage.getTextMessage();
			int sIDSender;
			int sIDReceiver;
			try {
				sIDSender = EmployeeDAOFactory.getEmployeeDAOMgt()
						.searchEmployee(oTextMessage.getSender().getUserName())
						.getEmployeeID();
				sIDReceiver = EmployeeDAOFactory
						.getEmployeeDAOMgt()
						.searchEmployee(
								oTextMessage.getReceiver().getUserName())
						.getEmployeeID();
			} catch (EmployeeDoesNotExist e) {
				// TODO Auto-generated catch block
				throw new EmployeeDoesNotExist();
			} catch (EmployeeAlreadyExists e) {
				// TODO Auto-generated catch block
				throw new EmployeeAlreadyExists();
			}

			String sInsert = "INSERT INTO TextMessages (text, employeeSenderID, employeeReceiverID) VALUES (?,?,?)";

			oPrepStatement = oConnection.prepareStatement(sInsert);

			oPrepStatement.setString(1, sText);
			oPrepStatement.setInt(2, sIDSender);
			oPrepStatement.setInt(3, sIDReceiver);

			oPrepStatement.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	@Override
	public ArrayList<TextMessage> getTextMessages(Employee oSender,
			Employee oReceiver) throws DataBaseConnection,
			EmployeeDoesNotExist, EmployeeAlreadyExists {
		// TODO Auto-generated method stub

		ArrayList<TextMessage> oList = new ArrayList<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();
			ResultSet oResultSet = null;
			String sQuery = null;

			int oSenderID = 0;
			int oReceiverID = 0;

			try {
				oSenderID = EmployeeDAOFactory.getEmployeeDAOMgt()
						.searchEmployee(oSender.getUserName()).getEmployeeID();
				if (oReceiver != null) {

					oReceiverID = EmployeeDAOFactory.getEmployeeDAOMgt()
							.searchEmployee(oReceiver.getUserName())
							.getEmployeeID();
				}

			} catch (EmployeeDoesNotExist e) {
				// TODO Auto-generated catch block
				throw new EmployeeDoesNotExist();
			} catch (EmployeeAlreadyExists e) {
				// TODO Auto-generated catch block
				throw new EmployeeAlreadyExists();
			}

			sQuery = "SELECT * FROM (SELECT DISTINCT e.employeeID, e.iD, e.name, e.lastName, e.location, e.sector, e.position, tm.textMessageID, tm.text, tm.date, tm.employeeSenderID, tm.employeeReceiverID"
					+ " FROM Employees e, TextMessages tm"
					+ " WHERE tm.employeeSenderID = e.employeeID AND tm.employeeSenderID = "
					+ oSenderID
					+ " AND tm.employeeReceiverID = "
					+ oReceiverID
					+ " UNION"
					+ " SELECT DISTINCT e.employeeID, e.iD, e.name, e.lastName, e.location, e.sector, e.position, tm.textMessageID, tm.text, tm.date, tm.employeeSenderID, tm.employeeReceiverID"
					+ " FROM Employees e, TextMessages tm"
					+ " WHERE tm.employeeSenderID = e.employeeID AND tm.employeeSenderID = "
					+ oReceiverID
					+ " AND tm.employeeReceiverID = "
					+ oSenderID
					+ " ) as result ORDER BY result.date ASC;";

			oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sID = oResultSet.getInt(8);
				String sTextMessage = oResultSet.getString(9);
				Timestamp sDate = oResultSet.getTimestamp(10);
				int sEmployeeSenderID = oResultSet.getInt(11);
				int sEmployeeReceiverID = oResultSet.getInt(12);
				Employee sSender = EmployeeDAOFactory.getEmployeeDAOMgt()
						.searchEmployee(sEmployeeSenderID);
				Employee sReceiver = EmployeeDAOFactory.getEmployeeDAOMgt()
						.searchEmployee(sEmployeeReceiverID);

				TextMessage oTextMessages = new TextMessage(sID, sTextMessage,
						sSender, sReceiver, sDate);

				oList.add(oTextMessages);

			}

			oResultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

		return oList;
	}

	@Override
	public int countTextCharacters(Employee oEmployee)
			throws DataBaseConnection, EmployeeDoesNotExist, EmployeeAlreadyExists {
		// TODO Auto-generated method stub

		Statement oStatement = null;
		Connection oConnection = null;

		int returnCount = 0;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();
			ResultSet oResultSet = null;
			String sQuery = null;

			int sEmployeeID = 0;
			try {
				sEmployeeID = EmployeeDAOFactory.getEmployeeDAOMgt()
						.searchEmployee(oEmployee.getUserName())
						.getEmployeeID();
			} catch (EmployeeDoesNotExist e) {
				// TODO Auto-generated catch block
				throw new EmployeeDoesNotExist();
			} catch (EmployeeAlreadyExists e) {
				// TODO Auto-generated catch block
				throw new EmployeeAlreadyExists();
			}

			sQuery = "SELECT * FROM (SELECT sum(char_length(text)) FROM TextMessages tm"
					+ " WHERE tm.employeeSenderID = "
					+ sEmployeeID
					+ " ) as result ;";

			oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				returnCount = oResultSet.getInt(1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return returnCount;
	}

}