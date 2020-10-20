package search;

import database.DataBase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.sql.*;

public class InspectorSearch {

    private Table table;
    private PreparedStatement statement;
    private Connection connection;
    private String showInspector = "SELECT fullName, inspectorRank, carID " +
            "FROM inspector " +
            "JOIN checkup ON inspector.inspectorID = checkup.inspectorID " +
            "WHERE checkupDate BETWEEN ? AND ?;";

    public InspectorSearch(Table table){
        this.table = table;
    }

    public void searchInspector (String fromDate, String tillDate) throws SQLException {

        DataBase db = new DataBase();
        connection = db.getOpenedConnection();
        statement = connection.prepareStatement(showInspector);

        statement.setDate(1, Date.valueOf(fromDate));
        statement.setDate(2, Date.valueOf(tillDate));

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, resultSet.getString(1));
            item.setText(1, resultSet.getString(2));
            item.setText(2, String.valueOf(resultSet.getInt(3)));
        }

        resultSet.close();
        statement.close();
        connection.close();

    }
}
