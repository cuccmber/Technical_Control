package database;

import java.sql.*;

public class DataBase {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/kbe?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "Diane";
    private static final String password = "Tralya1478zraza05!";

    // JDBC variables for opening and managing connection
    private static Connection connection;

    public Connection getOpenedConnection() throws SQLException {

        // opening database connection to MySQL server
        connection = DriverManager.getConnection(url, user, password);

        return connection;
    }

}
