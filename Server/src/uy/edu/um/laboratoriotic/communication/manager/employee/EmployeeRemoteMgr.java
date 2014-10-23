package uy.edu.um.laboratoriotic.communication.manager.employee;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This class is the implementation of EmployeeRemoteMgt
 * 
 * @author sblanco1
 * 
 */
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

	public static EmployeeRemoteMgr getInstance() throws RemoteException {

		if (instance == null) {
			instance = new EmployeeRemoteMgr();
		}

		return instance;
	}

	/*
	 * Methods implementation
	 */
	@Override
	public void addEmployee(EmployeeVO oEmployeeVO) throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();	

		try {
			oEmployeeMgt.addEmployee(oEmployeeVO);
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException {
		// TODO Auto-generated method stub

		ArrayList<EmployeeVO> oListToReturn = new ArrayList<>();	

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();
		
		try {
			oListToReturn = oEmployeeMgt.getEmployees();
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		return oListToReturn;
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub

		Employee oEmployee;
		EmployeeVO oEmployeeVOToReturn = null;
		
		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();		
		
		try {
			oEmployee = oEmployeeMgt.getEmployee(oEmployeeVO);
			oEmployeeVOToReturn = oEmployee.toVO();
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		return oEmployeeVOToReturn;
	}

	@Override
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException {
		// TODO Auto-generated method stub

		Employee oEmployee;
		
		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();		
		
		try {
			oEmployee = oEmployeeMgt.getEmployee(oEmployeeVO);
			oEmployeeMgt.removeEmployee(oEmployee.getEmployeeID());
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

	}

	@Override
	public boolean checkLogin(EmployeeVO oEmployeeVO) throws RemoteException {

		boolean toReturn = false;

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();		

		try {
			toReturn = oEmployeeMgt.checkLogin(oEmployeeVO);
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
		}

		return toReturn;
	}
	
}