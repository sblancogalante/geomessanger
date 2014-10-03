package uy.edu.um.laboratoriotic.communication;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.Employee;
import uy.edu.um.laboratoriotic.business.EmployeeFactory;
import uy.edu.um.laboratoriotic.business.EmployeeMgt;
import uy.edu.um.laboratoriotic.business.EmployeeVO;

public class EmployeeRemoteMgr implements EmployeeRemoteMgt {

	/*
	 * Attributes of the class
	 */
	private static EmployeeRemoteMgr instance = null;

	/*
	 * Constructors
	 */
	private EmployeeRemoteMgr() throws RemoteException {

		// String name = "Employee";
		// EmployeeRemoteMgt oStub = (EmployeeRemoteMgt) UnicastRemoteObject
		// .exportObject(this, 0);
		// Registry oRegistry = LocateRegistry.createRegistry(1099);
		// oRegistry.rebind(name, oStub);

	}

	public String msg(String name) throws RemoteException {

		return name;
	}

	public static EmployeeRemoteMgr getInstance() throws RemoteException {

		if (instance == null) {
			instance = new EmployeeRemoteMgr();
		}

		return instance;
	}

	@Override
	public void addEmployee(EmployeeVO oEmployeeVO) throws RemoteException {
		BusinessFacade.getInstance().getEmployeeFactory();
		// TODO Auto-generated method stub
		EmployeeMgt oEmployee = EmployeeFactory.getInstance().getEmployeeMgt();
		Employee oNewEmployeeToAdd = oEmployee.getEmployee(oEmployeeVO);
		oEmployee.addEmployee(oNewEmployeeToAdd);
	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO oEmployee) throws RemoteException {
		// TODO Auto-generated method stub
		EmployeeRemoteMgt oRemoteEmployee = (EmployeeRemoteMgt) BusinessFacade.getInstance()
				.getEmployeeRemoteFactory();

		EmployeeVO oEmployeeVO = oRemoteEmployee.getEmployee(oEmployee);

		return oEmployeeVO;
	}

}
