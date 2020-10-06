package window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;


public class MainWindow {
    private Display display = new Display();
    private Shell mainShell = new Shell(display);

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
                ShowAll showAll = new ShowAll(mainShell);
                showAll.overrideButton(driverButton, inspectorButton, checkupButton);
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

                CountCars countCars =new CountCars(mainShell);
                countCars.overrideButton(fromText, tillText);

            }
        });

        showInspectorButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                ShowInspector showInspector =new ShowInspector(mainShell);
                showInspector.overrideButton(fromText, tillText);

            }
        });

        showHistoryButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {

                ShowHistory showHistory = new ShowHistory(mainShell);
                showHistory.overrideButton(engineIDText);

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

