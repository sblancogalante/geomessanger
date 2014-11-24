package uy.edu.um.laboratoriotic.persistence.manager.message;

import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeAlreadyExists;
import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.persistence.DataBaseConnectionMgr;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
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

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();

			byte[] sFile = oFileMessage.getFileMessage();
			String sFileName = oFileMessage.getFileMessageName();
			int sIDSender = oFileMessage.getSender().getEmployeeID();
			int sIDReceiver = oFileMessage.getReceiver().getEmployeeID();

			Blob sFileToInsert = new javax.sql.rowset.serial.SerialBlob(sFile);

			String sInsert = "INSERT INTO FileMessages (file, fileName, employeeSenderID, employeeReceiverID) VALUES (?,?,?,?)";

			oPrepStatement = oConnection.prepareStatement(sInsert);

			oPrepStatement.setBlob(1, sFileToInsert);
			oPrepStatement.setString(2, sFileName);
			oPrepStatement.setInt(3, sIDSender);
			oPrepStatement.setInt(4, sIDReceiver);

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
	public ArrayList<FileMessage> getFileMessages(Employee oSender,
			Employee oReceiver) throws DataBaseConnection, RemoteException, EmployeeDoesNotExist, EmployeeAlreadyExists {
		// TODO Auto-generated method stub

		ArrayList<FileMessage> oList = new ArrayList<>();
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
				oReceiverID = EmployeeDAOFactory.getEmployeeDAOMgt()
						.searchEmployee(oReceiver.getUserName())
						.getEmployeeID();
				
			} catch (EmployeeDoesNotExist e) {
				throw new EmployeeDoesNotExist();
			}
			
			sQuery = "SELECT * FROM (SELECT DISTINCT e.employeeID, e.iD, e.name, e.lastName, e.location, e.sector, e.position, fm.fileMessageID, fm.file, fm.fileName, fm.date, fm.employeeSenderID, fm.employeeReceiverID"
					+ " FROM Employees e, FileMessages fm"
					+ " WHERE fm.employeeSenderID = e.employeeID AND fm.employeeSenderID = "
					+ oSenderID
					+ " AND fm.employeeReceiverID = "
					+ oReceiverID
					+ " UNION"
					+ " SELECT DISTINCT e.employeeID, e.iD, e.name, e.lastName, e.location, e.sector, e.position, fm.fileMessageID, fm.file, fm.fileName, fm.date, fm.employeeSenderID, fm.employeeReceiverID"
					+ " FROM Employees e, FileMessages fm"
					+ " WHERE fm.employeeSenderID = e.employeeID AND fm.employeeSenderID = "
					+ oReceiverID
					+ " AND fm.employeeReceiverID = "
					+ oSenderID
					+ " ) as result ORDER BY result.date ASC;";

			oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sID = oResultSet.getInt(8);
				Blob sFileMessage = oResultSet.getBlob(9);
				String sFileName = oResultSet.getString(10);
				Timestamp sDate = oResultSet.getTimestamp(11);
				int sEmployeeSenderID = oResultSet.getInt(12);
				int sEmployeeReceiverID = oResultSet.getInt(13);

				Employee sSender = EmployeeDAOFactory.getEmployeeDAOMgt()
						.searchEmployee(sEmployeeSenderID);
				Employee sReceiver = EmployeeDAOFactory.getEmployeeDAOMgt()
						.searchEmployee(sEmployeeReceiverID);

				int blobLength = (int) sFileMessage.length();
				byte[] sFile = sFileMessage.getBytes(1, blobLength);

				FileMessage oFileMessages = new FileMessage(sID, sFile,
						sFileName, sSender, sReceiver, sDate);

				oList.add(oFileMessages);

			}

			oResultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmployeeAlreadyExists e) {
			// TODO Auto-generated catch block
			throw new EmployeeAlreadyExists();
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

}