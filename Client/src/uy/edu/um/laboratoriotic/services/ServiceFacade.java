package uy.edu.um.laboratoriotic.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import uy.edu.um.laboratoriotic.services.factory.employee.EmployeeFactory;
import uy.edu.um.laboratoriotic.services.factory.general.GeneralFactory;
import uy.edu.um.laboratoriotic.services.factory.message.FileMessageFactory;
import uy.edu.um.laboratoriotic.services.factory.message.TextMessageFactory;

public class ServiceFacade {

	/*
	 * Attributes of the class
	 */
	private static ServiceFacade instance = null;

	/*
	 * Constructor
	 */
	private ServiceFacade() {

	}

	public static ServiceFacade getInstance() {

		if (instance == null) {
			instance = new ServiceFacade();
		}

		return instance;
	}

	/*
	 * All the instances
	 */
	public EmployeeFactory getEmployeeFactory() {

		return EmployeeFactory.getInstance();
	}

	public GeneralFactory getGeneralFactory() {

		return GeneralFactory.getInstance();
	}

	public TextMessageFactory getTextMessageFactory() {

		return TextMessageFactory.getInstance();
	}

	public FileMessageFactory getFileMessageFactory() {

		return FileMessageFactory.getInstance();
	}

	/*
	 * This is a helping method
	 */
	public String getHost() {
	
		Properties oProperties = new Properties();
		
		try {
			oProperties.load(new FileInputStream("dataSource.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return oProperties.getProperty("host");
	}

	public int getPort() {

		Properties oProperties = new Properties();

		try {
			oProperties.load(new FileInputStream("dataSource.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return Integer.parseInt(oProperties.getProperty("port"));
	}

}
