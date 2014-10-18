package uy.edu.um.laboratoriotic.business.manager.employee;

import java.rmi.RemoteException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.employee.EmployeeDAOMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

/**
 * This class is the implementation of EmployeeMgt
 * 
 * @author sblanco1
 * 
 */
public class EmployeeMgr implements EmployeeMgt {

	/*
	 * Attributes of the class
	 */
	private static EmployeeMgr instance = null;

	/*
	 * Constructor
	 */
	private EmployeeMgr() {

	}

	/*
	 * Methods
	 */
	public static EmployeeMgr getInstance() {
		if (instance == null) {
			instance = new EmployeeMgr();
		}

		return instance;
	}

	/*
	 * This are the management implementation methods
	 */
	@Override
	public void addEmployee(Employee oEmployee) throws DataBaseConnection {
		// TODO Auto-generated method stub

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		Employee oNewEmployee = oEmployee;
		try {
			oNewDAOEmployee.addEmployee(oNewEmployee);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeEmployee(int oEmployeeID) throws DataBaseConnection {
		// TODO Auto-generated method stub

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		try {
			oNewDAOEmployee.removeEmployee(oEmployeeID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Employee modifyEmployee(Employee oEmployee) {
		// TODO Auto-generated method stub
		// EmployeeDAOMgt oNewDAOEmployee =
		// EmployeeDAOFactory.getEmployeeDAOMgt();

		return null;
	}

	@Override
	public Employee searchEmployee(int oEmployeeID) throws DataBaseConnection {
		// TODO Auto-generated method stub
		EmployeeVO oResult = null;

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		Employee oEmployee;
		try {
			oEmployee = oNewDAOEmployee.searchEmployee(oEmployeeID);
			if (oEmployee != null) {
				oResult = oEmployee.toVO();
			} else {
				System.out
						.println("No se encontro el usuario con identificacion "
								+ oEmployeeID);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Employee oToReturn = new Employee(oResult.getUserName(),
				oResult.getLocation(), oResult.getSector(), oResult.getStatus());

		return oToReturn;
	}

	@Override
	public ArrayList<Employee> getEmployees() throws DataBaseConnection {
		// TODO Auto-generated method stub

		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		ArrayList<Employee> list = new ArrayList<Employee>();
		try {
			list = oDAOEmployee.getEmployees();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public Employee getEmployee(EmployeeVO oEmployeeVO)
			throws DataBaseConnection {

		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		Employee oNewEmployee = null;
		try {
			oNewEmployee = oDAOEmployee.searchEmployee(oEmployeeVO
					.getEmployeeID());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Employee oEmployeeToReturn = oNewEmployee;

		return oEmployeeToReturn;
	}

}