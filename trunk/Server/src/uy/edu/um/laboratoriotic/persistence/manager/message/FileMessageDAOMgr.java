package uy.edu.um.laboratoriotic.persistence.manager.message;

import java.rmi.RemoteException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.DataBaseConnectionMgr;
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

			Blob sFile = oFileMessage.getFileMessage();
			int sIDSender = oFileMessage.getSender().getEmployeeID();
			int sIDReceiver = oFileMessage.getReceiver().getEmployeeID();
			// FIXME
			Timestamp sDate = new Timestamp(System.currentTimeMillis());

			String sInsert1 = "INSERT INTO FileMessages (text, employeeSenderID, employeeReceiverID, date) VALUES (?,?,?,?)";
			// + sID + "," + sIDSender + "," + sDate + ",'"
			// + sFile + "'," + sConf + ")";

			oPrepStatement = oConnection.prepareStatement(sInsert1);

			oPrepStatement.setBlob(2, sFile);
			oPrepStatement.setInt(3, sIDSender);
			oPrepStatement.setInt(3, sIDReceiver);
			oPrepStatement.setTimestamp(5, sDate);

			System.out
					.println("Se agrego con exito a la tabla de mensajes con el texto: "
							+ sFile	+ "enviado por " + sIDSender
							+ " , con la fecha " + sDate);

			oPrepStatement.execute();			

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

}