package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

import javax.swing.JOptionPane;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * A class to Connect to a mysqlServer
 * @author Helmuth Brunner
 * @version Jun 25, 2014
 * Current project: RueckwertsSalto
 */
public class ConnectMysql {

	private static ConnectMysql instance;
	private Connection con;
	private MysqlDataSource mds;

	/**
	 * Default-Constructor creates the Connection.
	 * @param ds the DataSource
	 */
	private ConnectMysql(DataSource ds) {
		// Set the parameters for der MysqlDataSource
		mds= new MysqlDataSource();
		mds.setUser(ds.getUserName());
		mds.setServerName(ds.getServername());
		mds.setDatabaseName(ds.getDatabasename());
		mds.setPassword(ds.getPassword());

		try {
			con= mds.getConnection();//Create the connection.
		} catch (SQLException e) {

		}
	}

	/**
	 * Singleton-Pattern
	 * @return the instance from this Class
	 */
	public static ConnectMysql get(DataSource ds) {
		if(instance==null)
			instance= new ConnectMysql(ds);
		return instance;
	}

	public ResultSet executeQuery(String query) {
		try {
			return con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			System.err.println("SQL-Exception in "+ ConnectMysql.class.getName());
		}
		return null;
	}

	public int update(String query) {
		try {
			return con.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			System.err.println("SQL-Exception in "+ ConnectMysql.class.getName());
		}
		return 0;
	}

}
