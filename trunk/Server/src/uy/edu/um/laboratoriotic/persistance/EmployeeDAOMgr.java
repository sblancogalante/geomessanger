package uy.edu.um.laboratoriotic.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.Employee;

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
	private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE Employees (firstName VARCHAR(27), lastName VARCHAR(28) NOT NULL, iD INT PRIMARY KEY, location VARCHAR(27) NOT NULL, sector VARCHAR(27), status BOOLEAN NOT NULL)";

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
	 * (non-Javadoc) methods implementation
	 * 
	 * @see
	 * uy.edu.um.laboratoriotic.persistance.EmployeeDAOMgt#addEmployee(uy.edu
	 * .um.laboratoriotic.business.Employee)
	 */
	@Override
	public void addEmployee(Employee oEmployee) {
		// TODO Auto-generated method stub
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

			Statement oStatement = oConnection.createStatement();

			String sFirstName = oEmployee.getName();
			String sLastName = oEmployee.getLastName();
			int nID = oEmployee.getEmployeeID();
			String sLocation = oEmployee.getLocation();
			String sSector = oEmployee.getSector();
			boolean sStatus = oEmployee.getStatus();

			String sInsert = "INSERT INTO Employees (firstName, lastName, iD, location, sector, status) VALUES (\'"
					+ sFirstName+ "','"+ sLastName+ "',"+ nID+ sLocation+ "','" + sSector + "','" + sStatus + ")";
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
	
	/*
	 * (non-Javadoc)
	 * @see uy.edu.um.laboratoriotic.persistance.EmployeeDAOMgt#getEmployees()
	 */
	public ArrayList<Employee> getEmployees() {

		ArrayList<Employee> oList = new ArrayList<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Employees ORDER BY location ASC, sector ASC, status ASC";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				String sResultFIrstName = oResultSet.getString(1);
				String sResultLastName = oResultSet.getString(2);
				int nResultID = oResultSet.getInt(3);
				String sResultLocation = oResultSet.getString(4);
				String sResultSector = oResultSet.getString(5);
				boolean sResultStatus = oResultSet.getBoolean(6);

				Employee oEmployee = new Employee(sResultLastName,
						sResultLastName, nResultID, sResultLocation,
						sResultSector, sResultStatus);

				oList.add(oEmployee);

				System.out.println("El empleado encontrado es:\n"
						+ sResultFIrstName + " " + sResultLastName+ " identificacion personal:" + nResultID
						+ " pais en donde trabaja:" + sResultLocation+ " sector de trabajo:" + sResultSector + " estado:"
						+ sResultStatus);

			}

			oResultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * Helping methods
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

	public void createTable() {

		Connection oConnection = null;
		Statement oStatement;
		boolean bValue = false;

		oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

		try {

			oStatement = oConnection.createStatement();
			bValue = oStatement.execute(CREATE_TABLE_EMPLOYEE);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!bValue) {
			System.out.println("Se ejecuto con exito");
		}

	}
	
}
