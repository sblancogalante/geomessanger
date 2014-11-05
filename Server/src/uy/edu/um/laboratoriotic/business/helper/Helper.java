package uy.edu.um.laboratoriotic.business.helper;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.business.entities.message.FileMessage;
import uy.edu.um.laboratoriotic.business.entities.message.TextMessage;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.FileMessageVO;
import uy.edu.um.laboratoriotic.services.valueobject.message.TextMessageVO;

/**
 * This class is a helper class to modularize the conversion of value object to 
 * object
 * 
 * @author sblanco1
 *
 */
public class Helper {

	/**
	 * This method converts from EmployeeVO to Employee 
	 * 
	 * @param oEmployeeVO
	 * @return
	 */
	public static Employee modularizeEmployee(EmployeeVO oEmployeeVO) {

		Type oTypeDocument = new Type(oEmployeeVO.getDocument().getTypeID(),
				oEmployeeVO.getDocument().getType(), oEmployeeVO.getDocument()
						.getValue());
		Type oTypeLocation = new Type(oEmployeeVO.getLocation().getTypeID(),
				oEmployeeVO.getLocation().getType(), oEmployeeVO.getLocation()
						.getValue());
		Type oTypeSector = new Type(oEmployeeVO.getSector().getTypeID(),
				oEmployeeVO.getSector().getType(), oEmployeeVO.getSector()
						.getValue());

		Employee oEmployee = new Employee(oTypeDocument, oEmployeeVO.getID(),
				oEmployeeVO.getName(), oEmployeeVO.getLastName(),
				oEmployeeVO.getUserName(), oEmployeeVO.getPassword(),
				oTypeLocation, oTypeSector, oEmployeeVO.getMail(),
				oEmployeeVO.getPosition(), oEmployeeVO.getWorkingHour(),
				oEmployeeVO.getProfilePicture(), oEmployeeVO.getStatus(),
				oEmployeeVO.getAdmin());

		return oEmployee;
	}
	
	/**
	 * This method converts from TextMessageVO to TextMessage
	 * 
	 * @param oTextMessageVO
	 * @return
	 */
	public static TextMessage modularizeTextMessage(TextMessageVO oTextMessageVO) {

		Type oTypeDocumentSender = new Type(oTextMessageVO.getSender()
				.getDocument().getTypeID(), oTextMessageVO.getSender()
				.getDocument().getType(), oTextMessageVO.getSender()
				.getDocument().getValue());
		Type oTypeLocationSender = new Type(oTextMessageVO.getSender()
				.getLocation().getTypeID(), oTextMessageVO.getSender()
				.getLocation().getType(), oTextMessageVO.getSender()
				.getLocation().getValue());
		Type oTypeSectorSender = new Type(oTextMessageVO.getSender()
				.getSector().getTypeID(), oTextMessageVO.getSender()
				.getSector().getType(), oTextMessageVO.getSender().getSector()
				.getValue());

		Employee oSenderEmployee = new Employee(oTypeDocumentSender,
				oTextMessageVO.getSender().getID(), oTextMessageVO.getSender()
						.getName(), oTextMessageVO.getSender().getLastName(),
				oTextMessageVO.getSender().getUserName(), oTextMessageVO
						.getSender().getPassword(), oTypeLocationSender,
				oTypeSectorSender, oTextMessageVO.getSender().getMail(),
				oTextMessageVO.getSender().getPosition(), oTextMessageVO
						.getSender().getWorkingHour(), oTextMessageVO
						.getSender().getProfilePicture(), oTextMessageVO
						.getSender().getStatus(), oTextMessageVO.getSender()
						.getAdmin());

		Type oTypeDocumentReceiver = new Type(oTextMessageVO.getReceiver()
				.getDocument().getTypeID(), oTextMessageVO.getReceiver()
				.getDocument().getType(), oTextMessageVO.getReceiver()
				.getDocument().getValue());
		Type oTypeLocationReceiver = new Type(oTextMessageVO.getSender()
				.getLocation().getTypeID(), oTextMessageVO.getSender()
				.getLocation().getType(), oTextMessageVO.getSender()
				.getLocation().getValue());
		Type oTypeSectorReceiver = new Type(oTextMessageVO.getReceiver()
				.getSector().getTypeID(), oTextMessageVO.getReceiver()
				.getSector().getType(), oTextMessageVO.getReceiver()
				.getSector().getValue());

		Employee oReceiverEmployee = new Employee(oTypeDocumentReceiver,
				oTextMessageVO.getReceiver().getID(), oTextMessageVO
						.getReceiver().getName(), oTextMessageVO.getReceiver()
						.getLastName(), oTextMessageVO.getReceiver()
						.getUserName(), oTextMessageVO.getReceiver()
						.getPassword(), oTypeLocationReceiver,
				oTypeSectorReceiver, oTextMessageVO.getReceiver().getMail(),
				oTextMessageVO.getReceiver().getPosition(), oTextMessageVO
						.getReceiver().getWorkingHour(), oTextMessageVO
						.getReceiver().getProfilePicture(), oTextMessageVO
						.getReceiver().getStatus(), oTextMessageVO
						.getReceiver().getAdmin());

		TextMessage oTextMessage = new TextMessage(
				oTextMessageVO.getIDMessage(), oTextMessageVO.getTextMessage(),
				oSenderEmployee, oReceiverEmployee, oTextMessageVO.getDate());

		return oTextMessage;
	}
	
