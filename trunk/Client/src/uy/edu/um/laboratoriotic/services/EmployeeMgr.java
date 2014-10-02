package uy.edu.um.laboratoriotic.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class EmployeeMgr implements EmployeeMgt{

	private static EmployeeMgr instance = null;

	private EmployeeMgr() {

	}

	public static EmployeeMgr getInstance() {
		if (instance == null) {
			instance = new EmployeeMgr();
		}

		return instance;
	}

	@Override
	public void addEmployee(EmployeeVO oEmployee) throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		String sObjectService = "Employee"; 
		Registry oRegitry = LocateRegistry.getRegistry(1099); 
		EmployeeRemoteMgt oEmployeeRemoteMgt = (EmployeeRemoteMgt)oRegitry.lookup(sObjectService); 
		
		
	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
