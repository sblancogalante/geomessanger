package uy.edu.um.laboratoriotic.business;

import java.rmi.RemoteException;

import uy.edu.um.laboratoriotic.business.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.business.factory.general.GeneralFactory;
import uy.edu.um.laboratoriotic.business.factory.message.TextMessageFactory;
import uy.edu.um.laboratoriotic.communication.factory.employee.EmployeeRemoteFactory;
import uy.edu.um.laboratoriotic.communication.factory.general.GeneralRemoteFactory;
import uy.edu.um.laboratoriotic.communication.factory.message.TextMessageRemoteFactory;

/**
 * This class recognizes all interfaces of communication module and creates
 * instances to isolate the business module of the communication module
 * 
 * @author sblanco1
 * 
 */
public class BusinessFacade {

	/*
	 * Attributes of the class
	 */
	private static BusinessFacade instance = null;

	private BusinessFacade() {

	}

	public static BusinessFacade getInstance() {

		if (instance == null) {
			instance = new BusinessFacade();
		}

		return instance;

	}

	/*
	 * All the instances
	 */
	public EmployeeFactory getEmployeeFactory() {

		return EmployeeFactory.getInstance();
	}

	public EmployeeRemoteFactory getEmployeeRemoteFactory()
			throws RemoteException {

		return EmployeeRemoteFactory.getInstance();
	}

	public GeneralFactory getGeneralFactory() {

		return GeneralFactory.getInstance();
	}

	public GeneralRemoteFactory getGeneralRemoteFactory()
			throws RemoteException {

		return GeneralRemoteFactory.getInstance();
	}

	public TextMessageFactory getTextMessageFactory() {

		return TextMessageFactory.getInstance();
	}

	public TextMessageRemoteFactory getTextMessageRemoteFactory()
			throws RemoteException {

		return TextMessageRemoteFactory.getInstance();
	}

}
