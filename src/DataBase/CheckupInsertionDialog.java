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

public class CheckupInsertionDialog {

    private Shell parent;
    private Shell subShell;
    private InfoTable infoTable;

    private String insertionString = "INSERT INTO checkup (checkupDate, result, carID, inspectorID) VALUES (";

    public CheckupInsertionDialog(Shell parent, InfoTable infoTable) {
        this.parent = parent;
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
            public void widgetSelected(SelectionEvent e){ //add type check?

                insertionString += ("'" + checkupDateText.getText() + "', '" + resultCombo.getText() + "', '" +
                        carIDText.getText() + "', '" + inspectorIDText.getText() + "');");

                DataBase db = new DataBase();
                try {
                    db.openConnection();
                    db.updateQuery(insertionString);
                    infoTable.getTable().removeAll();
                    infoTable.updateCheckupTable(db.selectQuery(Query.showAllCheckups), infoTable.getTable());
                    db.closeConnection();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                subShell.close();

                //MessageBox box = new MessageBox(parent, SWT.OK);
                //box.setText("Info");
                //box.setMessage("A check up has been added successfully!");
                //box.open();
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
}
