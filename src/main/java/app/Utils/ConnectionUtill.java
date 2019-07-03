package app.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtill {
    /**
     * get database postgres connection
     * @return database connection
     * @throws SQLException
     */
    public static Connection getPostgresConnection() {
        // Change connection parameters
        final String hostName = "localhost";
        final String port = "5432";
        final String dbName = "parts";
        final String user = "postgres";
        final String password = "postgres";
        return getPostgresConnection(hostName, port, dbName, user, password);
    }

    /**
     * get database postgres connection
     * example connection URL - jdbc:postgresql://localhost:5432/partEntity
     * @param hostName - hostname, example - localhost
     * @param port - port, example - 5432
     * @param dbName - database name, example partEntity
     * @param user - user, example - postgres
     * @param password - password, example - postgres
     * @return database connection
     * @throws SQLException
     */
    public static Connection getPostgresConnection(String hostName, String port, String dbName, String user, String password) {
        String connectionURL = "jdbc:postgresql://" + hostName + ":" + port + "/" + dbName;
        try {
            return DriverManager.getConnection(connectionURL, user, password);
        } catch (SQLException e) {
            System.out.println("Error creating database connection");
            e.printStackTrace();
            return null;
        }
    }
}
