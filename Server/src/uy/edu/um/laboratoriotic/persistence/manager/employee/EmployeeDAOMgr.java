package uy.edu.um.laboratoriotic.persistence.manager.employee;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.DataBaseConnectionMgr;
import uy.edu.um.laboratoriotic.persistence.management.employee.EmployeeDAOMgt;

/**
 * This is the implementation of EmployeeDAOMgt
 * 
 * @author sblanco1
 * 
 */
public class EmployeeDAOMgr implements EmployeeDAOMgt {

	/*
	 * Attributes of the class
	 */
	private static EmployeeDAOMgr instance = null;
	
	/*
	 * Constructor of the class
	 */
	private EmployeeDAOMgr() {

	}

	public static EmployeeDAOMgr getInstance() {

		if (instance == null) {
			instance = new EmployeeDAOMgr();
		}

		return instance;

	}

	/*
	 * This are the management implementation methods
	 */
	@Override
	public void addEmployee(Employee oEmployee) throws DataBaseConnection {
		// TODO Auto-generated method stub
		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();

			oStatement = oConnection.createStatement();
			String sInsert = "INSERT INTO `Employees` (iD, name, lastName, userName, password, location, sector, mail, position, workingHour, profilePicture, status) VALUES ('"
					+ oEmployee.getID()
					+ "','"
					+ oEmployee.getName()
					+ "','"
					+ oEmployee.getLastName()
					+ "','"
					+ oEmployee.getUserName()
					+ "','"
					+ oEmployee.getPassword()
					+ "','"
					+ oEmployee.getLocation().getValue()
					+ "','"
					+ oEmployee.getSector().getValue()
					+ "','"
					+ oEmployee.getMail()
					+ "','"
					+ oEmployee.getPosition()
					+ "','"
					+ oEmployee.getWorkingHour()
					+ "',"
					+ oEmployee.getProfilePicture()
					+ ","
					+ oEmployee.getStatus() + ");";

			oStatement.execute(sInsert);

			System.out.println("Se agrego con exito al empleado "
					+ oEmployee.getUserName());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnection != null) {
				try {
					oConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public ArrayList<Employee> getEmployees() throws DataBaseConnection {

		ArrayList<Employee> oList = new ArrayList<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Employees ORDER BY location ASC,sector ASC,status ASC;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sResultEmployeeID = oResultSet.getInt(1);
				String sResultDocument = oResultSet.getString(2);
				String sResultID = oResultSet.getString(3);
				String sResultName = oResultSet.getString(4);
				String sResultLastName = oResultSet.getString(5);
				String sResultUserName = oResultSet.getString(6);
				String sResultPassword = oResultSet.getString(7);
				String sResultLocation = oResultSet.getString(8);
				String sResultSector = oResultSet.getString(9);
				String sResultMail = oResultSet.getString(10);
				String sResultPosition = oResultSet.getString(11);
				String sResultWorkingHour = oResultSet.getString(12);
				Blob sResultProfilePicture = oResultSet.getBlob(13);
				boolean sResultStatus = oResultSet.getBoolean(14);
				boolean sResultAdmin = oResultSet.getBoolean(15);

				Type oTypeDocument = new Type("Document", sResultDocument);
				Type oTypeLocation = new Type("Location", sResultLocation);
				Type oTypeSector = new Type("Sector", sResultSector);

				 Employee oEmployee = new Employee(sResultEmployeeID, oTypeDocument,
						sResultID, sResultName, sResultLastName,
						sResultUserName, sResultPassword, oTypeLocation,
						oTypeSector, sResultMail, sResultPosition,
						sResultWorkingHour, sResultProfilePicture,
						sResultStatus, sResultAdmin);

				oList.add(oEmployee);

				System.out.println("El empleado encontrado es:\n"
						+ sResultUserName);

			}

			oResultSet.close();

		} catch (SQLException e) {
			// throw new RemoteException();
			e.printStackTrace();
		} finally {
			if (oConnection != null) {
				try {
					oConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}

		return oList;
	}

	@Override
	public Employee searchEmployee(String oUserName) throws DataBaseConnection {

		Employee oEmployee = null;
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Employees where (Employees.userName = '"
					+ oUserName + "');";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sResultEmployeeID = oResultSet.getInt(1);
				String sResultDocument = oResultSet.getString(2);
				String sResultID = oResultSet.getString(3);
				String sResultName = oResultSet.getString(4);
				String sResultLastName = oResultSet.getString(5);
				String sResultUserName = oResultSet.getString(6);
				String sResultPassword = oResultSet.getString(7);
				String sResultLocation = oResultSet.getString(8);
				String sResultSector = oResultSet.getString(9);
				String sResultMail = oResultSet.getString(10);
				String sResultPosition = oResultSet.getString(11);
				String sResultWorkingHour = oResultSet.getString(12);
				Blob sResultProfilePicture = oResultSet.getBlob(13);
				boolean sResultStatus = oResultSet.getBoolean(14);
				boolean sResultAdmin = oResultSet.getBoolean(15);

				Type oTypeDocument = new Type("Document", sResultDocument);
				Type oTypeLocation = new Type("Location", sResultLocation);
				Type oTypeSector = new Type("Sector", sResultSector);

				oEmployee = new Employee(sResultEmployeeID, oTypeDocument,
						sResultID, sResultName, sResultLastName,
						sResultUserName, sResultPassword, oTypeLocation,
						oTypeSector, sResultMail, sResultPosition,
						sResultWorkingHour, sResultProfilePicture,
						sResultStatus, sResultAdmin);

				System.out
						.println("El empleado hallado es: " + sResultUserName);

			}

			oResultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnection != null) {
				try {
					oConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return oEmployee;
	}

	@Override
	public void removeEmployee(int oEmployeeID) throws DataBaseConnection {
		// TODO Auto-generated method stub

		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQueryCount = "SELECT COUNT(*) FROM Employees";

			ResultSet oResultSetCount = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount.next()) {

				int nCount = oResultSetCount.getInt(1);

				System.out.println("Cantidad de empleados: " + nCount);
			}

			String sQuery = "DELETE FROM Employees where (Employees.employeeID = '"
					+ oEmployeeID + "') ;";
			oStatement.execute(sQuery);
			System.out.println(sQuery);

			ResultSet oResultSetCount2 = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount2.next()) {

				int nCount = oResultSetCount2.getInt(1);

				System.out.println("Cantidad de empleados: " + nCount);
			}

			oStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnection != null) {
				try {
					oConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public boolean checkLogin(String oUserName, String oPassword)
			throws DataBaseConnection {

		boolean toReturn = false;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			Statement oStatement = oConnection.createStatement();
			String oQuery = "SELECT userName, password FROM Employees WHERE (Employees.userName = '"
					+ oUserName
					+ "' AND Employees.password = '"
					+ hashEncriptation(oPassword) + "');";

			ResultSet oResultSet = oStatement.executeQuery(oQuery);

			while (oResultSet.next()) {

				String userName = oResultSet.getString(1);
				String password = oResultSet.getString(2);

				if (oUserName.equals(userName)
						&& hashEncriptation(oPassword).equals(password)) {
					toReturn = true;
				}
			}

			oResultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnection != null) {
				try {
					oConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return toReturn;
	}

	@Override
	public Employee getLoginEmployee(String oUserName, String oPassword)
			throws DataBaseConnection {

		Employee oEmployeeToReturn = null;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			Statement oStatement = oConnection.createStatement();
			ResultSet oResultSet = oStatement
					.executeQuery("SELECT * FROM Employees WHERE (Employees.userName = '"
							+ oUserName + "');");

			while (oResultSet.next()) {

				int sResultEmployeeID = oResultSet.getInt(1);
				String sResultDocument = oResultSet.getString(2);
				String sResultID = oResultSet.getString(2);
				String sResultName = oResultSet.getString(3);
				String sResultLastName = oResultSet.getString(4);
				String sResultUserName = oResultSet.getString(5);
				String sResultPassword = oResultSet.getString(6);
				String sResultLocation = oResultSet.getString(7);
				String sResultSector = oResultSet.getString(8);
				String sResultMail = oResultSet.getString(9);
				String sResultPosition = oResultSet.getString(10);
				String sResultWorkingHour = oResultSet.getString(11);
				Blob sResultProfilePicture = oResultSet.getBlob(12);
				boolean sResultStatus = oResultSet.getBoolean(13);
				boolean sResultAdmin = oResultSet.getBoolean(15);
				
				if (oUserName.equals(sResultUserName)
						&& hashEncriptation(oPassword).equals(sResultPassword)) {

					Type oTypeDocument = new Type("Document", sResultDocument);
					Type oTypeLocation = new Type("Location", sResultLocation);
					Type oTypeSector = new Type("Sector", sResultSector);

					oEmployeeToReturn = new Employee(sResultEmployeeID, oTypeDocument,
							sResultID, sResultName, sResultLastName,
							sResultUserName, sResultPassword, oTypeLocation,
							oTypeSector, sResultMail, sResultPosition,
							sResultWorkingHour, sResultProfilePicture,
							sResultStatus, sResultAdmin);

				}
			}

			oResultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnection != null) {
				try {
					oConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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

	// /**
	// * This method converts from Date to String
	// * @param oDate
	// * @return
	// */
	// private String dateToString(Date oDate){
	//
	//
	// DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	//
	// Date today = oDate;
	//
	// String reportDate = df.format(today);
	//
	// return reportDate;
	//
	// }

}