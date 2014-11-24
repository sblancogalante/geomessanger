package uy.edu.um.laboratoriotic.services.manager.message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.exceptions.employee.EmployeeDoesNotExist;
import uy.edu.um.laboratoriotic.services.ServiceFacade;
import uy.edu.um.laboratoriotic.services.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.services.management.message.FileMessageRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;

/**
 * This is the implementation of FileMessageMgt
 * 
 * @author sblanco1
 * 
 */
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

	public void addFileMessage(FileMessageVO oFileMessageVO)
			throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub

		FileMessageRemoteMgt oFileMessageRemoteMgt = newLookUp("FileMessageRemoteMgr");

		oFileMessageRemoteMgt.addFileMessage(oFileMessageVO);

	}

	public ArrayList<FileMessageVO> getFileMessages(EmployeeVO oSender,
			EmployeeVO oReceiver) throws RemoteException, NotBoundException, EmployeeDoesNotExist {
		// TODO Auto-generated method stub

		ArrayList<FileMessageVO> oArrayListToReturn = new ArrayList<>();

		FileMessageRemoteMgt oFileMessageRemoteMgt = newLookUp("FileMessageRemoteMgr");

		oArrayListToReturn = oFileMessageRemoteMgt.getFileMessages(oSender,
				oReceiver);

		return oArrayListToReturn;
	}

	/*
	 * Helping methods
	 */
	private FileMessageRemoteMgt newLookUp(String sObjectService)
			throws RemoteException, NotBoundException {

		FileMessageRemoteMgt oFileMessageRemoteMgtToReturn;

		Registry oRegistry = LocateRegistry
				.getRegistry(ServiceFacade.getInstance().getHost(),
						ServiceFacade.getInstance().getPort());
		oFileMessageRemoteMgtToReturn = (FileMessageRemoteMgt) oRegistry
				.lookup(sObjectService);

		return oFileMessageRemoteMgtToReturn;
	}

}