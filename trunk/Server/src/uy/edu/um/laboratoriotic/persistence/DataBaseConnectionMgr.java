package uy.edu.um.laboratoriotic.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

/**
 * This class represents the manager that establishes the connection with the database
 * @author sblanco1
 *
 */
public class DataBaseConnectionMgr {

	/*
	 * Attributes of the class
	 */
	private static DataBaseConnectionMgr instance = null;
	private DataSource dataSource = null;

	/*
	 * Constructor
	 */
	private DataBaseConnectionMgr() {
		
		Properties propiedades = new Properties();
		
		try {
			
			propiedades.load(new FileInputStream("dataSource.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(propiedades);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Methods
	 */
	public static synchronized DataBaseConnectionMgr getInstance() {

		if (instance == null) {
			instance = new DataBaseConnectionMgr();
		}

		return instance;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
