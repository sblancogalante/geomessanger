package uy.edu.um.laboratoriotic.communication.manager.employee;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

public class EmployeeRemoteMgr implements EmployeeRemoteMgt {

	/*
	 * Attributes of the class
	 */
	private static EmployeeRemoteMgr instance = null;

	/*
	 * Constructors
	 */
	private EmployeeRemoteMgr() throws RemoteException {

	}

	/*
	 * Methods implementation
	 */
	public static EmployeeRemoteMgr getInstance() throws RemoteException {

		if (instance == null) {
			instance = new EmployeeRemoteMgr();
		}

		return instance;
	}

	@Override
	public void addEmployee(EmployeeVO oEmployeeVO) throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();
		
		Employee oEmployee = oEmployeeMgt.getEmployeeVO(oEmployeeVO);
		
		oEmployeeMgt.addEmployee(oEmployee);
	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeMgt oEmployee = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		ArrayList<EmployeeVO> oList = oEmployee.getEmployees();

		return oList;
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub
		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		Employee oEmployee = oEmployeeMgt.getEmployeeVO(oEmployeeVO);

		EmployeeVO oEmployeeToReturn = new EmployeeVO(oEmployee.getUserName(),
				oEmployee.getLocation(), oEmployee.getSector(),
				oEmployee.getStatus());

		return oEmployeeToReturn;
	}

	@Override
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();
		
		Employee oEmployee = oEmployeeMgt.getEmployeeVO(oEmployeeVO);
		
		oEmployeeMgt.removeEmployee(oEmployee.getEmployeeID());
	}

}
