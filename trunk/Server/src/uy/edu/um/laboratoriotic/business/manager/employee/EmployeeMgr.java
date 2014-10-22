package uy.edu.um.laboratoriotic.business.manager.employee;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		Employee oEmployee;
		Employee  oEmployeeToReturn = null;
		try {
			oEmployee = oNewDAOEmployee.searchEmployee(oEmployeeID);
			if (oEmployee != null) {
				oEmployeeToReturn = oEmployee;
			} else {
				System.out
						.println("No se encontro el usuario con identificacion "
								+ oEmployeeID);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return oEmployeeToReturn;
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
		Employee oEmployeeToReturn = null;
		try {
			oEmployeeToReturn = oDAOEmployee.searchEmployee(oEmployeeVO
					.getEmployeeID());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return oEmployeeToReturn;
	}
	
	@Override
	public boolean checkLogin(Employee oEmployee) throws DataBaseConnection {
		
		boolean toReturn = false;
		
		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		String crypted = this.hashPassword(oEmployee.getPassword());
		try {
			toReturn = oDAOEmployee.checkLogin(oEmployee.getUserName(), crypted);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
		
	}
	
	/*
	 * Helping methods 
	 */
	public String hashPassword(String oPassword) {
		
		String password = null;
		
		try {
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(oPassword.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			password = hash.toString(16);
			
		} catch (NoSuchAlgorithmException e) {
			// No hacer nada
		}
		
		return password;
	}

}