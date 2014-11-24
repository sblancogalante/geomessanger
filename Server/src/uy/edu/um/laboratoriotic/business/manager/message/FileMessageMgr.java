package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.business.helper.Helper;
import uy.edu.um.laboratoriotic.business.management.message.FileMessageMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.message.FileMessageDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.message.FileMessageDAOMgt;
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

	@Override
	public ArrayList<FileMessageVO> getFileMessages(EmployeeVO oSenderVO,
			EmployeeVO oReceiverVO) throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		FileMessageVO oFileMessageVO;
		ArrayList<FileMessage> oArrayList = new ArrayList<>();
		ArrayList<FileMessageVO> oListToReturn = new ArrayList<>();

		FileMessageDAOMgt oDAOFileMessage = FileMessageDAOFactory
				.getFileMessageDAOMgt();

		Employee oSenderEmployee = Helper.modularizeEmployee(oSenderVO);

		Employee oReceiverEmployee = Helper.modularizeEmployee(oReceiverVO);

		oArrayList = oDAOFileMessage.getFileMessages(oSenderEmployee,
				oReceiverEmployee);

		if (oArrayList != null) {

			for (FileMessage iFileMessage : oArrayList) {
				oFileMessageVO = iFileMessage.toVO();
				oListToReturn.add(oFileMessageVO);
			}

		}

		return oListToReturn;
	}

}
