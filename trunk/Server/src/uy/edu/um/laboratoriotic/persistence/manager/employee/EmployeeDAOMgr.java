package uy.edu.um.laboratoriotic.persistence.manager.employee;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
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
	private static final String DRIVER_JDBC = "org.hsqldb.jdbc.JDBCDriver";
	private static final String URL_MEM_JDBC = "jdbc:hsqldb:mem:Server";
	private static final String CREATE_TABLE_EMPLOYEES = "CREATE TABLE Employees (employeeID INT PRIMARY KEY NOT NULL, iD VARCHAR(20) NOT NULL, name VARCHAR(20), lastName VARCHAR(20), userName VARCHAR(20) NOT NULL, password VARCHAR(100) NOT NULL, location VARCHAR(30) NOT NULL, sector VARCHAR(30), mail VARCHAR(30) NOT NULL, position VARCHAR(30), workingHour VARCHAR(20), profilePicture BLOB, status BOOLEAN NOT NULL)";

	private static long identifierNumber = 0;

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
	public void addEmployee(Employee oEmployee) throws DataBaseConnection,
			RemoteException {
		// TODO Auto-generated method stub
		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

			long sID = EmployeeDAOMgr.identifierNumber++;

			oStatement = oConnection.createStatement();
			String sInsert = "INSERT INTO Employees (employeeID, iD, name, lastName, userName, password, location, sector, mail, position, workingHour, profilePicture, status) VALUES ("
					+ sID
					+ ",'"
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
					+ oEmployee.getLocation()
					+ "','"
					+ oEmployee.getSector()
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
			throw new RemoteException();
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
	public ArrayList<Employee> getEmployees() throws DataBaseConnection,
			RemoteException {

		ArrayList<Employee> oList = new ArrayList<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Employees ORDER BY location ASC,sector ASC,status ASC;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

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

				Employee oEmployee = new Employee(sResultID, sResultName,
						sResultLastName, sResultUserName, sResultPassword,
						sResultLocation, sResultSector, sResultMail,
						sResultPosition, sResultWorkingHour,
						sResultProfilePicture, sResultStatus);

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
	public Employee searchEmployee(int oEmployeeID) throws DataBaseConnection,
			RemoteException {

		Employee oEmployee = null;
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Employees where (Employees.employeeID = '"
					+ oEmployeeID + "');";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sResultEmployeeID = oResultSet.getInt(1);
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

				oEmployee = new Employee(sResultID, sResultName,
						sResultLastName, sResultUserName, sResultPassword,
						sResultLocation, sResultSector, sResultMail,
						sResultPosition, sResultWorkingHour,
						sResultProfilePicture, sResultStatus);

				oEmployee.setEmployeeID(sResultEmployeeID);

				System.out
						.println("El empleado hallado es: " + sResultUserName);

			}

			oResultSet.close();

		} catch (SQLException e) {
			throw new RemoteException();
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
	public void removeEmployee(int oEmployeeID) throws DataBaseConnection,
			RemoteException {
		// TODO Auto-generated method stub

		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();

			String sQueryCount = "SELECT COUNT(*) FROM Employees";

			ResultSet oResultSetCount = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount.next()) {

				int nCount = oResultSetCount.getInt(1);

				System.out.println("Cantidad de empleados: " + nCount);
			}

			String sQuery = "DELETE FROM Employees where (Employees.employeeID = '"
					+ oEmployeeID + "') ;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			ResultSet oResultSetCount2 = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount2.next()) {

				int nCount = oResultSetCount2.getInt(1);

				System.out.println("Cantidad de empleados: " + nCount);
			}

			oResultSet.close();

		} catch (SQLException e) {
			throw new RemoteException();
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
	public void createTable() throws DataBaseConnection, RemoteException {

		Connection oConnection = null;
		Statement oStatement;

		oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

		try {

			oStatement = oConnection.createStatement();
			oStatement.execute(CREATE_TABLE_EMPLOYEES);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RemoteException();
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
			throws DataBaseConnection, RemoteException {

		boolean toReturn = false;
		Connection oConnection = null;
		try {
			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			Statement oStatement = oConnection.createStatement();
			String oQuery = "SELECT userName, password FROM Employees WHERE (Employees.userName = '"
					+ oUserName
					+ "' AND Employees.password = '"
					+ hashEncriptation(oPassword) + "');";
			ResultSet oResultSet = oStatement.executeQuery(oQuery);

			System.out.println(oQuery);

			while (oResultSet.next()) {

				String userName = oResultSet.getString(1);
				String password = oResultSet.getString(2);

				// System.out.println(userName + " " + password);

				if (oUserName.equals(userName)
						&& hashEncriptation(oPassword).equals(password)) {
					toReturn = true;
				}
			}

			oResultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RemoteException();
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
			throws DataBaseConnection, RemoteException {

		Employee oEmployeeToReturn = null;
		Connection oConnection = null;
		try {
			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			Statement oStatement = oConnection.createStatement();
			ResultSet oResultSet = oStatement
					.executeQuery("SELECT * FROM Employees WHERE (Employees.userName = '"
							+ oUserName + "');");

			while (oResultSet.next()) {

				int sResultEmployeeID = oResultSet.getInt(1);
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

				if (oUserName.equals(sResultUserName)
						&& hashEncriptation(oPassword).equals(sResultPassword)) {
					
					oEmployeeToReturn = new Employee(sResultID, sResultName,
							sResultLastName, sResultUserName, sResultPassword,
							sResultLocation, sResultSector, sResultMail,
							sResultPosition, sResultWorkingHour,
							sResultProfilePicture, sResultStatus);
					
					oEmployeeToReturn.setEmployeeID(sResultEmployeeID);

				}
			}

			oResultSet.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RemoteException();
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
	/**
	 * This method establishes the connection with the data base
	 * 
	 * @param oDBDriver
	 * @param dBDirection
	 * @return
	 */
	private Connection connect(String oDBDriver, String dBDirection) {

		Connection oResult = null;

		try {

			Class.forName(oDBDriver);
			oResult = DriverManager.getConnection(dBDirection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return oResult;
	}

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