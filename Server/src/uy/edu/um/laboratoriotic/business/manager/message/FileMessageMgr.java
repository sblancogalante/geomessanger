package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
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

		Employee oSenderEmployee = new Employee(oFileMessageVO.getSender().getID(),
				oFileMessageVO.getSender().getName(), oFileMessageVO
						.getSender().getLastName(), oFileMessageVO.getSender()
						.getUserName(), oFileMessageVO.getSender()
						.getPassword(), oFileMessageVO.getSender()
						.getLocation(), oFileMessageVO.getSender().getSector(),
				oFileMessageVO.getSender().getMail(), oFileMessageVO
						.getSender().getPosition(), oFileMessageVO.getSender()
						.getWorkingHour(), oFileMessageVO.getSender()
						.getProfilePicture(), oFileMessageVO.getSender()
						.getStatus());

		HashSet<Employee> oReceiversEmployees = new HashSet<>();

		for (EmployeeVO iEmployeeVO : oFileMessageVO.getReceivers()) {

			Employee oReceiverEmployee = new Employee(iEmployeeVO.getID(),
					iEmployeeVO.getName(), iEmployeeVO.getLastName(),
					iEmployeeVO.getUserName(), iEmployeeVO.getPassword(),
					iEmployeeVO.getLocation(), iEmployeeVO.getSector(),
					iEmployeeVO.getMail(), iEmployeeVO.getPosition(),
					iEmployeeVO.getWorkingHour(),
					iEmployeeVO.getProfilePicture(), iEmployeeVO.getStatus());

			oReceiversEmployees.add(oReceiverEmployee);
		}

		FileMessage oFileMessage = new FileMessage(
				oFileMessageVO.getIDMessage(), oFileMessageVO.getFileMessage(),
				oFileMessageVO.getFileMessageName(), oSenderEmployee,
				oReceiversEmployees, oFileMessageVO.getDate(),
				oFileMessageVO.getIsConference());

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
	// HashSet<EmployeeVO> oReceiversVO)
	// throws DataBaseConnection {
	// // TODO Auto-generated method stub
	//
	// FileMessageDAOMgt oDAOFileMessage =
	// FileMessageDAOFactory.getFileMessageDAOMgt();
	// ArrayList<FileMessage> list = new ArrayList<FileMessage>();
	// try {
	// list = oDAOFileMessage.getFileMessages(oSender, oReceivers);
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// return list;
	// }

}
