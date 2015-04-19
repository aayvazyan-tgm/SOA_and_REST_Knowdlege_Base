package mysql;

/**
 * A Class to all the Connection-Parameters.
 * @author Helmuth Brunner
 * @version Jun 25, 2014
 * Current project: RueckwertsSalto
 */
public class DataSource {

	//Attributes
	private static DataSource instance;
	
	private String username;
	private String password;
	private String databasename;
	private String servername;
	private int portnumber;
	
	/**
	 * Default-Constructor
	 */
	private DataSource() {
	}
	
	/**
	 * Method which returns a instance from this class
	 * @return the instance from this class
	 */
	public static DataSource get() {
		if(instance==null)
			instance= new DataSource();
		return instance;
	}
	
	/**
	 * Setter to set the username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username= username;
	}

	/**
	 * Getter to get the username
	 * @return the username
	 */
	public String getUserName() {
		return this.username;
	}

	/**
	 * Setter to set the password
	 * @param password
	 */
	public void setPassword(String password) {
		this.password= password;
	}

	/**
	 * Getter to get the password
	 * @return the password in plain text
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Setter to set the databasename
	 * @param databasename the databasename
	 */
	public void setDatabasename(String databasename) {
		this.databasename= databasename;
	}

	/**
	 * Getter for the databasename
	 * @return the databasename
	 */
	public String getDatabasename() {
		return this.databasename;
	}

	/**
	 * Setter to set the portnumber
	 * @param port the portnumber
	 */
	public void setPortNumber(int port) {
		this.portnumber= port;
	}

	/**
	 * Getter to get the portnumber
	 * @return the portnumber
	 */
	public int getPortNumber() {
		return this.portnumber;
	}

	public void setServername(String servername) {
		this.servername= servername;
	}

	public String getServername() {
		return this.servername;
	}
}
