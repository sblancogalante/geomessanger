package uy.edu.um.laboratoriotic.communication.manager.employee;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.services.management.employee.EmployeeRemoteMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;
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
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException {
		// TODO Auto-generated method stub

		Employee oEmployee;

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		try {
			oEmployee = oEmployeeMgt.searchEmployee(oEmployeeVO.getUserName());
			oEmployeeMgt.removeEmployee(oEmployee.getUserName());
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

	}

	@Override
	public EmployeeVO searchEmployee(String oUserName) throws RemoteException {
		// TODO Auto-generated method stub

		Employee oEmployee;
		EmployeeVO oEmployeeVOToReturn = null;

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		try {
			
			oEmployee = oEmployeeMgt.searchEmployee(oUserName);
			
			if (oEmployee != null)
				oEmployeeVOToReturn = oEmployee.toVO();
			
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		return oEmployeeVOToReturn;
	}

	@Override
	public EmployeeVO modifyEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub

		Employee oEmployee;
		EmployeeVO oEmployeeVOToReturn = null;

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		try {
			oEmployee = oEmployeeMgt.modifyEmployee(oEmployeeVO);
			oEmployeeVOToReturn = oEmployee.toVO();
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		return oEmployeeVOToReturn;

	}

	@Override
	public boolean checkLogin(EmployeeFilterVO oEmployeeFilterVO)
			throws RemoteException {

		boolean toReturn = false;

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		try {
			toReturn = oEmployeeMgt.checkLogin(oEmployeeFilterVO);
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
		}

		return toReturn;
	}

	@Override
	public EmployeeVO getLoginEmployee(EmployeeFilterVO oEmployeeFilterVO)
			throws RemoteException {

		Employee oEmployeeToReturn = null;

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		try {
			oEmployeeToReturn = oEmployeeMgt
					.getLoginEmployee(oEmployeeFilterVO);

		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block
		}

		return oEmployeeToReturn.toVO();
	}

}