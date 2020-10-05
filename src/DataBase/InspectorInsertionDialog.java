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

public class InspectorInsertionDialog {

    private Shell parent;
    private Shell subShell;
    private InfoTable infoTable;

    private String insertionString = "INSERT INTO inspector (fullName, inspectorID, post, inspectorRank) VALUES (";

    public InspectorInsertionDialog(Shell parent, InfoTable infoTable) {
        this.parent = parent;
        subShell = new Shell(parent);
        this.infoTable = infoTable;

        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 4;
        gridLayout.horizontalSpacing = 50;
        gridLayout.verticalSpacing = 10;
        subShell.setLayout(gridLayout);

    }

    public void insertInspector(){

        Label fullNameLabel = new Label(subShell, SWT.NONE);
        fullNameLabel.setText("Full Name:");
        Label inspectorIDLabel = new Label(subShell, SWT.NONE);
        inspectorIDLabel.setText("Inspector ID:");
        Label postLabel = new Label(subShell, SWT.NONE);
        postLabel.setText("Post:");
        Label rankLabel = new Label(subShell, SWT.NONE);
        rankLabel.setText("Rank:");

        //limit text length
        Text fullNameText = new Text(subShell, SWT.NONE);
        fullNameText.setTextLimit(45);
        Text inspectorIDText = new Text(subShell, SWT.NONE);
        inspectorIDText.setTextLimit(4);
        Text postText = new Text(subShell, SWT.NONE);
        postText.setTextLimit(45);
        Text rankText = new Text(subShell, SWT.NONE);
        rankText.setTextLimit(45);

        Button insertButton = new Button(subShell, SWT.PUSH);
        insertButton.setText("Insert");
        insertButton.addSelectionListener(new SelectionAdapter(){

            @Override
            public void widgetSelected(SelectionEvent e){ //add type check?

                insertionString += ("'" + fullNameText.getText() + "', '" + inspectorIDText.getText() + "', '" +
                        postText.getText() + "', '" + rankText.getText() + "');");

                DataBase db = new DataBase();
                try {
                    db.openConnection();
                    db.updateQuery(insertionString);
                    infoTable.getTable().removeAll();
                    infoTable.updateInspectorTable(db.selectQuery(Query.showAllInspectors), infoTable.getTable());
                    db.closeConnection();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                subShell.close();

                //MessageBox box = new MessageBox(parent, SWT.OK);
                //box.setText("Info");
                //box.setMessage("An inspector has been added successfully!");
                //box.open();
            }
        });

        GridData gridData = new GridData(300, 40);
        gridData.horizontalSpan = 4;
        gridData.horizontalIndent = 90;
        gridData.heightHint = 50;
        insertButton.setLayoutData(gridData);

        subShell.setBounds(650, 650, 500, 180);
        subShell.open();

    }
}
