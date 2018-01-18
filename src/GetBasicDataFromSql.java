import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import model.Employee;

/**
 * This class is used to get some data from DB and print the Object Output.
 * 
 * @author Kranthi Kumar
 *
 */
public class GetBasicDataFromSql {

	private static final Logger logger = Logger.getLogger(GetBasicDataFromSql.class);

	public static void main(String[] args) throws SQLException {
		Statement st = null;
		Connection conn = null;
		
		/**
		 * Below line is used for the logger to log all the messages on the console
		 * 
		 * The output contains the time elapsed from the start of the program in
		 * milliseconds, the thread name, the logger level [Info,Debug,Warn, error
		 * etc..,] the class name and the log message.
		 */
		BasicConfigurator.configure();
		try {
			/**
			 * create our Oracle database connection Oracle driver [We need oracle
			 * (ojdbc6.jar) jar to make this work when calling Class.forName("") below.
			 * Below line is not mandatory (That's why commented) because JVM's are able to
			 * auto-discover the drivers now
			 */

			// String oracleDriver = "oracle.jdbc.driver.OracleDriver";

			/**
			 * Connection Url to my localhost with the required portnumber and database name
			 * This may vary from system to system based on port numbers and database names
			 */
			String connectURL = "jdbc:oracle:thin:@localhost:1521:xe";

			/**
			 * Below one is not mandatory.. It is used to tell JDBC Driver Manager how to
			 * map the JDBC URL to the real driver.
			 * 
			 * Class.forName("X") causes class named "X" to be dynamically loaded JVM keeps
			 * tracks of all the classes that have been previously loaded
			 */
			// Class.forName(oracleDriver);

			/**
			 * Getting Connection to database by passing the required parameters
			 * databaseUrl, userName, password.
			 */
			logger.info("Getting the Connection");
			conn = DriverManager.getConnection(connectURL, "Kranthi", "password");

			/**
			 * Select Query with required columns to fetch the data
			 */
			String query = "SELECT EMP_ID,EMP_NAME,EMP_ROLE,EMP_COMPANY,EMP_SALARY FROM KR_EMPLOYEE";

			/**
			 * Before Executing the query using Statement Object we need to create one using
			 * Connection Object
			 */
			logger.info("Creating the statement");
			st = conn.createStatement();

			/**
			 * Executing the Query and getting ResultSet..
			 */
			logger.info("Executing the Query : " + query);
			ResultSet rs = st.executeQuery(query);

			/**
			 * Iterating the Result Set Note: After iteration make sure you are giving
			 * proper dataTypes matching with DB dataType
			 */
			while (rs.next()) {
				/**
				 * Creating a Copy constructor new Employee(ResultSet) in Employee Object to
				 * copy data from ResultSet to Employee Object
				 */
				logger.info("Employee Detail --> " + new Employee(rs));
			}
		} catch (Exception e) {
			// Best practice in Java --> To log the errors properly
			// NOTE : Don't use Sysouts anywhere in the code...
			logger.error("Exception occured while getting Employee Details", e);
		} finally {
			// Best practice in Java --> to close all the connections that are opened
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

}
