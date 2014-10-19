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

		// Employee oEmployee = oEmployeeMgt.getEmployee(oEmployeeVO);

		Employee oEmployee = new Employee(oEmployeeVO.getEmployeeID(),
				oEmployeeVO.getID(), oEmployeeVO.getName(),
				oEmployeeVO.getLastName(), oEmployeeVO.getUserName(),
				oEmployeeVO.getPassword(), oEmployeeVO.getLocation(),
				oEmployeeVO.getSector(), oEmployeeVO.getMail(),
				oEmployeeVO.getPosition(), oEmployeeVO.getWorkingHour(),
				oEmployeeVO.getProfilePicture(), oEmployeeVO.getStatus());

		try {
			oEmployeeMgt.addEmployee(oEmployee);
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException {
		// TODO Auto-generated method stub

		ArrayList<EmployeeVO> oListToReturn = new ArrayList<>();
		EmployeeVO oEmployeeVO;

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		ArrayList<Employee> oList = new ArrayList<>();
		try {
			oList = oEmployeeMgt.getEmployees();

			for (Employee iEmployee : oList) {
				oEmployeeVO = iEmployee.toVO();
				oListToReturn.add(oEmployeeVO);
			}

		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

		return oListToReturn;
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		Employee oEmployee;
		EmployeeVO oEmployeeVOToReturn = null;
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

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		Employee oEmployee;
		try {
			oEmployee = oEmployeeMgt.getEmployee(oEmployeeVO);
			oEmployeeMgt.removeEmployee(oEmployee.getEmployeeID());
		} catch (DataBaseConnection e) {
			// TODO Auto-generated catch block

		}

	}

}