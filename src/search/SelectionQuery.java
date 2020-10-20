package search;

import database.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectionQuery {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private String queryString;

    public SelectionQuery(String queryString){

        this.queryString = queryString;
    }

    public ResultSet show(){

        DataBase db = new DataBase();

        try {
            connection = db.openConnection();
            statement = connection.prepareStatement(queryString);
            resultSet = statement.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public void closeAll() throws SQLException {

        connection.close();
        statement.close();
        resultSet.close();
    }
}
