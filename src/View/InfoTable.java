package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class InfoTable{

    public static  String[] driverTitles = {"Car ID", "Engine ID", "Color", "Brand", "Passport ID", "Licence",
            "Full Name", "Address", "Birthday", "Sex"};
    public static String[] inspectorTitles = {"Full Name", "Inspector ID", "Post", "Rank"};
    public static  String[] checkupTitles = {"Date", "Result", "Check Up ID"};

    Table table;
    Shell subShell;
    Button addRecordButton;
    Button editRecordButton;
    Button deleteRecordButton;


    public InfoTable(Shell parent){

        subShell = new Shell(parent);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.horizontalSpacing = 50;
        subShell.setLayout(gridLayout);

        table = new Table(subShell, SWT.NONE);

        addRecordButton = new Button(subShell, SWT.PUSH);
        editRecordButton = new Button(subShell, SWT.PUSH);
        deleteRecordButton = new Button(subShell, SWT.PUSH);

    }

    public Table getTable() {
        return table;
    }

    public void drawTable(String[] titles){

        GridData tableGridData = new GridData(1000, 250);
        tableGridData.horizontalSpan = 3;
        table.setLayoutData(tableGridData);

        GridData buttonGridData = new GridData(300, 60);
        addRecordButton.setLayoutData(buttonGridData);
        editRecordButton.setLayoutData(buttonGridData);
        deleteRecordButton.setLayoutData(buttonGridData);

        table.setHeaderVisible(true);

        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).setWidth(100);
        }

        addRecordButton.setText("Add");
        editRecordButton.setText("Edit");
        deleteRecordButton.setText("Delete");

        addRecordButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                DriverInsertionWindow insertWindow = new DriverInsertionWindow(subShell);
                insertWindow.drawWindow();

            }
        });





        subShell.setBounds(400, 230, 1030, 400);
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
