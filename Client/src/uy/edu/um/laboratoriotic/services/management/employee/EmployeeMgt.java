package uy.edu.um.laboratoriotic.services.management.employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;


public interface EmployeeMgt {

	public void addEmployee(EmployeeVO oEmployee) throws RemoteException, NotBoundException;
	
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException, NotBoundException;
	
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException, NotBoundException;
	
}
