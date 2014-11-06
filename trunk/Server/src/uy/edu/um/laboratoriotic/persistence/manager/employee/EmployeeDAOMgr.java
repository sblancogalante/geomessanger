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
			String sInsert = "INSERT INTO `Employees` (document, iD, name, lastName, userName, password, location, sector, mail, position, workingHour, profilePicture, status, admin) VALUES ('"
					+ oEmployee.getDocument().getValue()
					+ "','"
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
					+ oEmployee.getStatus() + "," + oEmployee.getAdmin() + ");";

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
	public void removeEmployee(String oUserName) throws DataBaseConnection {
		// TODO Auto-generated method stub

		Statement oStatement = null;
		Connection oConnection = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "DELETE FROM Employees where (Employees.userName = '"
					+ oUserName + "') ;";
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

				Employee oEmployee = new Employee(sResultEmployeeID,
						oTypeDocument, sResultID, sResultName, sResultLastName,
						sResultUserName, sResultPassword, oTypeLocation,
						oTypeSector, sResultMail, sResultPosition,
						sResultWorkingHour, sResultProfilePicture,
						sResultStatus, sResultAdmin);

				oList.add(oEmployee);

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
	public Employee modifyEmployee(Employee oEmployee)
			throws DataBaseConnection {

		Employee oEmployeeToReturn = null;
		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			if (oEmployee.getDocument().getValue() != null) {
				updateDocument(oEmployee);
			} else if (oEmployee.getID() != null) {
				updateID(oEmployee);
			} else if (oEmployee.getLocation().getValue() != null) {
				updateLocation(oEmployee);
			} else if (oEmployee.getSector().getValue() != null) {
				updateSector(oEmployee);
			} else if (oEmployee.getMail() != null) {
				updateMail(oEmployee);
			} else if (oEmployee.getPosition() != null) {
				updatePosition(oEmployee);
			} else if (oEmployee.getWorkingHour() != null) {
				updateWorkingHour(oEmployee);
			} else if (oEmployee.getProfilePicture() != null) {
				updateProfilePicture(oEmployee);
			} else if (oEmployee.getStatus()) {
				updateStatus(oEmployee);
			} else if (oEmployee.getAdmin()) {
				updateAdmin(oEmployee);
			}

			oEmployeeToReturn = searchEmployee(oEmployee.getUserName());

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

		return oEmployeeToReturn;
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

				if (oUserName.equals(sResultUserName)
						&& hashEncriptation(oPassword).equals(sResultPassword)) {

					Type oTypeDocument = new Type("Document", sResultDocument);
					Type oTypeLocation = new Type("Location", sResultLocation);
					Type oTypeSector = new Type("Sector", sResultSector);

					oEmployeeToReturn = new Employee(sResultEmployeeID,
							oTypeDocument, sResultID, sResultName,
							sResultLastName, sResultUserName, sResultPassword,
							oTypeLocation, oTypeSector, sResultMail,
							sResultPosition, sResultWorkingHour,
							sResultProfilePicture, sResultStatus, sResultAdmin);

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
	private String hashEncriptation(String oPassword) {

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

	private void updateDocument(Employee oEmployee) {
		// TODO Auto-generated method stub

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set document = '"
					+ oEmployee.getDocument()
					+ "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updateID(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set iD = '" + oEmployee.getID()
					+ "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updateLocation(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set location = '"
					+ oEmployee.getLocation().getValue()
					+ "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updateSector(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set sector = '"
					+ oEmployee.getSector().getValue()
					+ "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updateMail(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set mail = '"
					+ oEmployee.getMail() + "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updatePosition(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set position = '"
					+ oEmployee.getPosition()
					+ "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updateWorkingHour(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set workingHour = '"
					+ oEmployee.getWorkingHour()
					+ "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updateProfilePicture(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set profilePicture = '"
					+ oEmployee.getWorkingHour()
					+ "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updateStatus(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set status = '"
					+ oEmployee.getStatus() + "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

	private void updateAdmin(Employee oEmployee) {

		Connection oConnection = null;
		Statement oStatement = null;

		try {

			oConnection = DataBaseConnectionMgr.getInstance().getConnection();
			oStatement = oConnection.createStatement();

			String sQuery = "UPDATE Employees set admin = '"
					+ oEmployee.getWorkingHour()
					+ "' WHERE Employees.userName = '"
					+ oEmployee.getUserName() + "';";

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

}