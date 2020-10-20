package edit;

import database.DataBase;
import info.InfoTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import search.SelectionQuery;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverInsertionDialog {

    private Shell subShell;
    private InfoTable infoTable;
    private PreparedStatement statement;
    private Connection connection;
    private String insertionString = "INSERT INTO driver (carID, engineID, color, brand, technicalPassportID," +
            " licenceID, fullName, address, birthday, sex) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private String showAllDrivers = "SELECT * FROM driver";

    public DriverInsertionDialog(Shell parent, InfoTable infoTable) {
        subShell = new Shell(parent);
        this.infoTable = infoTable;

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 10;
        gridLayout.horizontalSpacing = 30;
        gridLayout.verticalSpacing = 10;
        subShell.setLayout(gridLayout);

    }

    public void insertDriver() {

        Label carIDLabel = new Label(subShell, SWT.NONE);
        carIDLabel.setText("Car ID:");
        Label engineIDLabel = new Label(subShell, SWT.NONE);
        engineIDLabel.setText("Engine ID:");
        Label colorLabel = new Label(subShell, SWT.NONE);
        colorLabel.setText("Color:");
        Label brandLabel = new Label(subShell, SWT.NONE);
        brandLabel.setText("Brand:");
        Label passportIDLabel = new Label(subShell, SWT.NONE);
        passportIDLabel.setText("Passport ID:");
        Label licenceIDLabel = new Label(subShell, SWT.NONE);
        licenceIDLabel.setText("Licence ID:");
        Label fullNameLabel = new Label(subShell, SWT.NONE);
        fullNameLabel.setText("FullName:");
        Label addressLabel = new Label(subShell, SWT.NONE);
        addressLabel.setText("Address:");
        Label birthdayLabel = new Label(subShell, SWT.NONE);
        birthdayLabel.setText("Birthday:");
        Label sexLabel = new Label(subShell, SWT.NONE);
        sexLabel.setText("Sex:");

        Text carIDText = new Text(subShell, SWT.NONE);
        carIDText.setTextLimit(4);
        Text engineIDText = new Text(subShell, SWT.NONE);
        engineIDText.setTextLimit(4);
        Text colorText = new Text(subShell, SWT.NONE);
        colorText.setTextLimit(45);
        Text brandText = new Text(subShell, SWT.NONE);
        brandText.setTextLimit(45);
        Text passportIDText = new Text(subShell, SWT.NONE);
        passportIDText.setTextLimit(6);
        Text licenceIDText = new Text(subShell, SWT.NONE);
        licenceIDText.setTextLimit(4);
        Text fullNameText = new Text(subShell, SWT.NONE);
        fullNameText.setTextLimit(45);
        Text addressText = new Text(subShell, SWT.NONE);
        addressText.setTextLimit(45);
        Text birthdayText = new Text(subShell, SWT.NONE);
        birthdayText.setTextLimit(10);
        Combo sexCombo = new Combo(subShell, SWT.READ_ONLY);
        sexCombo.add("male");
        sexCombo.add("female");

        Button insertButton = new Button(subShell, SWT.PUSH);
        insertButton.setText("Insert");
        insertButton.addSelectionListener(new SelectionAdapter(){

            @Override
            public void widgetSelected(SelectionEvent e){

                DataBase db = new DataBase();
                try {
                    connection = db.getOpenedConnection();
                    statement = connection.prepareStatement(insertionString);

                    statement.setInt(1, Integer.parseInt(carIDText.getText()));
                    statement.setInt(2, Integer.parseInt(engineIDText.getText()));
                    statement.setString(3, colorText.getText());
                    statement.setString(4, brandText.getText());
                    statement.setInt(5, Integer.parseInt(passportIDText.getText()));
                    statement.setInt(6, Integer.parseInt(licenceIDText.getText()));
                    statement.setString(7, fullNameText.getText());
                    statement.setString(8, addressText.getText());
                    statement.setDate(9, Date.valueOf(birthdayText.getText()));
                    statement.setString(10, sexCombo.getText());

                    statement.executeUpdate();

                    statement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                redrawTable();
                subShell.close();

            }
        });

        GridData gridData = new GridData(300, 40);
        gridData.horizontalSpan = 8;
        gridData.horizontalIndent = 350;
        gridData.heightHint = 50;
        insertButton.setLayoutData(gridData);

        subShell.setBounds(400, 650, 1030, 180);
        subShell.open();

    }

    public void redrawTable(){
        infoTable.getTable().removeAll();
        SelectionQuery query = new SelectionQuery(showAllDrivers);
        try {
            infoTable.updateDriverTable(query.show(), infoTable.getTable());
            query.closeAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
