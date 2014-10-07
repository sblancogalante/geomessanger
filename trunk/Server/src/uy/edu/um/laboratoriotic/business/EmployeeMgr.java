package uy.edu.um.laboratoriotic.business;

import uy.edu.um.laboratoriotic.persistance.EmployeeDAOMgr;
import uy.edu.um.laboratoriotic.services.EmployeeVO;

/**
 * this class is the implementation of EmployeeMgt
 * 
 * @author sblanco1
 * 
 */
public class EmployeeMgr implements EmployeeMgt {

	private static EmployeeMgr instance = null;

	private EmployeeMgr() {

	}

	public static EmployeeMgr getInstance() {
		if (instance == null) {
			instance = new EmployeeMgr();
		}

		return instance;
	}

	@Override
	public void addEmployee(Employee oEmployee) {
		// TODO Auto-generated method stub
		EmployeeDAOMgr oNewDAOEmployee = EmployeeDAOMgr.getInstance();
		Employee oNewEmployee = new Employee(oEmployee.getName(),
				oEmployee.getLastName(), oEmployee.getEmployeeID(),
				oEmployee.getLocation(), oEmployee.getSector(),
				oEmployee.getStatus());
		oNewDAOEmployee.addEmployee(oNewEmployee);

	}

	@Override
	public void removeEmployee() {
		// TODO Auto-generated method stub

	}

	@Override
	public EmployeeVO modifyEmployee(EmployeeVO oEmployee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee searchEmployee(String oLastName, String oName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee searchEmployee(String oUserName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmployeeVO getEmployeeVO(Employee oEmployee) {
		EmployeeDAOMgr oDAOEmployee = EmployeeDAOMgr.getInstance();
		Employee oNewEmployee = oDAOEmployee.searchEmployee(oEmployee
				.getEmployeeID());
		EmployeeVO newVOEmployee = this.getEmployeeVO(oEmployee);

		return newVOEmployee;
	}

	@Override
	public Employee getEmployee(EmployeeVO oEmployee) {
		// TODO Auto-generated method stub
		String oName = oEmployee.getName();
		String oLastName = oEmployee.getLastName();
		int oEmployeeID = oEmployee.getEmployeeID();
		String oLocation = oEmployee.getLocation();
		String oSector = oEmployee.getSector();
		boolean oStatus = oEmployee.getStatus();

		Employee oNewVOEmployee = new Employee(oName, oLastName, oEmployeeID,
				oLocation, oSector, oStatus);

		return oNewVOEmployee;

	}

}
