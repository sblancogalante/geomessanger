package uy.edu.um.laboratoriotic.persistance.manager.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.employee.Employee;
import uy.edu.um.laboratoriotic.persistance.management.employee.EmployeeDAOMgt;
import uy.edu.um.laboratoriotic.services.valueobject.employee.EmployeeVO;

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
	private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE Employees (firstName VARCHAR(27),lastName VARCHAR(28) NOT NULL,employeeID INT PRIMARY KEY,location VARCHAR(27) NOT NULL,sector VARCHAR(27),status BOOLEAN NOT NULL)";

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
		Statement oStatement = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

			oStatement = oConnection.createStatement();

			String sFirstName = oEmployee.getName();
			String sLastName = oEmployee.getLastName();
			int sEmployeeID = oEmployee.getEmployeeID();
			String sLocation = oEmployee.getLocation();
			String sSector = oEmployee.getSector();
			boolean sStatus = oEmployee.getStatus();

			System.out.println("Se agrego con exito al empleado " + sFirstName
					+ " " + sLastName);

			String sInsert = "INSERT INTO Employees (firstName,lastName,employeeID,location,sector,status) VALUES (\'"
					+ sFirstName+ "','"	+ sLastName	+ "','"	+ sEmployeeID + "','"
					+ sLocation	+ "','"	+ sSector + "'," + sStatus + ")";

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
	 * 
	 * @see uy.edu.um.laboratoriotic.persistance.EmployeeDAOMgt#getEmployees()
	 */
	public ArrayList<EmployeeVO> getEmployees() {

		ArrayList<EmployeeVO> oList = new ArrayList<>();
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

				EmployeeVO oEmployee = new EmployeeVO(sResultFirstName,
						sResultLastName, sResultEmployeeID, sResultLocation,
						sResultSector, sResultStatus);

				oList.add(oEmployee);

				System.out.println("El empleado encontrado es:\n"
						+ sResultFirstName + " " + sResultLastName
						+ " identificacion personal:" + sResultEmployeeID
						+ " pais en donde trabaja:" + sResultLocation
						+ " sector de trabajo:" + sResultSector + " estado:"
						+ sResultStatus);

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * uy.edu.um.laboratoriotic.persistance.EmployeeDAOMgt#searchEmployee(int)
	 */
	public EmployeeVO searchEmployee(int oEmployeeID) {

		EmployeeVO oEmployeeVO = null;
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

				oEmployeeVO = new EmployeeVO(sResultFirstName, sResultLastName,
						sResultEmployeeID, sResultLocation, sResultSector,
						sResultStatus);

				System.out.println("El empleado hallado es: " + "\n"
						+ "Nombre: " + sResultFirstName + "\n" + "Apellido: "
						+ sResultLastName + "\n" + "Identificacion personal: "
						+ sResultEmployeeID + "\n" + "Pais: " + sResultLocation
						+ "\n" + "Sector de trabajo: " + sResultSector + "\n"
						+ "Estado de conexion: " + sResultStatus);

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

		return oEmployeeVO;

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
			if (!bValue) {
				System.out.println("Se ejecuto con exito");
			}

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