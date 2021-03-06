package info;

import edit.DriverInsertionDialog;
import edit.InspectorInsertionDialog;
import edit.CheckupInsertionDialog;
import edit.DriverDeletion;
import edit.InspectorDeletion;
import edit.CheckupDeletion;
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
    public static  String[] checkupTitles = {"Date", "Result", "Car ID", "Inspector ID"};

    private String shellTitle;
    private String driverTitle = "driver";
    private String inspectorTitle = "inspector";
    private String checkupTitle = "checkup";

    Table table;
    Shell subShell;
    Button addRecordButton;
    Button editRecordButton;
    Button deleteRecordButton;


    public InfoTable(Shell parent, String shellTitle){

        subShell = new Shell(parent);
        this.shellTitle = shellTitle;
        subShell.setText(shellTitle);

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.horizontalSpacing = 50;
        subShell.setLayout(gridLayout);

        table = new Table(subShell, SWT.FULL_SELECTION);

        addRecordButton = new Button(subShell, SWT.PUSH);
        editRecordButton = new Button(subShell, SWT.PUSH);
        deleteRecordButton = new Button(subShell, SWT.PUSH);

    }

    public InfoTable getInfoTable() {
        return this;
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
                if(shellTitle == driverTitle) {

                    DriverInsertionDialog insertDriverWindow = new DriverInsertionDialog(subShell, getInfoTable());
                    insertDriverWindow.insertDriver();

                }
                else if (shellTitle == inspectorTitle){

                    InspectorInsertionDialog insertInspectorWindow = new InspectorInsertionDialog(subShell, getInfoTable());
                    insertInspectorWindow.insertInspector();
                }
                else if (shellTitle == checkupTitle) {

                    CheckupInsertionDialog insertCheckupWindow = new CheckupInsertionDialog(subShell, getInfoTable());
                    insertCheckupWindow.insertCheckup();
                }
            }
        });

        editRecordButton.addSelectionListener(new SelectionAdapter(){

            @Override
            public void widgetSelected(SelectionEvent e){
                if(shellTitle == driverTitle) {

                    String passportID = table.getSelection()[0].getText(4);
                    DriverDeletion driverDeletion = new DriverDeletion(getInfoTable());
                    driverDeletion.deleteDriver(passportID);

                    DriverInsertionDialog insertWindow = new DriverInsertionDialog(subShell, getInfoTable());
                    insertWindow.insertDriver();

                }
                else if(shellTitle == inspectorTitle){

                    String inspectorID = table.getSelection()[0].getText(1);
                    InspectorDeletion inspectorDeletion = new InspectorDeletion(getInfoTable());
                    inspectorDeletion.deleteInspector(inspectorID);

                    InspectorInsertionDialog insertInspectorWindow = new InspectorInsertionDialog(subShell, getInfoTable());
                    insertInspectorWindow.insertInspector();
                }
                else if(shellTitle == checkupTitle){

                    String checkupDate = table.getSelection()[0].getText(0);
                    String carID = table.getSelection()[0].getText(2);
                    CheckupDeletion checkupDeletion = new CheckupDeletion(getInfoTable());
                    checkupDeletion.deleteCheckup(checkupDate, carID);

                    CheckupInsertionDialog insertCheckupWindow = new CheckupInsertionDialog(subShell, getInfoTable());
                    insertCheckupWindow.insertCheckup();
                }
            }
        });

        deleteRecordButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if(shellTitle == driverTitle) {
                    String passportID = table.getSelection()[0].getText(4);
                    DriverDeletion driverDeletion = new DriverDeletion(getInfoTable());
                    driverDeletion.deleteDriver(passportID);
                    driverDeletion.redrawTable();
                }
                else if(shellTitle == inspectorTitle){

                    String inspectorID = table.getSelection()[0].getText(1);
                    InspectorDeletion inspectorDeletion = new InspectorDeletion(getInfoTable());
                    inspectorDeletion.deleteInspector(inspectorID);
                    inspectorDeletion.redrawTable();
                }
                else if(shellTitle == checkupTitle){

                    String checkupDate = table.getSelection()[0].getText(0);
                    String carID = table.getSelection()[0].getText(2);
                    CheckupDeletion checkupDeletion = new CheckupDeletion(getInfoTable());
                    checkupDeletion.deleteCheckup(checkupDate, carID);
                    checkupDeletion.redrawTable();
                }
            }
        });

        subShell.setBounds(400, 230, 1030, 400);
        subShell.open();
    }

    public void updateDriverTable(ResultSet resultSet, Table table) throws SQLException { //extend for all 3 tables

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
            item.setText(8, String.valueOf(resultSet.getDate(9)));
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
            item.setText(0, String.valueOf(resultSet.getDate(1)));
            item.setText(1, String.valueOf(resultSet.getInt(2)));
            item.setText(2, String.valueOf(resultSet.getInt(3)));
            item.setText(3, String.valueOf(resultSet.getInt(4)));
        }
    }
}
