package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
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

		Type oTypeLocationSender = new Type(oFileMessageVO.getSender()
				.getLocation().getTypeID(), oFileMessageVO.getSender()
				.getLocation().getType(), oFileMessageVO.getSender()
				.getLocation().getValue());
		Type oTypeSectorSender = new Type(oFileMessageVO.getSender()
				.getSector().getTypeID(), oFileMessageVO.getSender()
				.getSector().getType(), oFileMessageVO.getSender().getSector()
				.getValue());

		Employee oSenderEmployee = new Employee(oFileMessageVO.getSender()
				.getID(), oFileMessageVO.getSender().getName(), oFileMessageVO
				.getSender().getLastName(), oFileMessageVO.getSender()
				.getUserName(), oFileMessageVO.getSender().getPassword(),
				oTypeLocationSender, oTypeSectorSender, oFileMessageVO
						.getSender().getMail(), oFileMessageVO.getSender()
						.getPosition(), oFileMessageVO.getSender()
						.getWorkingHour(), oFileMessageVO.getSender()
						.getProfilePicture(), oFileMessageVO.getSender()
						.getStatus());

		Type oTypeLocationReceiver = new Type(oFileMessageVO.getReceiver()
				.getLocation().getTypeID(), oFileMessageVO.getReceiver()
				.getLocation().getType(), oFileMessageVO.getReceiver()
				.getLocation().getValue());
		Type oTypeSectorReceiver = new Type(oFileMessageVO.getReceiver()
				.getSector().getTypeID(), oFileMessageVO.getReceiver()
				.getSector().getType(), oFileMessageVO.getReceiver()
				.getSector().getValue());

		Employee oReceiverEmployee = new Employee(oFileMessageVO.getReceiver()
				.getID(), oFileMessageVO.getReceiver().getName(),
				oFileMessageVO.getReceiver().getLastName(), oFileMessageVO
						.getReceiver().getUserName(), oFileMessageVO
						.getReceiver().getPassword(), oTypeLocationReceiver,
				oTypeSectorReceiver, oFileMessageVO.getReceiver().getMail(),
				oFileMessageVO.getReceiver().getPosition(), oFileMessageVO
						.getReceiver().getWorkingHour(), oFileMessageVO
						.getReceiver().getProfilePicture(), oFileMessageVO
						.getReceiver().getStatus());

		FileMessage oFileMessage = new FileMessage(
				oFileMessageVO.getIDMessage(), oFileMessageVO.getFileMessage(),
				oFileMessageVO.getFileMessageName(), oSenderEmployee,
				oReceiverEmployee, oFileMessageVO.getDate(),
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
