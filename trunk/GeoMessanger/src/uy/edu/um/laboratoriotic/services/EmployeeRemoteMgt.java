package uy.edu.um.laboratoriotic.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface EmployeeRemoteMgt extends Remote {
	
	public void addEmployee(EmployeeVO oEmployee) throws RemoteException;

	public String msg(String name) throws RemoteException;

	public EmployeeVO getEmployee(EmployeeVO oEmployee) throws RemoteException;

	public ArrayList<EmployeeVO> getEmployees();

	void removeEmployee(EmployeeVO oEmployee) throws RemoteException;

}
