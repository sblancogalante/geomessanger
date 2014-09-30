package uy.edu.um.laboratoriotic.persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE Employees (firstName VARCHAR(27), lastName VARCHAR(28) NOT NULL,document INT PRIMARY KEY)";

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

			String sInsert = "INSERT INTO Employees (firstName, lastName, iD) VALUES (\'"
					+ sFirstName + "','" + sLastName + "'," + nID + ")";
			oStatement.execute(sInsert);

			String sQuery = "SELECT * FROM Employees";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				String sResultFIrstName = oResultSet.getString(1);
				String sResultLastName = oResultSet.getString(2);
				int nResultID = oResultSet.getInt(3);

				System.out.println("El empleado encontredo es:\n"
						+ sResultFIrstName + " " + sResultLastName
						+ " document:" + nResultID);

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
	
	public void createTable(){
		
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
