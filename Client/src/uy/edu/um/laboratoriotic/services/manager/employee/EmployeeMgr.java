package uy.edu.um.laboratoriotic.services.manager.employee;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.services.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This is the implementation of EmployeeMgt
 * 
 * @author sblanco1
 * 
 */
public class EmployeeMgr implements EmployeeMgt {

	/*
	 * Attributes of the class
	 */
	private static EmployeeMgr instance = null;

	/*
	 * Constructors
	 */
	private EmployeeMgr() {

	}

	public static EmployeeMgr getInstance() {
		
		if (instance == null) {
			instance = new EmployeeMgr();
		}

		return instance;
	}

	/*
	 * Management implementation methods
	 */
	@Override
	public void addEmployee(EmployeeVO oEmployeeVO) throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub

		EmployeeRemoteMgt oEmployeeRemoteMgt = lookUp("EmployeeRemoteMgr", 1099);
		oEmployeeRemoteMgt.addEmployee(oEmployeeVO);

	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException,
			NotBoundException {
		// TODO Auto-generated method stub
		
		ArrayList<EmployeeVO> oListToReturn = new ArrayList<EmployeeVO>();

		EmployeeRemoteMgt oEmployeeRemoteMgt = lookUp("EmployeeRemoteMgr", 1099);
		oListToReturn = oEmployeeRemoteMgt.getEmployees();

		return oListToReturn;
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
	private EmployeeRemoteMgt lookUp(String sObjectService, int oPortNumber)
			throws RemoteException, NotBoundException {

		EmployeeRemoteMgt oReturn;

		Registry oRegistry = LocateRegistry.getRegistry(oPortNumber);
		oReturn = (EmployeeRemoteMgt) oRegistry.lookup(sObjectService);

		return oReturn;
	}

}
