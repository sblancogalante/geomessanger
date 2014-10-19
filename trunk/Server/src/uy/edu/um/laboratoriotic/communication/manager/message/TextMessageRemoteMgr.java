package uy.edu.um.laboratoriotic.communication.manager.message;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.business.management.message.TextMessageMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

public class TextMessageRemoteMgr implements TextMessageRemoteMgt {

	/*
	 * Attributes of the class
	 */
	private static TextMessageRemoteMgr instance = null;

	/*
	 * Constructor
	 */
	private TextMessageRemoteMgr() throws RemoteException {

	}

	public static TextMessageRemoteMgr getInstance() throws RemoteException {

		if (instance == null) {
			instance = new TextMessageRemoteMgr();
		}

		return instance;
	}

	/*
	 * Management implementation methods
	 */
	@Override
	public TextMessageVO addTextMessage(TextMessageVO oTextMessageVO)
			throws RemoteException {
		// TODO Auto-generated method stub

		TextMessageMgt oTextMessageMgt = BusinessFacade.getInstance()
				.getTextMessageFactory().getTextMessageMgt();

		Employee oSenderEmployee = new Employee(oTextMessageVO.getSender()
				.getEmployeeID(), oTextMessageVO.getSender().getID(),
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

			Employee oReceiverEmployee = new Employee(
					iEmployeeVO.getEmployeeID(), iEmployeeVO.getID(),
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
			oTextMessageMgt.addTextMessage(oTextMessage);
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		TextMessageVO oTextMessageVOToReturn = oTextMessage.toVO();

		return oTextMessageVOToReturn;
	}

	@Override
	public HashSet<TextMessageVO> getTextMessages(Date oDate)
			throws RemoteException {
		// TODO Auto-generated method stub

		HashSet<TextMessageVO> oListToReturn = new HashSet<>();
		TextMessageVO oTextMessageVO;

		TextMessageMgt oTextMessageMgt = BusinessFacade.getInstance()
				.getTextMessageFactory().getTextMessageMgt();

		HashSet<TextMessage> oHashSet = new HashSet<>();
		
		try {
			oHashSet = oTextMessageMgt.getTextMessages();

			for (TextMessage iTextMessage : oHashSet) {
				oTextMessageVO = iTextMessage.toVO();
				oListToReturn.add(oTextMessageVO);
			}

		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		return oListToReturn;
		
	}

	@Override
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clearHistory(EmployeeVO oSender, EmployeeVO oReceiver) {
		// TODO Auto-generated method stub

	}

}
