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
		
		String sObjectService = "EmployeeRemoteMgr"; 
		Registry oRegitry = LocateRegistry.getRegistry(1099); 
		EmployeeRemoteMgt oEmployeeRemoteMgt = (EmployeeRemoteMgt)oRegitry.lookup(sObjectService); 
		oEmployeeRemoteMgt.addEmployee(oEmployee);
		
	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>(); 
		
		String sObjectService = "EmployeeRemoteMgr"; 
		Registry oRegitry = LocateRegistry.getRegistry(1099); 
		EmployeeRemoteMgt oEmployeeRemoteMgt = (EmployeeRemoteMgt)oRegitry.lookup(sObjectService); 
		list = oEmployeeRemoteMgt.getEmployees();
		
		return list;
	}
	
	
}
