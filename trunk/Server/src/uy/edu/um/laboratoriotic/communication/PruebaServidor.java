package uy.edu.um.laboratoriotic.communication;

import java.rmi.RemoteException;

/**
 * 
 * @author sblanco1
 *
 */
public class PruebaServidor {

	public static void main(String[] args) throws RemoteException {

		EmployeeRemoteMgr.getInstance();
	}

}