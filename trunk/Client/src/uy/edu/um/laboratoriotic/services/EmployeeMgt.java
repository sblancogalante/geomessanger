package uy.edu.um.laboratoriotic.services;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface EmployeeMgt {

	public void addEmployee(EmployeeVO oEmployee) throws RemoteException, NotBoundException;
	
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException, NotBoundException;
	
}
