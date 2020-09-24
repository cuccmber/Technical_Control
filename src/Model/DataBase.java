package Model;

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

    public void createQuery(String query){
        //String query = "select distinct count(carID) from checkup where result = 1";

        try {
            // opening database connection to MySQL server
            connection = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            statement = connection.createStatement();

            // executing SELECT query
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int date = resultSet.getInt(1);
                int count = resultSet.getInt(2);
                System.out.println(date + " " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { connection.close(); } catch(SQLException se) { /*can't do anything */ }
            try { statement.close(); } catch(SQLException se) { /*can't do anything */ }
            try { resultSet.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

}
