package uy.edu.um.laboratoriotic.services.manager.message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.services.management.message.FileMessageRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
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

	public void addFileMessage(FileMessageVO oFileMessageVO)
			throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub

		FileMessageRemoteMgt oFileMessageRemoteMgt = lookUp(
				"FileMessageRemoteMgr", 1099);

		oFileMessageRemoteMgt.addFileMessage(oFileMessageVO);
		
	}

	public ArrayList<FileMessageVO> getFileMessages(EmployeeVO oSender, EmployeeVO oReceiver) throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		
		ArrayList<FileMessageVO> oArrayListToReturn = new ArrayList<>();
		
		FileMessageRemoteMgt oFileMessageRemoteMgt = lookUp(
				"FileMessageRemoteMgr", 1099);
		
		oArrayListToReturn = oFileMessageRemoteMgt.getFileMessages(oSender, oReceiver);
		
		return oArrayListToReturn;
	}

	/*
	 * Helping methods
	 */
	private FileMessageRemoteMgt lookUp(String sObjectService, int oPortNumber)
			throws RemoteException, NotBoundException {

		FileMessageRemoteMgt oReturn;

		Registry oRegistry = LocateRegistry.getRegistry(oPortNumber);
		oReturn = (FileMessageRemoteMgt) oRegistry.lookup(sObjectService);

		return oReturn;
	}


}
