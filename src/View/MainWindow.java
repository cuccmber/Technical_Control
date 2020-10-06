package View;

import DataBase.DataBase;
import DataBase.Query;
import DataBase.SearchResultTable;
import DataBase.CheckupSearch;
import DataBase.InspectorSearch;
import DataBase.HistorySearch;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import java.sql.SQLException;

public class MainWindow {
    private Display display = new Display();
    private Shell mainShell = new Shell(display);
    private String driverTitle = "driver";
    private String inspectorTitle = "inspector";
    private String checkupTitle = "checkup";

    public void showWindow(){

        mainShell.setText("Car Inspection");

        FillLayout verticalLayout = new FillLayout(SWT.VERTICAL);
        FillLayout horizontalLayout = new FillLayout(SWT.HORIZONTAL);
        mainShell.setLayout(horizontalLayout);

        Group viewAddRemoveGroup = new Group(mainShell, SWT.NONE);
        viewAddRemoveGroup.setLayout(verticalLayout);

        Group queryGroup = new Group(mainShell, SWT.NONE);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.horizontalSpacing = 30;
        queryGroup.setLayout(gridLayout);

        Button driverButton = new Button(viewAddRemoveGroup, SWT.RADIO);
        driverButton.setText("Show Driver Data");

        Button inspectorButton = new Button(viewAddRemoveGroup, SWT.RADIO);
        inspectorButton.setText("Show Inspector Data");

        Button checkupButton = new Button(viewAddRemoveGroup, SWT.RADIO);
        checkupButton.setText("Show Check Up Data");

        Button showTableButton = new Button(viewAddRemoveGroup, SWT.PUSH);
        showTableButton.setText("Show");
        showTableButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if(driverButton.getSelection() == true){
                    InfoTable infoTable = new InfoTable(mainShell, driverTitle);
                    infoTable.drawTable(InfoTable.driverTitles);
                    DataBase db = new DataBase();

                    try {
                        db.openConnection();
                        infoTable.updateDriverTable(db.selectQuery(Query.showAllDrivers), infoTable.getTable());
                        db.closeConnection();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(inspectorButton.getSelection() == true){
                    InfoTable infoTable = new InfoTable(mainShell, inspectorTitle);
                    infoTable.drawTable(InfoTable.inspectorTitles);
                    DataBase db = new DataBase();

                    try {
                        db.openConnection();
                        infoTable.updateInspectorTable(db.selectQuery(Query.showAllInspectors), infoTable.getTable());
                        db.closeConnection();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(checkupButton.getSelection() == true){
                    InfoTable infoTable = new InfoTable(mainShell, checkupTitle);
                    infoTable.drawTable(InfoTable.checkupTitles);
                    DataBase db = new DataBase();

                    try {
                        db.openConnection();
                        infoTable.updateCheckupTable(db.selectQuery(Query.showAllCheckups), infoTable.getTable());
                        db.closeConnection();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        GridData buttonGridData = new GridData(490, 77);
        buttonGridData.horizontalSpan = 2;

        Button countCarsButton = new Button(queryGroup, SWT.PUSH);
        countCarsButton.setText("Calculate how many cars completed check up (grouped by days)");
        countCarsButton.setLayoutData(buttonGridData);


        Button showInspectorButton = new Button(queryGroup, SWT.PUSH);
        showInspectorButton.setText("Show list of inspectors examined cars (grouped by days)");
        showInspectorButton.setLayoutData(buttonGridData);


        Button showHistoryButton = new Button(queryGroup, SWT.PUSH);
        showHistoryButton.setText("Show history of check ups");
        showHistoryButton.setLayoutData(buttonGridData);


        Label fromLabel = new Label(queryGroup, SWT.NONE);
        fromLabel.setText("From (YYYY-MM-DD):");

        Text fromText = new Text(queryGroup, SWT.NONE);

        Label tillLabel = new Label(queryGroup, SWT.NONE);
        tillLabel.setText("Till (YYYY-MM-DD):");

        Text tillText = new Text(queryGroup, SWT.NONE);

        Label engineIDLabel = new Label(queryGroup, SWT.NONE);
        engineIDLabel.setText("Engine ID:");

        Text engineIDText = new Text(queryGroup, SWT.NONE);

        countCarsButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                String fromDate = fromText.getText();
                String tillDate = tillText.getText();

                SearchResultTable checkupInfo = new SearchResultTable(mainShell);
                CheckupSearch checkupSearch = new CheckupSearch();
                checkupInfo.createTable(checkupInfo.checkupBetweenInfoTitles);

                try {
                    checkupSearch.searchCheckup(checkupInfo.getTable(), fromDate, tillDate);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                checkupInfo.showTable();

            }

        });

        showInspectorButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                String fromDate = fromText.getText();
                String tillDate = tillText.getText();

                SearchResultTable inspectorInfo = new SearchResultTable(mainShell);
                InspectorSearch inspectorSearch = new InspectorSearch();
                inspectorInfo.createTable(inspectorInfo.inspectorBetweenInfoTitles);

                try {
                    inspectorSearch.searchInspector(inspectorInfo.getTable(), fromDate, tillDate);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                inspectorInfo.showTable();

            }
        });

        showHistoryButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                String engineID = engineIDText.getText();

                SearchResultTable historyInfo = new SearchResultTable(mainShell);
                HistorySearch historySearch = new HistorySearch();
                historyInfo.createTable(historyInfo.checkupHistoryInfo);

                try {
                    historySearch.searchHistory(historyInfo.getTable(), engineID);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                historyInfo.showTable();

            }

        });



        mainShell.setBounds(400, 230, 1030, 400);
        mainShell.open();
        while(!mainShell.isDisposed()) {
            if(!display.readAndDispatch()) {
                display.sleep();
            }
        }

    }
}

