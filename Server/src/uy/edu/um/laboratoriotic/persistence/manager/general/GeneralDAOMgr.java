package uy.edu.um.laboratoriotic.persistence.manager.general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.persistence.management.general.GeneralDAOMgt;

/**
 * This is the implementation of GeneralDAOMgt
 * 
 * @author sblanco1
 * 
 */
public class GeneralDAOMgr implements GeneralDAOMgt {

	/*
	 * Attributes of the class
	 */
	private static GeneralDAOMgr instance = null;
	private static final String DRIVER_JDBC = "org.hsqldb.jdbc.JDBCDriver";
	private static final String URL_MEM_JDBC = "jdbc:hsqldb:mem:Server";
	private static final String CREATE_TABLE_TYPE = "CREATE TABLE Types (typeID INT PRIMARY KEY NOT NULL, typeCountry VARCHAR(30) NOT NULL, typeSector VARCHAR(30) NOT NULL)";

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

			int sId = oType.getTypeID();
			String sTypeCountry = oType.getTypeCountry();
			String sTypeSector = oType.getTypeSector();

			String sInsert = "INSERT INTO Type (typeID, type, value) VALUES (\'"
					+ sId + "','" + sTypeCountry + "','" + sTypeSector + ")";

			oStatement.execute(sInsert);

			System.out.println("Se agrego con exito al pais " + sTypeCountry
					+ " , y al sector " + sTypeSector);

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
	public void removeType(Type oType) {
		// TODO Auto-generated method stub

		Statement oStatement = null;
		Connection oConnection = null;

		String sQueryC = null;
		String sQueryS = null;

		ResultSet oResultSetC = null;
		ResultSet oResultSetS = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();

			String sQueryCount = "SELECT COUNT(*) FROM Types";

			ResultSet oResultSetCount = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount.next()) {

				int nCount = oResultSetCount.getInt(1);

				System.out.println("Cantidad de Types: " + nCount);
			}

			if (oType.isType()) {

				sQueryC = "DELETE FROM Types where (Types.typeCountry = '"
						+ oType.getTypeCountry() + "') ;";

				oResultSetC = oStatement.executeQuery(sQueryC);

			} else {

				sQueryS = "DELETE FROM Types where (Types.typeSector = '"
						+ oType.getTypeSector() + "') ;";

				oResultSetS = oStatement.executeQuery(sQueryS);

			}

			ResultSet oResultSetCount2 = oStatement.executeQuery(sQueryCount);

			if (oResultSetCount2.next()) {

				int nCount = oResultSetCount2.getInt(1);

				System.out.println("Cantidad de Types: " + nCount);
			}

			oResultSetC.close();
			oResultSetS.close();

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
	public Type searchType(Type oType) {

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

				if (oType.isType()) {
					oTypeToReturn = new Type(sResultTypeID, sResultType,
							sResultValue, true);
				} else {
					oTypeToReturn = new Type(sResultTypeID, sResultType,
							sResultValue, false);
				}

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

	@Override
	public ArrayList<Type> getTypes(String oType) {
		// TODO Auto-generated method stub

		ArrayList<Type> oListToReturn = new ArrayList<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = connect(DRIVER_JDBC, URL_MEM_JDBC);
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Types ORDER BY typeCountry ASC,typeSector ASC;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);
			Type oTypeToReturn = null;

			while (oResultSet.next()) {

				int sResultTypeID = oResultSet.getInt(1);
				String sResultCountry = oResultSet.getString(2);
				String sResultSector = oResultSet.getString(3);

				if (oType.equals("pais")) {
					oTypeToReturn = new Type(sResultTypeID, sResultCountry,
							sResultSector, true);
				}
				if (oType.equals("sector")) {
					oTypeToReturn = new Type(sResultTypeID, sResultCountry,
							sResultSector, false);
				}

				oListToReturn.add(oTypeToReturn);

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

		return oListToReturn;
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
