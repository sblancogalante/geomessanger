package uy.edu.um.laboratoriotic.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import uy.edu.um.laboratoriotic.business.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.business.factory.general.GeneralFactory;
import uy.edu.um.laboratoriotic.business.factory.message.FileMessageFactory;
import uy.edu.um.laboratoriotic.business.factory.message.TextMessageFactory;
import uy.edu.um.laboratoriotic.communication.factory.employee.EmployeeRemoteFactory;
import uy.edu.um.laboratoriotic.communication.factory.general.GeneralRemoteFactory;
import uy.edu.um.laboratoriotic.communication.factory.message.FileMessageRemoteFactory;
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

	/*
	 * Constructor
	 */
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

	public FileMessageFactory getFileMessageFactory() {

		return FileMessageFactory.getInstance();
	}

	public FileMessageRemoteFactory getFileMessageRemoteFactory()
			throws RemoteException {

		return FileMessageRemoteFactory.getInstance();
	}
	
	/*
	 * This is a helping method
	 */
	public int getPort() {
		
		Properties oProperties= new Properties();
		
		try {
			oProperties.load(new FileInputStream("dataSource.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Integer.parseInt(oProperties.getProperty("port"));
	}

}
