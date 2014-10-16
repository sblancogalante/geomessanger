package uy.edu.um.laboratoriotic.persistence.manager.general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.persistence.management.general.GeneralDAOMgt;

public class GeneralDAOMgr implements GeneralDAOMgt {

	/*
	 * Attributes of the class
	 */
	private static GeneralDAOMgr instance = null;
	private static final String DRIVER_JDBC = "org.hsqldb.jdbc.JDBCDriver";
	private static final String URL_MEM_JDBC = "jdbc:hsqldb:mem:Server";
	private static final String CREATE_TABLE_TYPE = "CREATE TABLE Types (typeID INT PRIMARY KEY,type VARCHAR(27) NOT NULL,value VARCHAR(28) NOT NULL)";

	/*
	 * Constructor of the class
	 */
	private GeneralDAOMgr() {

	}

	public static GeneralDAOMgr getInstance() {

		if (instance == null) {
			instance = new GeneralDAOMgr();
		}

		return instance;

	}

	/*
	 * Management interface method implementations
	 */
	@Override
	public void addType(Type oType) {
		// TODO Auto-generated method stub
		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);

			oStatement = oConnection.createStatement();

			long sId = oType.getTypeID();
			String sType = oType.getType();
			String sValue = oType.getValue();

			String sInsert = "INSERT INTO Type (typeID, type, value) VALUES (\'"
					+ sId + "','" + sType + "','" + sValue + ")";

			oStatement.execute(sInsert);

			System.out.println("Se agrego con exito al Tipo " + sType
					+ " , con el valor " + sValue);

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
	public void removeType(String oType) {
		// TODO Auto-generated method stub

		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();

			String sQueryCount = "SELECT COUNT(*) FROM Types";

			ResultSet oResultSetCount = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount.next()) {

				int nCount = oResultSetCount.getInt(1);

				System.out.println("Cantidad de Types: " + nCount);
			}

			String sQuery = "DELETE FROM Types where (Types.type = '" + oType
					+ "') ;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			ResultSet oResultSetCount2 = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount2.next()) {

				int nCount = oResultSetCount2.getInt(1);

				System.out.println("Cantidad de Types: " + nCount);
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

	@Override
	public void removeValue(String oValue) {
		// TODO Auto-generated method stub

		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();

			String sQueryCount = "SELECT COUNT(*) FROM Types";
			ResultSet oResultSetCount = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount.next()) {

				int nCount = oResultSetCount.getInt(1);

				System.out.println("Cantidad de Types: " + nCount);
			}

			String sQuery = "DELETE FROM Types where (Types.value = '" + oValue
					+ "') ;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);
			ResultSet oResultSetCount2 = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount2.next()) {

				int nCount = oResultSetCount2.getInt(1);

				System.out.println("Cantidad de Types: " + nCount);
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

	@Override
	public Type searchType(String oType) {

		Type oTypeToReturn = null;
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Types where (Type.type = '" + oType
					+ "') ;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sResultTypeID = oResultSet.getInt(1);
				String sResultType = oResultSet.getString(2);
				String sResultValue = oResultSet.getString(3);

				oTypeToReturn = new Type(sResultTypeID, sResultType, sResultValue);

				System.out.println("El Type hallado es: " + "\n" + "Id: "
						+ sResultTypeID + "\n" + "Type: " + sResultType + "\n"
						+ " Valor: " + sResultValue + "\n");

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

		return oTypeToReturn;

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
			bValue = oStatement.execute(CREATE_TABLE_TYPE);
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
