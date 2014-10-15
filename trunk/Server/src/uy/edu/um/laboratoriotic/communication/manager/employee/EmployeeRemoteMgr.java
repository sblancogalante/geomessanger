package uy.edu.um.laboratoriotic.communication.manager.employee;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import uy.edu.um.laboratoriotic.business.BusinessFacade;
import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.management.employee.EmployeeMgt;
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

		Employee oEmployee = new Employee(oEmployeeVO.getUserName(),
				oEmployeeVO.getEmployeeID(), oEmployeeVO.getLocation(),
				oEmployeeVO.getSector(), oEmployeeVO.getStatus());

		oEmployeeMgt.addEmployee(oEmployee);
	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<EmployeeVO> oListToReturn = new ArrayList<>();
		EmployeeVO oEmployee;
		
		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		ArrayList<Employee> oList = oEmployeeMgt.getEmployees();

		while (oList.iterator().hasNext()) {
			oEmployee = oList.iterator().next().toVO();
			oListToReturn.add(oEmployee);		
		}

		return oListToReturn;
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub
		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		Employee oEmployee = oEmployeeMgt.getEmployee(oEmployeeVO);

		EmployeeVO oEmployeeToReturn = oEmployee.toVO();

		return oEmployeeToReturn;
	}

	@Override
	public void removeEmployee(EmployeeVO oEmployeeVO) throws RemoteException {
		// TODO Auto-generated method stub

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		Employee oEmployee = oEmployeeMgt.getEmployee(oEmployeeVO);

		oEmployeeMgt.removeEmployee(oEmployee.getEmployeeID());
	}

}
