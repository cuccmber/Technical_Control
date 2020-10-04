package Controller;

import java.sql.*;

public class DataBase {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/kbe?allowPublicKeyRetrieval=true&useSSL=false";
    private static final String user = "Diane";
    private static final String password = "Tralya1478zraza05!";

    // JDBC variables for opening and managing connection
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public void openConnection() throws SQLException {

        // opening database connection to MySQL server
        connection = DriverManager.getConnection(url, user, password);

        // getting Statement object to execute query
        statement = connection.createStatement();

    }

    public ResultSet selectQuery(String query) throws SQLException {

        resultSet = statement.executeQuery(query);

        return resultSet;
    }

    public void updateQuery(String query) throws SQLException {

        statement.executeUpdate(query);

    }

    public void closeConnection() throws SQLException {

        //close connection, stmt and resultset here
        connection.close();
        statement.close();
        resultSet.close();

    }

}
