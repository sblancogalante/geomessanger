package uy.edu.um.laboratoriotic.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.communication.factory.employee.EmployeeRemoteFactory;
import uy.edu.um.laboratoriotic.communication.factory.message.TextMessageRemoteFactory;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
import uy.edu.um.laboratoriotic.persistence.factory.message.TextMessageDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.employee.EmployeeDAOMgt;
import uy.edu.um.laboratoriotic.persistence.management.message.TextMessageDAOMgt;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.management.message.TextMessageRemoteMgt;

/**
 * This class is the one that initializes the DB connection and the RMI
 * 
 * @author sblanco1
 * 
 */
public class MainServer {

	public static void main(String[] args) throws RemoteException {

		/*
		 * Inicializamos la base de datos creando la tabla
		 */
		EmployeeDAOMgt oEmployeeDAOMgt = EmployeeDAOFactory.getEmployeeDAOMgt();
		TextMessageDAOMgt oTextMessageDAOMgt = TextMessageDAOFactory
				.getTextMessageDAOMgt();
		try {
			oEmployeeDAOMgt.createTable();
			oEmployeeDAOMgt.addEmployee(new Employee("root", "root"));
			oTextMessageDAOMgt.createTable();
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Registry oRegistry = LocateRegistry.createRegistry(1099);

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
		 * Establecemos la conexion RMI con los mensajes
		 */		
		String nameTextMessage = "TextMessageRemoteMgr";

		TextMessageRemoteMgt oTextMessageRemoteMgt = TextMessageRemoteFactory
				.getInstance().getTextMessageRemoteMgt();

		TextMessageRemoteMgt oStubTextMessage = (TextMessageRemoteMgt) UnicastRemoteObject
				.exportObject((Remote) oTextMessageRemoteMgt, 0);

		oRegistry.rebind(nameTextMessage, oStubTextMessage);

	}
}
