package uy.edu.um.laboratoriotic;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.communication.factory.employee.EmployeeRemoteFactory;
import uy.edu.um.laboratoriotic.communication.factory.general.GeneralRemoteFactory;
import uy.edu.um.laboratoriotic.communication.factory.message.TextMessageRemoteFactory;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.management.general.GeneralRemoteMgt;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageRemoteMgt;

/**
 * This class is the one that initializes the DB connection and the RMI
 * 
 * @author sblanco1 HC4UK9xY5pN6
 * 
 */
public class MainServer {

	public static void main(String[] args) throws RemoteException {

		/*
		 * Inicializamos la base de datos creando la tabla
		 */

		try {

			Registry oRegistry = LocateRegistry.createRegistry(BusinessFacade
					.getInstance().getPort());

			/*
			 * Establecemos la conexion RMI con el cliente
			 */
			String nameEmployee = "EmployeeRemoteMgr";

			EmployeeRemoteMgt oEmployeeRemoteMgt = EmployeeRemoteFactory
					.getInstance().getEmployeeRemoteMgt();

			EmployeeRemoteMgt oStubEmployee = (EmployeeRemoteMgt) UnicastRemoteObject
					.exportObject((Remote) oEmployeeRemoteMgt, 0);

			oRegistry.rebind(nameEmployee, oStubEmployee);

			/*
			 * Establecemos la conexion con los tipos
			 */
			String nameGeneral = "GeneralRemoteMgr";

			GeneralRemoteMgt oGeneralRemoteMgt = GeneralRemoteFactory
					.getInstance().getGeneralRemoteMgt();

			GeneralRemoteMgt oStubGeneral = (GeneralRemoteMgt) UnicastRemoteObject
					.exportObject((Remote) oGeneralRemoteMgt, 0);

			oRegistry.rebind(nameGeneral, oStubGeneral);

			/*
			 * Establecemos la conexion RMI con los mensajes
			 */
			String nameTextMessage = "TextMessageRemoteMgr";

			TextMessageRemoteMgt oTextMessageRemoteMgt = TextMessageRemoteFactory
					.getInstance().getTextMessageRemoteMgt();

			TextMessageRemoteMgt oStubTextMessage = (TextMessageRemoteMgt) UnicastRemoteObject
					.exportObject((Remote) oTextMessageRemoteMgt, 0);

			oRegistry.rebind(nameTextMessage, oStubTextMessage);

			System.out.println("Ready and waiting");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
