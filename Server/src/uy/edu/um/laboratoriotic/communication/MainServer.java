package uy.edu.um.laboratoriotic.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.persistance.EmployeeDAOFactory;
import uy.edu.um.laboratoriotic.persistance.EmployeeDAOMgt;
import uy.edu.um.laboratoriotic.services.EmployeeRemoteMgt;

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

		BusinessFacade.getInstance()
				.getEmployeeRemoteFactory();
		EmployeeRemoteMgt oUserRemoteMgr = EmployeeRemoteFactory.getInstance().getEmployeeRemoteMgt();

		EmployeeRemoteMgt oStubUser = (EmployeeRemoteMgt) UnicastRemoteObject.exportObject(
				(Remote) oUserRemoteMgr, 0);

		Registry oRegistry = LocateRegistry.createRegistry(1099);
		
		oRegistry.rebind(name, oStubUser);

	}

}
