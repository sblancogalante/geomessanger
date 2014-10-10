package uy.edu.um.laboratoriotic.persistence.manager.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
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
	private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE Employees (firstName VARCHAR(27), lastName VARCHAR(28), employeeID INT PRIMARY KEY NOT NULL, location VARCHAR(27) NOT NULL, sector VARCHAR(27), status BOOLEAN NOT NULL, userName VARCHAR(27) NOT NULL, password VARCHAR(20))";

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
	public void addEmployee(Employee oEmployee) {
		// TODO Auto-generated method stub
		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

			oStatement = oConnection.createStatement();

			System.out.println("Se agrego con exito al empleado "
					+ oEmployee.getUserName());

			String sInsert = "INSERT INTO Employees (userName, employeeID, location, sector, status) VALUES (\'"
					+ oEmployee.getUserName() + "','" + oEmployee.getEmployeeID()	+ "','"
					+ oEmployee.getLocation() + "','" + oEmployee.getSector()
					+ "'," + oEmployee.getStatus() + ")";

			oStatement.execute(sInsert);

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
	public ArrayList<Employee> getEmployees() {

		ArrayList<Employee> oList = new ArrayList<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Employees ORDER BY location ASC,sector ASC,status ASC";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				String sResultFirstName = oResultSet.getString(1);
				String sResultLastName = oResultSet.getString(2);
				int sResultEmployeeID = oResultSet.getInt(3);
				String sResultLocation = oResultSet.getString(4);
				String sResultSector = oResultSet.getString(5);
				boolean sResultStatus = oResultSet.getBoolean(6);
				String sUserName = oResultSet.getString(7);

				Employee oEmployee = new Employee(sResultFirstName,
						sResultLastName, sResultEmployeeID, sResultLocation,
						sResultSector, sResultStatus);

				oList.add(oEmployee);

				System.out.println("El empleado encontrado es:\n" + sUserName);

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

		return oList;
	}

	@Override
	public Employee searchEmployee(int oEmployeeID) {

		Employee oEmployee = null;
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Employees where (Employees.employeeID = '"
					+ oEmployeeID + "') ;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				String sResultFirstName = oResultSet.getString(1);
				String sResultLastName = oResultSet.getString(2);
				int sResultEmployeeID = oResultSet.getInt(3);
				String sResultLocation = oResultSet.getString(4);
				String sResultSector = oResultSet.getString(5);
				boolean sResultStatus = oResultSet.getBoolean(6);
				String oUserName = oResultSet.getString(7);

				oEmployee = new Employee(sResultFirstName, sResultLastName,
						sResultEmployeeID, sResultLocation, sResultSector,
						sResultStatus);

				System.out.println("El empleado hallado es: " + oUserName);

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
	public void removeEmployee(int oEmployeeID) {
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

	/**
	 * This method creates the tables in the data base
	 */
	public void createTable() {

		Connection oConnection = null;
		Statement oStatement;

		oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

		try {

			oStatement = oConnection.createStatement();
			oStatement.execute(CREATE_TABLE_EMPLOYEE);

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
	}

}