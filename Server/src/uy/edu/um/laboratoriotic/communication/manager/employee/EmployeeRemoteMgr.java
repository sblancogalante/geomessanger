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

		Employee oEmployee = oEmployeeMgt.getEmployeeVO(oEmployeeVO);

		oEmployeeMgt.addEmployee(oEmployee);
	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<EmployeeVO> oListToReturn = new ArrayList<>();
		EmployeeVO oEmployee;
		int index = 0;

		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		ArrayList<Employee> oList = oEmployeeMgt.getEmployees();

		while (oList.iterator().hasNext()) {
			oEmployee = oList.get(index).toVO();
			oListToReturn.add(oEmployee);
			index++;
		}

		return oListToReturn;
	}

	@Override
	public EmployeeVO getEmployee(EmployeeVO oEmployeeVO)
			throws RemoteException {
		// TODO Auto-generated method stub
		EmployeeMgt oEmployeeMgt = BusinessFacade.getInstance()
				.getEmployeeFactory().getEmployeeMgt();

		Employee oEmployee = oEmployeeMgt.getEmployeeVO(oEmployeeVO);

		EmployeeVO oEmployeeToReturn = oEmployee.toVO();

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
