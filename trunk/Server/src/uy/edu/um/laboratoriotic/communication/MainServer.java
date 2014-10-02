package uy.edu.um.laboratoriotic.communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uy.edu.um.laboratoriotic.business.BusinessFacade;

/**
 * This class is the one that initializes the DB connection and the RMI
 * 
 * @author sblanco1
 * 
 */
public class MainServer {

	public static void main(String[] args) throws RemoteException {

		String name = "EmployeeRemoteMgr";

		EmployeeRemoteFactory oUserRemoteMgr = BusinessFacade.getInstance()
				.getEmployeeRemoteFactory();

		EmployeeRemoteMgt oStubUser = (EmployeeRemoteMgt) UnicastRemoteObject.exportObject(
				(Remote) oUserRemoteMgr, 0);

		Registry oRegistry = LocateRegistry.createRegistry(1099);
		
		oRegistry.rebind(name, oStubUser);

	}

}
