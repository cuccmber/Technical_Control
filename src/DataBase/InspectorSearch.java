package DataBase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InspectorSearch {


    public void searchInspector (Table table, String fromDate, String tillDate) throws SQLException {

        DataBase db = new DataBase();
        ResultSet resultSet;

        String query = Query.showInspector + fromDate + "' AND '" + tillDate + "' GROUP BY checkupDate;";


        db.openConnection();
        resultSet = db.selectQuery(query);

        while (resultSet.next()) {

            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, resultSet.getString(1));
            item.setText(1, resultSet.getString(2));
            item.setText(2, String.valueOf(resultSet.getInt(3)));
        }

        db.closeConnection();

    }
}
