package uy.edu.um.laboratoriotic.communication.manager.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.services.management.message.FileMessageRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;

/**
 * This class is the implementation of FileMessageRemoteMgt
 * 
 * @author sblanco1
 * 
 */
public class FileMessageRemoteMgr implements FileMessageRemoteMgt {

	/*
	 * Attributes of the class
	 */
	private static FileMessageRemoteMgr instance = null;

	/*
	 * Constructor
	 */
	private FileMessageRemoteMgr() throws RemoteException {

	}

	public static FileMessageRemoteMgr getInstance() throws RemoteException {

		if (instance == null) {
			instance = new FileMessageRemoteMgr();
		}

		return instance;
	}

	/*
	 * Management implementation methods
	 */
	@Override
	public void addFileMessage(FileMessageVO oFileMessageVO)
			throws RemoteException {
		// TODO Auto-generated method stub

		FileMessageMgt oFileMessageMgt = BusinessFacade.getInstance()
				.getFileMessageFactory().getFileMessageMgt();

		try {
			oFileMessageMgt.addFileMessage(oFileMessageVO);
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

	}

	@Override
	public ArrayList<FileMessageVO> getFileMessages(EmployeeVO oSenderVO,
			EmployeeVO oReceiverVO) throws RemoteException {
		// TODO Auto-generated method stub

		ArrayList<FileMessageVO> oListToReturn = new ArrayList<>();

		FileMessageMgt oFileMessageMgt = BusinessFacade.getInstance()
				.getFileMessageFactory().getFileMessageMgt();

		try {
			oListToReturn = oFileMessageMgt.getFileMessages(oSenderVO,
					oReceiverVO);

		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		return oListToReturn;

	}

	@Override
	public void clearHistory(EmployeeVO oSender, EmployeeVO oReceiver)
			throws RemoteException {
		// TODO Auto-generated method stub

	}

}
