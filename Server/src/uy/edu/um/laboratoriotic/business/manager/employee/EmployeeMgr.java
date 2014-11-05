package uy.edu.um.laboratoriotic.business.manager.employee;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.helper.Helper;
import uy.edu.um.laboratoriotic.business.management.employee.EmployeeMgt;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.factory.employee.EmployeeDAOFactory;
import uy.edu.um.laboratoriotic.persistence.management.employee.EmployeeDAOMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeFilterVO;
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
	public void addEmployee(EmployeeVO oEmployeeVO) throws DataBaseConnection,
			RemoteException {
		// TODO Auto-generated method stub

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();

		// Employee oEmployee = oEmployeeMgt.getEmployee(oEmployeeVO);		

		Employee oEmployee = Helper.modularizeEmployee(oEmployeeVO);
		oNewDAOEmployee.addEmployee(oEmployee);

	}

	@Override
	public void removeEmployee(String oUserName) throws DataBaseConnection,
			RemoteException {
		// TODO Auto-generated method stub

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		oNewDAOEmployee.removeEmployee(oUserName);

	}

	@Override
	public ArrayList<EmployeeVO> getEmployees() throws DataBaseConnection,
			RemoteException {
		// TODO Auto-generated method stub

		EmployeeVO oEmployeeVO;
		ArrayList<EmployeeVO> oListToReturn = new ArrayList<>();
		ArrayList<Employee> oList = new ArrayList<>();

		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();

		oList = oDAOEmployee.getEmployees();

		for (Employee iEmployee : oList) {
			oEmployeeVO = iEmployee.toVO();
			oListToReturn.add(oEmployeeVO);
		}

		return oListToReturn;
	}

	@Override
	public Employee searchEmployee(String oUserName) throws DataBaseConnection,
			RemoteException {
		// TODO Auto-generated method stub

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		Employee oEmployee;
		Employee oEmployeeToReturn = null;
		oEmployee = oNewDAOEmployee.searchEmployee(oUserName);

		if (oEmployee != null) {
			oEmployeeToReturn = oEmployee;
		} else {
			System.out.println("No se encontro el usuario con identificacion "
					+ oUserName);
		}

		return oEmployeeToReturn;
	}

	@Override
	public Employee modifyEmployee(EmployeeVO oEmployeeVO)
			throws DataBaseConnection, RemoteException {
		// TODO Auto-generated method stub

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		Employee oEmployee;
		Employee oEmployeeToReturn = null;

		oEmployee = oNewDAOEmployee.searchEmployee(oEmployeeVO.getUserName());

		if (oEmployee != null) {
			oEmployeeToReturn = modifyEmployee(oEmployeeVO);
		} else {
			System.out.println("No se encontro el usuario con identificacion "
					+ oEmployeeVO.getEmployeeID());
		}

		return oEmployeeToReturn;
	}

	@Override
	public boolean checkLogin(EmployeeFilterVO oEmployeeFilterVO)
			throws DataBaseConnection, RemoteException {

		boolean toReturn = false;

		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		String crypted = this.hashEncriptation(oEmployeeFilterVO.getPassword());

		toReturn = oDAOEmployee.checkLogin(oEmployeeFilterVO.getUserName(),
				crypted);
		return toReturn;

	}

	@Override
	public Employee getLoginEmployee(EmployeeFilterVO oEmployeeFilterVO)
			throws DataBaseConnection, RemoteException {

		Employee oEmployeeToReturn = null;

		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		String crypted = this.hashEncriptation(oEmployeeFilterVO.getPassword());

		oEmployeeToReturn = oDAOEmployee.getLoginEmployee(
				oEmployeeFilterVO.getUserName(), crypted);

		return oEmployeeToReturn;
	}

	/*
	 * Helping methods
	 */
	public String hashEncriptation(String oPassword) {

		String newPassword = null;

		try {

			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(oPassword.getBytes());
			BigInteger hash = new BigInteger(1, md5.digest());
			newPassword = hash.toString(16);

		} catch (NoSuchAlgorithmException e) {
			// No hacer nada
		}

		return newPassword;
	}

}