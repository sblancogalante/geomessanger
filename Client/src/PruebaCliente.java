import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import uy.edu.um.laboratoriotic.communication.EmployeeRemoteMgt;


public class PruebaCliente {

	public static void main(String[] args) throws RemoteException, NotBoundException {
		
		String sObjectService = "Employee"; 
		Registry oRegitry = LocateRegistry.getRegistry(1099); 
		EmployeeRemoteMgt oEmployeeRemoteMgt = (EmployeeRemoteMgt)oRegitry.lookup(sObjectService); 
		String sReturn = oEmployeeRemoteMgt.msg("Pepito"); 
		System.out.println(sReturn);
		
	}
}
