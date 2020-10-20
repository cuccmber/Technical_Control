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

public class CheckupInsertionDialog {

    private Shell subShell;
    private InfoTable infoTable;
    private PreparedStatement statement;
    private Connection connection;
    private String insertionString = "INSERT INTO checkup (checkupDate, result, carID, inspectorID) VALUES (?, ?, ?, ?);";
    public static String showAllCheckups = "SELECT * FROM checkup";


    public CheckupInsertionDialog(Shell parent, InfoTable infoTable) {
        subShell = new Shell(parent);
        this.infoTable = infoTable;

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 4;
        gridLayout.horizontalSpacing = 50;
        gridLayout.verticalSpacing = 10;
        subShell.setLayout(gridLayout);

    }

    public void insertCheckup(){

        Label checkupDateLabel = new Label(subShell, SWT.NONE);
        checkupDateLabel.setText("Check up Date:");
        Label resultLabel = new Label(subShell, SWT.NONE);
        resultLabel.setText("Result:");
        Label carIDLabel = new Label(subShell, SWT.NONE);
        carIDLabel.setText("Car ID:");
        Label inspectorIDLabel = new Label(subShell, SWT.NONE);
        inspectorIDLabel.setText("Inspector ID:");

        Text checkupDateText = new Text(subShell, SWT.NONE);
        checkupDateText.setTextLimit(45);
        Combo resultCombo = new Combo(subShell, SWT.READ_ONLY);  //change to boolean
        resultCombo.add("0");
        resultCombo.add("1");
        Text carIDText = new Text(subShell, SWT.NONE);
        carIDText.setTextLimit(4);
        Text inspectorIDText = new Text(subShell, SWT.NONE);
        inspectorIDText.setTextLimit(4);

        Button insertButton = new Button(subShell, SWT.PUSH);
        insertButton.setText("Insert");
        insertButton.addSelectionListener(new SelectionAdapter(){

            @Override
            public void widgetSelected(SelectionEvent e){

                DataBase db = new DataBase();
                try {
                    connection = db.getOpenedConnection();
                    statement = connection.prepareStatement(insertionString);

                    statement.setDate(1, Date.valueOf(checkupDateText.getText()));
                    statement.setInt(2, Integer.parseInt(resultCombo.getText()));
                    statement.setInt(3, Integer.parseInt(carIDText.getText()));
                    statement.setInt(4, Integer.parseInt(inspectorIDText.getText()));

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
        gridData.horizontalSpan = 4;
        gridData.horizontalIndent = 90;
        gridData.heightHint = 50;
        insertButton.setLayoutData(gridData);

        subShell.setBounds(650, 650, 530, 180);
        subShell.open();

    }

    public void redrawTable(){

        infoTable.getTable().removeAll();
        SelectionQuery query = new SelectionQuery(showAllCheckups);
        try {
            infoTable.updateCheckupTable(query.show(), infoTable.getTable());
            query.closeAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
