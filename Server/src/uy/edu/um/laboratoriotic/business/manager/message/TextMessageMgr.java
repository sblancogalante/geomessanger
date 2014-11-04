package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.business.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.message.TextMessageDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.message.TextMessageDAOMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

/**
 * This class is the implementation of TextMessageMgt
 * 
 * @author sblanco1
 * 
 */
public class TextMessageMgr implements TextMessageMgt {

	/*
	 * Attributes of the class
	 */
	private static TextMessageMgr instance = null;

	/*
	 * Constructor
	 */
	private TextMessageMgr() {

	}

	public static TextMessageMgr getInstance() {

		if (instance == null) {
			instance = new TextMessageMgr();
		}

		return instance;
	}

	/*
	 * Management implementation methods
	 */
	@Override
	public void addTextMessage(TextMessageVO oTextMessageVO)
			throws DataBaseConnection {
		// TODO Auto-generated method stub

		TextMessageDAOMgt oNewDAOTextMessage = TextMessageDAOFactory
				.getTextMessageDAOMgt();

		Type oTypeLocationSender = new Type(oTextMessageVO.getSender()
				.getLocation().getTypeID(), oTextMessageVO.getSender()
				.getLocation().getType(), oTextMessageVO.getSender()
				.getLocation().getValue());
		Type oTypeSectorSender = new Type(oTextMessageVO.getSender()
				.getSector().getTypeID(), oTextMessageVO.getSender()
				.getSector().getType(), oTextMessageVO.getSender().getSector()
				.getValue());

		Employee oSenderEmployee = new Employee(oTextMessageVO.getSender()
				.getID(), oTextMessageVO.getSender().getName(), oTextMessageVO
				.getSender().getLastName(), oTextMessageVO.getSender()
				.getUserName(), oTextMessageVO.getSender().getPassword(),
				oTypeLocationSender, oTypeSectorSender, oTextMessageVO
						.getSender().getMail(), oTextMessageVO.getSender()
						.getPosition(), oTextMessageVO.getSender()
						.getWorkingHour(), oTextMessageVO.getSender()
						.getProfilePicture(), oTextMessageVO.getSender()
						.getStatus());

		Type oTypeLocationReceiver = new Type(oTextMessageVO.getSender()
				.getLocation().getTypeID(), oTextMessageVO.getSender()
				.getLocation().getType(), oTextMessageVO.getSender()
				.getLocation().getValue());
		Type oTypeSectorReceiver = new Type(oTextMessageVO.getReceiver()
				.getSector().getTypeID(), oTextMessageVO.getReceiver()
				.getSector().getType(), oTextMessageVO.getReceiver()
				.getSector().getValue());

		Employee oReceiverEmployee = new Employee(oTextMessageVO.getReceiver()
				.getID(), oTextMessageVO.getReceiver().getName(),
				oTextMessageVO.getReceiver().getLastName(), oTextMessageVO
						.getReceiver().getUserName(), oTextMessageVO
						.getReceiver().getPassword(), oTypeLocationReceiver,
				oTypeSectorReceiver, oTextMessageVO.getReceiver().getMail(),
				oTextMessageVO.getReceiver().getPosition(), oTextMessageVO
						.getReceiver().getWorkingHour(), oTextMessageVO
						.getReceiver().getProfilePicture(), oTextMessageVO
						.getReceiver().getStatus());

		TextMessage oTextMessage = new TextMessage(
				oTextMessageVO.getIDMessage(), oTextMessageVO.getTextMessage(),
				oSenderEmployee, oReceiverEmployee, oTextMessageVO.getDate());

		try {
			oNewDAOTextMessage.addTextMessage(oTextMessage);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		oTextMessage.toVO();

	}

	@Override
	public ArrayList<TextMessageVO> getTextMessages(EmployeeVO oSenderVO,
			EmployeeVO oReceiverVO) throws DataBaseConnection {
		// TODO Auto-generated method stub

		TextMessageVO oTextMessageVO;
		ArrayList<TextMessage> oArrayList = new ArrayList<>();
		ArrayList<TextMessageVO> oListToReturn = new ArrayList<>();

		TextMessageDAOMgt oDAOTextMessage = TextMessageDAOFactory
				.getTextMessageDAOMgt();

		Type oTypeLocationSender = new Type(
				oSenderVO.getLocation().getTypeID(), oSenderVO.getLocation()
						.getType(), oSenderVO.getLocation().getValue());
		Type oTypeSectorSender = new Type(oSenderVO.getSector().getTypeID(),
				oSenderVO.getSector().getType(), oSenderVO.getSector()
						.getValue());

		Employee oSenderEmployee = new Employee(oSenderVO.getID(),
				oSenderVO.getName(), oSenderVO.getLastName(),
				oSenderVO.getUserName(), oSenderVO.getPassword(),
				oTypeLocationSender, oTypeSectorSender, oSenderVO.getMail(),
				oSenderVO.getPosition(), oSenderVO.getWorkingHour(),
				oSenderVO.getProfilePicture(), oSenderVO.getStatus());

		Type oTypeLocationReceiver = new Type(oReceiverVO.getLocation()
				.getTypeID(), oReceiverVO.getLocation().getType(), oReceiverVO
				.getLocation().getValue());
		Type oTypeSectorReceiver = new Type(
				oReceiverVO.getSector().getTypeID(), oReceiverVO.getSector()
						.getType(), oReceiverVO.getSector().getValue());

		Employee oReceiverEmployee = new Employee(oReceiverVO.getID(),
				oReceiverVO.getName(), oReceiverVO.getLastName(),
				oReceiverVO.getUserName(), oReceiverVO.getPassword(),
				oTypeLocationReceiver, oTypeSectorReceiver,
				oReceiverVO.getMail(), oReceiverVO.getPosition(),
				oReceiverVO.getWorkingHour(), oReceiverVO.getProfilePicture(),
				oReceiverVO.getStatus());

		try {
			oArrayList = oDAOTextMessage.getTextMessages(oSenderEmployee,
					oReceiverEmployee);

			for (TextMessage iTextMessage : oArrayList) {
				oTextMessageVO = iTextMessage.toVO();
				oListToReturn.add(oTextMessageVO);
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return oListToReturn;
	}

}
