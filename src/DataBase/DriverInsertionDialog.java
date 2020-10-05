package DataBase;

import DataBase.DataBase;
import DataBase.Query;
import View.InfoTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.sql.SQLException;

public class DriverInsertionDialog {
    private Shell parent;
    private Shell subShell;
    private InfoTable infoTable;

    private String insertionString = "INSERT INTO driver (carID, engineID, color, brand, technicalPassportID, licenceID, " +
            "fullName, address, birthday, sex) VALUES (";

    public DriverInsertionDialog(Shell parent, InfoTable infoTable) {
        this.parent = parent;
        subShell = new Shell(parent);
        this.infoTable = infoTable;

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 10;
        gridLayout.horizontalSpacing = 30;
        gridLayout.verticalSpacing = 10;
        subShell.setLayout(gridLayout);

    }

    public void insertDriver(){

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
            public void widgetSelected(SelectionEvent e){ //add type check?

                insertionString += ("'" + carIDText.getText() + "', '" + engineIDText.getText() + "', '" +
                        colorText.getText() + "', '" + brandText.getText() + "', '" + passportIDText.getText() +
                        "', '" + licenceIDText.getText() + "', '" + fullNameText.getText() + "', '" +
                        addressText.getText() + "', '" + birthdayText.getText() + "', '" + sexCombo.getText() + "');");

                DataBase db = new DataBase();
                try {
                        db.openConnection();
                        db.updateQuery(insertionString);
                        infoTable.getTable().removeAll();
                        infoTable.updateDriverTable(db.selectQuery(Query.showAllDrivers), infoTable.getTable());
                        db.closeConnection();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                subShell.close();

                //MessageBox box = new MessageBox(parent, SWT.OK);
                //box.setText("Info");
                //box.setMessage("A driver has been added successfully!");
                //box.open();
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
}
