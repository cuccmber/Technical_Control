package search;

import database.DataBase;
import database.Query;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.sql.*;

public class CheckupSearch {

    private Table table;
    private PreparedStatement statement;
    private Connection connection;
    String showHistory = "SELECT checkupDate, result " +
            "FROM checkup " +
            "JOIN driver ON driver.carID = checkup.carID " +
            "WHERE driver.engineID = ?;";

    public CheckupSearch(Table table){
        this.table = table;
    }

    public void searchHistory (String engineID) throws SQLException, ClassNotFoundException {

        DataBase db = new DataBase();
        connection = db.openConnection();
        statement = connection.prepareStatement(showHistory);

        statement.setInt(1, Integer.parseInt(engineID));

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, String.valueOf(resultSet.getDate(1)));
            item.setText(1, String.valueOf(resultSet.getInt(2)));
        }

        connection.close();
    }
}
