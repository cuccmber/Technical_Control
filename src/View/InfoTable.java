package View;

import Model.DataBase;
import Model.Query;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InfoTable{

    public static  String[] driverTitles = {"Car ID", "Engine ID", "Color", "Brand", "Technical Passport ID", "Licence", "Full Name",
            "Address", "Birthday", "Sex"};
    public static String[] inspectorTitles = {"Full Name", "Inspector ID", "Post", "Inspector Rank"};
    public static  String[] checkupTitles = {"Date", "Result", "Check Up ID"};


    Table table;
    Shell subShell;


    public InfoTable(Shell parent){
        subShell = new Shell(parent);
        subShell.setLayout(new FillLayout());
        table = new Table(subShell, SWT.NONE);
    }

    public Table getTable() {
        return table;
    }

    public void drawTable(String[] titles){
        GridLayout gridLayout = new GridLayout();
        gridLayout.marginTop = 10;
        gridLayout.marginBottom = 10;
        gridLayout.marginLeft = 5;
        gridLayout.marginRight = 5;
        table.setLayout(gridLayout);

        table.setHeaderVisible(true);


        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }

        subShell.open();
    }

    public void updateDriverTable(ResultSet resultSet, Table table) throws SQLException {

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

    public void updateInspectorTable(ResultSet resultSet, Table table) throws SQLException {
        while (resultSet.next()) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, resultSet.getString(1));
            item.setText(1, String.valueOf(resultSet.getInt(2)));
            item.setText(2, resultSet.getString(3));
            item.setText(3, resultSet.getString(4));
        }
    }

    public void updateCheckupTable(ResultSet resultSet, Table table) throws SQLException {
        while (resultSet.next()) {
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText(0, String.valueOf(resultSet.getInt(1)));
            item.setText(1, String.valueOf(resultSet.getInt(2)));
            item.setText(2, String.valueOf(resultSet.getInt(3)));
            item.setText(3, String.valueOf(resultSet.getInt(4)));
        }
    }

    public void printCountCars(ResultSet resultSet, Table table) throws SQLException {
        while (resultSet.next()) {
            TableItem item = new TableItem(table, SWT.NONE);
            int date = resultSet.getInt(1);
            int count = resultSet.getInt(2);
            System.out.println(date + ": " + count);
        }
    }

    public void printShowInspector(ResultSet resultSet, Table table) throws SQLException {
        String date = resultSet.getString(1);
        String count = resultSet.getString(2);
        int sth = resultSet.getInt(3);
        System.out.println(date + ": " + count);
    }

    public void printShowHistory(ResultSet resultSet, Table table) throws SQLException {
        Date date = resultSet.getDate(1);
        boolean count = resultSet.getBoolean(2);
        System.out.println(date + ": " + count);
    }

    protected void checkSubclass() {
        // Disable the check that prevents subclassing of SWT components
    }

}
