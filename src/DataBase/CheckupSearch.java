package DataBase;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckupSearch {

    public void searchCheckup (Table table, String fromDate, String tillDate) throws SQLException {

        DataBase db = new DataBase();
        ResultSet resultSet;

        String query = Query.countCars + fromDate + "' AND '" + tillDate + "' GROUP BY checkupDate;";


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
