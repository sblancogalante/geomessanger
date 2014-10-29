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
	public void addEmployee(EmployeeVO oEmployeeVO) throws DataBaseConnection {
		// TODO Auto-generated method stub

		EmployeeDAOMgt oNewDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();

		// Employee oEmployee = oEmployeeMgt.getEmployee(oEmployeeVO);

		Employee oEmployee = new Employee(
				oEmployeeVO.getID(), oEmployeeVO.getName(),
				oEmployeeVO.getLastName(), oEmployeeVO.getUserName(),
				oEmployeeVO.getPassword(), oEmployeeVO.getLocation(),
				oEmployeeVO.getSector(), oEmployeeVO.getMail(),
				oEmployeeVO.getPosition(), oEmployeeVO.getWorkingHour(),
				oEmployeeVO.getProfilePicture(), oEmployeeVO.getStatus());

		try {
			oNewDAOEmployee.addEmployee(oEmployee);
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
	public Employee modifyEmployee(EmployeeVO oEmployeeVO) {
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
		Employee oEmployeeToReturn = null;
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
	public ArrayList<EmployeeVO> getEmployees() throws DataBaseConnection {
		// TODO Auto-generated method stub

		EmployeeVO oEmployeeVO;
		ArrayList<EmployeeVO> oListToReturn = new ArrayList<>();
		ArrayList<Employee> oList = new ArrayList<>();

		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();

		try {
			oList = oDAOEmployee.getEmployees();

			for (Employee iEmployee : oList) {
				oEmployeeVO = iEmployee.toVO();
				oListToReturn.add(oEmployeeVO);
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return oListToReturn;
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
	public boolean checkLogin(EmployeeFilterVO oEmployeeFilterVO)
			throws DataBaseConnection {

		boolean toReturn = false;

		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		String crypted = this.hashEncriptation(oEmployeeFilterVO.getPassword());

		try {
			toReturn = oDAOEmployee.checkLogin(oEmployeeFilterVO.getUserName(),
					crypted);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;

	}

	@Override
	public Employee getLoginEmployee(EmployeeFilterVO oEmployeeFilterVO)
			throws DataBaseConnection {

		Employee oEmployeeToReturn = null;

		EmployeeDAOMgt oDAOEmployee = EmployeeDAOFactory.getEmployeeDAOMgt();
		String crypted = this.hashEncriptation(oEmployeeFilterVO.getPassword());

		try {
			oEmployeeToReturn = oDAOEmployee.getLoginEmployee(oEmployeeFilterVO.getUserName(), crypted);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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