package uy.edu.um.laboratoriotic.communication;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.Employee;
import uy.edu.um.laboratoriotic.business.EmployeeFactory;
import uy.edu.um.laboratoriotic.business.EmployeeMgt;
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

		EmployeeMgt oEmployee = (EmployeeMgt) EmployeeFactory.getInstance()
				.getEmployeeMgt();
		Employee oNewEmployeeToAdd = oEmployee.getEmployeeVO(oEmployeeVO);
		oEmployee.addEmployee(oNewEmployeeToAdd);
	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeMgt oEmployee = EmployeeFactory.getInstance().getEmployeeMgt();

		ArrayList<EmployeeVO> list = oEmployee.getEmployees();

		return list;
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub
		EmployeeMgt oEmployeeMgt = (EmployeeMgt) BusinessFacade.getInstance()
				.getEmployeeRemoteFactory();

		Employee oEmployee = oEmployeeMgt.getEmployeeVO(oEmployeeVO);

		EmployeeVO oEmployeeToReturn = new EmployeeVO(oEmployee.getUserName(),
				oEmployee.getLocation(), oEmployee.getSector(),
				oEmployee.getStatus());

		return oEmployeeToReturn;
	}

	@Override
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeMgt oRemoteEmployee = EmployeeFactory.getInstance()
				.getEmployeeMgt();
		Employee oEmployee = oRemoteEmployee.getEmployeeVO(oEmployeeVO);
		oRemoteEmployee.removeEmployee(oEmployee.getEmployeeID());
	}

}
