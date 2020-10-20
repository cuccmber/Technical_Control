package search;

import database.DataBase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.sql.*;

public class CarSearch {

    private Table table;
    private PreparedStatement statement;
    private Connection connection;
    private String countCars = "SELECT checkupDate, COUNT(carID) " +
            "FROM checkup " +
            "WHERE result = 1 " +
            "AND checkupDate BETWEEN ? AND ?;";

    public CarSearch(Table table){
        this.table = table;
    }

    public void searchCheckup (String fromDate, String tillDate) throws SQLException {

        DataBase db = new DataBase();
        connection = db.openConnection();
        statement = connection.prepareStatement(countCars);

        statement.setDate(1, Date.valueOf(fromDate));
        statement.setDate(2, Date.valueOf(tillDate));

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, String.valueOf(resultSet.getDate(1)));
            item.setText(1, String.valueOf(resultSet.getInt(2)));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