	/**
	 * This method converts from FileMessageVO to FileMessage
	 * 
	 * @param oFileMessageVO
	 * @return
	 */
	public static FileMessage modularizeFileMessage(FileMessageVO oFileMessageVO) {

		Type oTypeDocumentSender = new Type(oFileMessageVO.getSender()
				.getDocument().getTypeID(), oFileMessageVO.getSender()
				.getDocument().getType(), oFileMessageVO.getSender()
				.getDocument().getValue());
		Type oTypeLocationSender = new Type(oFileMessageVO.getSender()
				.getLocation().getTypeID(), oFileMessageVO.getSender()
				.getLocation().getType(), oFileMessageVO.getSender()
				.getLocation().getValue());
		Type oTypeSectorSender = new Type(oFileMessageVO.getSender()
				.getSector().getTypeID(), oFileMessageVO.getSender()
				.getSector().getType(), oFileMessageVO.getSender().getSector()
				.getValue());

		Employee oSenderEmployee = new Employee(oTypeDocumentSender,
				oFileMessageVO.getSender().getID(), oFileMessageVO.getSender()
						.getName(), oFileMessageVO.getSender().getLastName(),
				oFileMessageVO.getSender().getUserName(), oFileMessageVO
						.getSender().getPassword(), oTypeLocationSender,
				oTypeSectorSender, oFileMessageVO.getSender().getMail(),
				oFileMessageVO.getSender().getPosition(), oFileMessageVO
						.getSender().getWorkingHour(), oFileMessageVO
						.getSender().getProfilePicture(), oFileMessageVO
						.getSender().getStatus(), oFileMessageVO.getSender()
						.getAdmin());

		Type oTypeDocumentReceiver = new Type(oFileMessageVO.getReceiver()
				.getDocument().getTypeID(), oFileMessageVO.getReceiver()
				.getDocument().getType(), oFileMessageVO.getReceiver()
				.getDocument().getValue());
		Type oTypeLocationReceiver = new Type(oFileMessageVO.getSender()
				.getLocation().getTypeID(), oFileMessageVO.getSender()
				.getLocation().getType(), oFileMessageVO.getSender()
				.getLocation().getValue());
		Type oTypeSectorReceiver = new Type(oFileMessageVO.getReceiver()
				.getSector().getTypeID(), oFileMessageVO.getReceiver()
				.getSector().getType(), oFileMessageVO.getReceiver()
				.getSector().getValue());

		Employee oReceiverEmployee = new Employee(oTypeDocumentReceiver,
				oFileMessageVO.getReceiver().getID(), oFileMessageVO
						.getReceiver().getName(), oFileMessageVO.getReceiver()
						.getLastName(), oFileMessageVO.getReceiver()
						.getUserName(), oFileMessageVO.getReceiver()
						.getPassword(), oTypeLocationReceiver,
				oTypeSectorReceiver, oFileMessageVO.getReceiver().getMail(),
				oFileMessageVO.getReceiver().getPosition(), oFileMessageVO
						.getReceiver().getWorkingHour(), oFileMessageVO
						.getReceiver().getProfilePicture(), oFileMessageVO
						.getReceiver().getStatus(), oFileMessageVO
						.getReceiver().getAdmin());

		FileMessage oFileMessage = new FileMessage(
				oFileMessageVO.getIDMessage(), oFileMessageVO.getFileMessage(),
				oFileMessageVO.getFileMessageName(), oSenderEmployee,
				oReceiverEmployee, oFileMessageVO.getDate());

		return oFileMessage;
	}
	
}
