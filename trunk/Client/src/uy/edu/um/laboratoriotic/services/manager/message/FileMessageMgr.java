package uy.edu.um.laboratoriotic.services.manager.message;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashSet;

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

	public HashSet<FileMessageVO> getFileMessages(EmployeeVO oSender, HashSet<EmployeeVO> oReceivers) throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		
		HashSet<FileMessageVO> oHashSetToReturn = new HashSet<>();
		
		FileMessageRemoteMgt oFileMessageRemoteMgt = lookUp(
				"FileMessageRemoteMgr", 1099);
		
		oHashSetToReturn = oFileMessageRemoteMgt.getFileMessages(oSender, oReceivers);
		
		return oHashSetToReturn;
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
