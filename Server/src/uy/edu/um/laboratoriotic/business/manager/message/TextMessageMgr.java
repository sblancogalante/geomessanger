package uy.edu.um.laboratoriotic.business.manager.message;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.business.helper.Helper;
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
			throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		TextMessageDAOMgt oNewDAOTextMessage = TextMessageDAOFactory
				.getTextMessageDAOMgt();

		TextMessage oTextMessageToReturn = Helper
				.modularizeTextMessage(oTextMessageVO);

		oNewDAOTextMessage.addTextMessage(oTextMessageToReturn);

		oTextMessageToReturn.toVO();

	}

	@Override
	public ArrayList<TextMessageVO> getTextMessages(EmployeeVO oSenderVO,
			EmployeeVO oReceiverVO) throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		TextMessageVO oTextMessageVO;
		ArrayList<TextMessage> oArrayList = new ArrayList<>();
		ArrayList<TextMessageVO> oListToReturn = new ArrayList<>();

		TextMessageDAOMgt oDAOTextMessage = TextMessageDAOFactory
				.getTextMessageDAOMgt();

		Employee oSenderEmployee = Helper.modularizeEmployee(oSenderVO);

		Employee oReceiverEmployee = Helper.modularizeEmployee(oReceiverVO);

		oArrayList = oDAOTextMessage.getTextMessages(oSenderEmployee,
				oReceiverEmployee);

		if (oArrayList != null) {
		
			for (TextMessage iTextMessage : oArrayList) {
				oTextMessageVO = iTextMessage.toVO();
				oListToReturn.add(oTextMessageVO);
			}
			
		}
		
		return oListToReturn;
	}

	@Override
	public int countTextCharacters(EmployeeVO oEmployeeVO)
			throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		int returnCount = 0;

		TextMessageDAOMgt oTextMessageDAOMgt = TextMessageDAOFactory
				.getTextMessageDAOMgt();
		Employee oEmployee = Helper.modularizeEmployee(oEmployeeVO);

		returnCount = oTextMessageDAOMgt.countTextCharacters(oEmployee);

		return returnCount;
	}

}