package uy.edu.um.laboratoriotic.services.management.employee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public interface EmployeeRemoteMgt extends Remote {
	
	public void addEmployee(EmployeeVO oEmployee) throws RemoteException;	

	public EmployeeVO getEmployee(EmployeeVO oEmployee) throws RemoteException;

	public ArrayList<EmployeeVO> getEmployees() throws RemoteException;

	public void removeEmployee(EmployeeVO oEmployee) throws RemoteException;

}
