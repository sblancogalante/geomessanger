package uy.edu.um.laboratoriotic.persistence.manager.general;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import uy.edu.um.laboratoriotic.business.entities.general.Type;
import uy.edu.um.laboratoriotic.exceptions.DataBaseConnection;
import uy.edu.um.laboratoriotic.persistence.DataBaseConnectionMgr;
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
	 * Management method implementations
	 */
	@Override
	public void addType(Type oType) throws DataBaseConnection {
		// TODO Auto-generated method stub
		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();

			oStatement = oConnection.createStatement();

			int sId = oType.getTypeID();
			String sType = oType.getType();
			String sValue = oType.getValue();

			String sInsert = "INSERT INTO Types (typeID, type, value) VALUES ("
					+ sId + ",'" + sType + "','" + sValue + "')";
						
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
	public void removeType(String oValue) throws DataBaseConnection {
		// TODO Auto-generated method stub

		Statement oStatement = null;
		Connection oConnection = null;

		String sQuery = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			sQuery = "DELETE FROM Types WHERE (Types.value = '"
					+ oValue + "') ;";			
						
			oStatement.execute(sQuery);

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
	public Type searchType(Type oType) throws DataBaseConnection {

		Type oTypeToReturn = null;
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "SELECT * FROM Types t WHERE (t.type = '"
					+ oType.getType() + "' AND t.value = '" + oType.getValue()
					+ "') ;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);

			while (oResultSet.next()) {

				int sResultTypeID = oResultSet.getInt(1);
				String sResultType = oResultSet.getString(2);
				String sResultValue = oResultSet.getString(3);

				oTypeToReturn = new Type(sResultTypeID, sResultType,
						sResultValue);

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
	public ArrayList<Type> getTypes(String oType) throws DataBaseConnection {
		// TODO Auto-generated method stub

		ArrayList<Type> oListToReturn = new ArrayList<>();
		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();
			String sQuery = "SELECT * FROM Types t WHERE t.type = '" + oType
					+ "' ORDER BY 1 ASC,2 ASC;";
			ResultSet oResultSet = oStatement.executeQuery(sQuery);
			Type oTypeToReturn = null;

			while (oResultSet.next()) {

				int sResultTypeID = oResultSet.getInt(1);
				String sResultType = oResultSet.getString(2);
				String sResultValue = oResultSet.getString(3);

				oTypeToReturn = new Type(sResultTypeID, sResultType,
						sResultValue);

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

}