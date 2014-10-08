package uy.edu.um.laboratoriotic.business;

import java.util.ArrayList;

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
	public void removeEmployee(int oEmployeeID) {
		// TODO Auto-generated method stub

		EmployeeDAOMgr oNewDAOEmployee = EmployeeDAOMgr.getInstance();
		oNewDAOEmployee.removeEmployee(oEmployeeID);

	}

	@Override
	public EmployeeVO modifyEmployee(EmployeeVO oEmployee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee searchEmployee(int oEmployeeID) {
		// TODO Auto-generated method stub
		Employee oResult = null;

		EmployeeDAOMgr oNewDAOEmployee = EmployeeDAOMgr.getInstance();
		Employee oEmployee = oNewDAOEmployee.searchEmployee(oEmployeeID);

		if (oEmployee != null) {
			oResult = oEmployee;
		} else {
			System.out.println("No se encontro el usuario con identificacion "
					+ oEmployeeID);
		}

		return oResult;
	}

	@Override
	public ArrayList<Employee> getEmployees() {
		// TODO Auto-generated method stub

		EmployeeDAOMgr oDAOEmployee = EmployeeDAOMgr.getInstance();
		ArrayList<Employee> oNewEmployee = oDAOEmployee.getEmployees();

		return oNewEmployee;
	}

	@Override
	public EmployeeVO getEmployeeVO(Employee oEmployee) {

		EmployeeDAOMgr oDAOEmployee = EmployeeDAOMgr.getInstance();
		Employee oNewEmployee = oDAOEmployee.searchEmployee(oEmployee
				.getEmployeeID());
		EmployeeVO newVOEmployee = this.getEmployeeVO(oNewEmployee);

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