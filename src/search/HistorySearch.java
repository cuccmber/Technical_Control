package search;

import database.DataBase;
import database.Query;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistorySearch {

    public void searchHistory (Table table, String engineID) throws SQLException {

        DataBase db = new DataBase();
        ResultSet resultSet;

        String query = Query.showHistory + engineID + "';";


        db.openConnection();
        resultSet = db.selectQuery(query);

        while (resultSet.next()) {

            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, String.valueOf(resultSet.getDate(1)));
            item.setText(1, String.valueOf(resultSet.getInt(2)));
        }

        db.closeConnection();
    }
}
