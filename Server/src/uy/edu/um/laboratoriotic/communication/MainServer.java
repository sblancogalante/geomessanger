package uy.edu.um.laboratoriotic.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uy.edu.um.laboratoriotic.communication.factory.employee.EmployeeRemoteFactory;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.employee.EmployeeDAOMgt;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;

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
		EmployeeDAOMgt oMgt = EmployeeDAOFactory.getEmployeeDAOMgt();
		oMgt.createTable();
		
		/*
		 * Establecemos la conexion RMI con el cliente
		 */
		String name = "EmployeeRemoteMgr";

		EmployeeRemoteMgt oUserRemoteMgr = EmployeeRemoteFactory.getInstance().getEmployeeRemoteMgt();

		EmployeeRemoteMgt oStubUser = (EmployeeRemoteMgt) UnicastRemoteObject.exportObject(
				(Remote) oUserRemoteMgr, 0);

		Registry oRegistry = LocateRegistry.createRegistry(1099);
		
		oRegistry.rebind(name, oStubUser);

	}

}
