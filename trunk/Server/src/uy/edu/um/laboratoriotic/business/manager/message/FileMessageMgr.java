package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.business.helper.Helper;
import uy.edu.um.laboratoriotic.business.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.message.FileMessageDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.message.FileMessageDAOMgt;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;

public class FileMessageMgr implements FileMessageMgt {

	/*
	 * Attributes of the class
	 */
	private static FileMessageMgr instance = null;

	/*
	 * Constructor
	 */
	private FileMessageMgr() {

	}

	public static FileMessageMgr getInstance() {
		if (instance == null) {
			instance = new FileMessageMgr();
		}

		return instance;
	}

	/*
	 * Management implementation methods
	 */
	@Override
	public void addFileMessage(FileMessageVO oFileMessageVO)
			throws DataBaseConnection {
		// TODO Auto-generated method stub

		FileMessageDAOMgt oNewDAOFileMessage = FileMessageDAOFactory
				.getFileMessageDAOMgt();				

		FileMessage oFileMessage = Helper.modularizeFileMessage(oFileMessageVO);

		try {
			oNewDAOFileMessage.addFileMessage(oFileMessage);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		oFileMessage.toVO();

	}

	// @Override
	// public ArrayList<FileMessageVO> getFileMessages(EmployeeVO oSenderVO,
	// EmployeeVO oReceiverVO)
	// throws DataBaseConnection {
	// // TODO Auto-generated method stub
	//
	// FileMessageDAOMgt oDAOFileMessage =
	// FileMessageDAOFactory.getFileMessageDAOMgt();
	// ArrayList<FileMessage> list = new ArrayList<FileMessage>();
	// try {
	// list = oDAOFileMessage.getFileMessages(oSender, oReceiver);
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return list;
	// }

}
