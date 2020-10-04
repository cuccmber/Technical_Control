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

    public ResultSet createQuery(String query){

        try {
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            statement = connection.createStatement();

            // executing SELECT query
            resultSet = statement.executeQuery(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection, stmt and result set here
            //try { connection.close(); } catch(SQLException se) { /*can't do anything */ }
            //try { statement.close(); } catch(SQLException se) { /*can't do anything */ }
            //try { resultSet.close(); } catch(SQLException se) { /*can't do anything */ }
        }

        return resultSet;
    }

    public void executeQuery(String query){
        try {
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            statement = connection.createStatement();

            // executing INSERT query
            statement.executeUpdate(query);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection, stmt and resultset here
            try { connection.close(); } catch(SQLException se) { /*can't do anything */ }
            try { statement.close(); } catch(SQLException se) { /*can't do anything */ }
            try { resultSet.close(); } catch(SQLException se) { /*can't do anything */ }
        }

    }

}
