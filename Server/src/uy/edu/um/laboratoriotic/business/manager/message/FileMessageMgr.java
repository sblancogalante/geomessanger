package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.business.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.message.FileMessageDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.message.FileMessageDAOMgt;


public class FileMessageMgr implements FileMessageMgt{
	
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
	public void addFileMessage(FileMessage oFileMessage)
			throws DataBaseConnection {
		// TODO Auto-generated method stub
		FileMessageDAOMgt oNewDAOFileMessage = FileMessageDAOFactory.getFileMessageDAOMgt();
		FileMessage oNewFileMessage = oFileMessage;
		try {
			oNewDAOFileMessage.addFileMessage(oNewFileMessage);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

	}

//	@Override
//	public HashSet<FileMessage> getFileMessages(Employee oSender, HashSet<Employee> oReceivers)
//			throws DataBaseConnection {
//		// TODO Auto-generated method stub
//
//		FileMessageDAOMgt oDAOFileMessage = FileMessageDAOFactory.getFileMessageDAOMgt();
//		HashSet<FileMessage> list = new HashSet<FileMessage>();
//		try {
//			list = oDAOFileMessage.getFileMessages(oSender, oReceivers);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return list;
//	}


}
