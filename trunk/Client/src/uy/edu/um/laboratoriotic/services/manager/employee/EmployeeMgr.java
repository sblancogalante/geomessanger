package uy.edu.um.laboratoriotic.services.manager.employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public class EmployeeMgr implements EmployeeMgt {

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
	public void addEmployee(EmployeeVO oEmployee) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

		String sObjectService = "EmployeeRemoteMgr";
		Registry oRegitry = LocateRegistry.getRegistry(1099);
		EmployeeRemoteMgt oEmployeeRemoteMgt = (EmployeeRemoteMgt) oRegitry
				.lookup(sObjectService);
		oEmployeeRemoteMgt.addEmployee(oEmployee);

	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub
		ArrayList<EmployeeVO> list = new ArrayList<EmployeeVO>();

		EmployeeRemoteMgt oEmployeeRemoteMgt = lookUp("EmployeeRemoteMgr", 1099);
		list = oEmployeeRemoteMgt.getEmployees();

		return list;
	}

	@Override
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub
		EmployeeRemoteMgt oEmployeeRemoteMgt = lookUp("EmployeeRemoteMgr", 1099);
		oEmployeeRemoteMgt.removeEmployee(oEmployeeVO);
	}
	
	/*
	 * Helping methods
	 */
	private EmployeeRemoteMgt lookUp(String sObjectService, int oPortNumber) throws RemoteException, NotBoundException{
		EmployeeRemoteMgt oReturn;
		
		Registry oRegistry = LocateRegistry.getRegistry(oPortNumber);
		oReturn = (EmployeeRemoteMgt)oRegistry.lookup(sObjectService);
		
		return oReturn;
		
	}
	

}
