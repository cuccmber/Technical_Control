package edit;

import database.DataBase;
import database.Query;
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

public class InspectorInsertionDialog {

    private Shell subShell;
    private InfoTable infoTable;
    private PreparedStatement statement;
    private Connection connection;
    private String insertionString = "INSERT INTO inspector (fullName, inspectorID, post, inspectorRank) VALUES (?, ?, ?, ?);";
    private String showAllInspectors = "SELECT * FROM inspector";

    public InspectorInsertionDialog(Shell parent, InfoTable infoTable) {
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
            public void widgetSelected(SelectionEvent e){

                DataBase db = new DataBase();
                try {
                    connection = db.openConnection();
                    statement = connection.prepareStatement(insertionString);

                    statement.setString(1, fullNameText.getText());
                    statement.setInt(2, Integer.parseInt(inspectorIDText.getText()));
                    statement.setString(3, postText.getText());
                    statement.setString(4, rankText.getText());

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

        subShell.setBounds(650, 650, 500, 180);
        subShell.open();

    }

    public void redrawTable(){
        infoTable.getTable().removeAll();
        SelectionQuery query = new SelectionQuery(showAllInspectors);
        try {
            infoTable.updateInspectorTable(query.show(), infoTable.getTable());
            query.closeAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
