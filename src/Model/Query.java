package Model;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Query {

    public static String showAllCheckups = "SELECT * FROM checkup";
    public static String showAllDrivers = "SELECT * FROM driver";
    public static String showAllInspectors = "SELECT * FROM inspector";
    public static String countCars = "SELECT checkupDate, COUNT(carID) " +
                "FROM checkup " +
                "WHERE result = 1 " +
                "AND checkupDate BETWEEN '2015-08-08' AND '2019-02-02'" +
                " GROUP BY checkupDate;";
    public static String showInspector = "SELECT fullName, inspectorRank, carID " +
                "FROM inspector " +
                "JOIN checkup ON inspector.inspectorID = checkup.inspectorID " +
                "WHERE checkupDate BETWEEN '2018-08-08' AND '2019-02-02';";
    public static String showHistory = "SELECT checkupDate, result " +
                "FROM checkup " +
                "JOIN driver ON driver.carID = checkup.carID " +
                "WHERE driver.engineID = '5542';";

    public static void printAllDrivers(ResultSet resultSet, Table table) throws SQLException {
         while (resultSet.next()) {

             TableItem item = new TableItem(table, SWT.NONE);
             item.setText(0, String.valueOf(resultSet.getInt(1)));
             item.setText(1, String.valueOf(resultSet.getInt(2)));
             item.setText(2, resultSet.getString(3));
             item.setText(3, resultSet.getString(4));
             item.setText(4, String.valueOf(resultSet.getInt(5)));
             item.setText(5, String.valueOf(resultSet.getInt(6)));
             item.setText(6, resultSet.getString(7));
             item.setText(7, resultSet.getString(8));
             item.setText(8, String.valueOf(resultSet.getInt(9)));
             item.setText(9, resultSet.getString(10));

         }
    }

    public static void printAllInspectors(ResultSet  resultSet, Table table) throws SQLException {
        while (resultSet.next()) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, resultSet.getString(1));
            item.setText(1, String.valueOf(resultSet.getInt(2)));
            item.setText(2, resultSet.getString(3));
            item.setText(3, resultSet.getString(4));

        }
    }

    public static void printAllCheckups(ResultSet resultSet, Table table) throws SQLException {
        while (resultSet.next()) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, String.valueOf(resultSet.getInt(1)));
            item.setText(1, String.valueOf(resultSet.getInt(2)));
            item.setText(2, String.valueOf(resultSet.getInt(3)));
            item.setText(3, String.valueOf(resultSet.getInt(4)));

        }
    }

    public static void printCountCars(ResultSet resultSet) throws SQLException {
        int date = resultSet.getInt(1);
        int count = resultSet.getInt(2);
        System.out.println(date + ": " + count);
    }
}
