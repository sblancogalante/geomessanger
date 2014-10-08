package uy.edu.um.laboratoriotic.communication;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.services.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.EmployeeVO;

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
		// TODO Auto-generated method stub
		
		EmployeeRemoteMgt oEmployee = (EmployeeRemoteMgt) EmployeeRemoteFactory
				.getInstance().getEmployeeRemoteMgt();
		EmployeeVO oNewEmployeeToAdd = oEmployee.getEmployee(oEmployeeVO);
		oEmployee.addEmployee(oNewEmployeeToAdd);
	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeRemoteMgt oRemoteEmployee = EmployeeRemoteFactory.getInstance()
				.getEmployeeRemoteMgt();
		
		ArrayList<EmployeeVO> list = oRemoteEmployee.getEmployees();

		return list;
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO oEmployee) throws RemoteException {
		// TODO Auto-generated method stub
		EmployeeRemoteMgt oRemoteEmployee = (EmployeeRemoteMgt) BusinessFacade
				.getInstance().getEmployeeRemoteFactory();

		EmployeeVO oEmployeeVO = oRemoteEmployee.getEmployee(oEmployee);

		return oEmployeeVO;
	}

	@Override
	public void removeEmployee(EmployeeVO oEmployee) throws RemoteException {
		// TODO Auto-generated method stub
		
		EmployeeRemoteMgt oRemoteEmployee = EmployeeRemoteFactory.getInstance()
				.getEmployeeRemoteMgt();
		EmployeeVO oEmployeeVO = oRemoteEmployee.getEmployee(oEmployee);
		oRemoteEmployee.removeEmployee(oEmployeeVO);
	}

}
