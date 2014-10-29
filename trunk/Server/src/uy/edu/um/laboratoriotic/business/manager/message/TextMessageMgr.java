package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
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

		Employee oSenderEmployee = new Employee(oTextMessageVO.getSender().getID(),
				oTextMessageVO.getSender().getName(), oTextMessageVO
						.getSender().getLastName(), oTextMessageVO.getSender()
						.getUserName(), oTextMessageVO.getSender()
						.getPassword(), oTextMessageVO.getSender()
						.getLocation(), oTextMessageVO.getSender().getSector(),
				oTextMessageVO.getSender().getMail(), oTextMessageVO
						.getSender().getPosition(), oTextMessageVO.getSender()
						.getWorkingHour(), oTextMessageVO.getSender()
						.getProfilePicture(), oTextMessageVO.getSender()
						.getStatus());

		HashSet<Employee> oReceiversEmployees = new HashSet<>();

		for (EmployeeVO iEmployeeVO : oTextMessageVO.getReceivers()) {

			Employee oReceiverEmployee = new Employee(iEmployeeVO.getID(),
					iEmployeeVO.getName(), iEmployeeVO.getLastName(),
					iEmployeeVO.getUserName(), iEmployeeVO.getPassword(),
					iEmployeeVO.getLocation(), iEmployeeVO.getSector(),
					iEmployeeVO.getMail(), iEmployeeVO.getPosition(),
					iEmployeeVO.getWorkingHour(),
					iEmployeeVO.getProfilePicture(), iEmployeeVO.getStatus());

			oReceiversEmployees.add(oReceiverEmployee);
		}

		TextMessage oTextMessage = new TextMessage(
				oTextMessageVO.getIDMessage(), oTextMessageVO.getTextMessage(),
				oSenderEmployee, oReceiversEmployees, oTextMessageVO.getDate(),
				oTextMessageVO.getIsConference());

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
			HashSet<EmployeeVO> oReceiversVO) throws DataBaseConnection {
		// TODO Auto-generated method stub

		TextMessageVO oTextMessageVO;
		ArrayList<TextMessage> oArrayList = new ArrayList<>();
		ArrayList<TextMessageVO> oListToReturn = new ArrayList<>();

		Employee oEmployee;
		HashSet<Employee> oReceivers = new HashSet<>();

		TextMessageDAOMgt oDAOTextMessage = TextMessageDAOFactory
				.getTextMessageDAOMgt();

		Employee oSender = new Employee(oSenderVO.getID(), oSenderVO.getName(),
				oSenderVO.getLastName(), oSenderVO.getUserName(),
				oSenderVO.getPassword(), oSenderVO.getLocation(),
				oSenderVO.getSector(), oSenderVO.getMail(),
				oSenderVO.getPosition(), oSenderVO.getWorkingHour(),
				oSenderVO.getProfilePicture(), oSenderVO.getStatus());

		for (EmployeeVO iEmployeeVO : oReceiversVO) {
			oEmployee = new Employee(iEmployeeVO.getID(), iEmployeeVO.getName(),
					iEmployeeVO.getLastName(), iEmployeeVO.getUserName(),
					iEmployeeVO.getPassword(), iEmployeeVO.getLocation(),
					iEmployeeVO.getSector(), iEmployeeVO.getMail(),
					iEmployeeVO.getPosition(), iEmployeeVO.getWorkingHour(),
					iEmployeeVO.getProfilePicture(), iEmployeeVO.getStatus());
			oReceivers.add(oEmployee);
		}

		try {
			oArrayList = oDAOTextMessage.getTextMessages(oSender, oReceivers);

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
