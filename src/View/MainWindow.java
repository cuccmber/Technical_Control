package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.*;

public class MainWindow {
    private Display display = new Display();
    private Shell mainShell = new Shell(display);

    public void showWindow(){
        FillLayout verticalLayout = new FillLayout(SWT.VERTICAL);
        FillLayout horizontalLayout = new FillLayout(SWT.HORIZONTAL);
        mainShell.setLayout(horizontalLayout);

        Group viewAddRemoveGroup = new Group(mainShell, SWT.NONE);
        viewAddRemoveGroup.setLayout(verticalLayout);
        Group queryGroup = new Group(mainShell, SWT.NONE);
        queryGroup.setLayout(verticalLayout);

        Button driverButton = new Button(viewAddRemoveGroup, SWT.RADIO);
        driverButton.setText("Show Driver Data");

        Button inspectorButton = new Button(viewAddRemoveGroup, SWT.RADIO);
        inspectorButton.setText("Show Inspector Data");

        Button checkupButton = new Button(viewAddRemoveGroup, SWT.RADIO);
        checkupButton.setText("Show Check Up Data");

        //Table infoTable = new Table(viewAddRemoveGroup, SWT.NONE);
        Table infoTable = new Table(viewAddRemoveGroup, SWT.NONE);

        driverButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if(driverButton.getSelection() == true){
                    DriverTable driverTable = new DriverTable();
                    driverTable.drawTable(infoTable);
                }
            }
        });

        //inspectorButton.addSelectionListener(new SelectionAdapter() {

        //    @Override
        //    public void widgetSelected(SelectionEvent e) {
        //        if(inspectorButton.getSelection() == true){
        //            InspectorTable inspectorTable = new InspectorTable();
        //            inspectorTable.drawTable(infoTable);
        //        }
        //    }
        //});
//
        //checkupButton.addSelectionListener(new SelectionAdapter() {
//
        //    @Override
        //    public void widgetSelected(SelectionEvent e) {
        //        if(checkupButton.getSelection() == true){
        //            CheckupTable checkupTable = new CheckupTable();
        //            checkupTable.drawTable(infoTable);
        //        }
        //    }
        //});
//
        //Button refreshTable = new Button(viewAddRemoveGroup, SWT.PUSH);
        //refreshTable.setText("Refresh");



        Button countCarsButton = new Button(queryGroup, SWT.PUSH);
        countCarsButton.setText("Calculate how many cars completed check up (grouped by days)");
        Button showInspectorButton = new Button(queryGroup, SWT.PUSH);
        showInspectorButton.setText("Show list of inspectors examined cars (grouped by days)");
        Button showHistoryButton = new Button(queryGroup, SWT.PUSH);
        showHistoryButton.setText("Show history of check ups");


        Label fromLabel = new Label(queryGroup, SWT.NONE);
        fromLabel.setText("From (YYYY-MM-DD):");
        Text fromText = new Text(queryGroup, SWT.NONE);
        Label tillLabel = new Label(queryGroup, SWT.NONE);
        tillLabel.setText("Till (YYYY-MM-DD):");
        Text tillText = new Text(queryGroup, SWT.NONE);

        mainShell.setBounds(400, 200, 1000, 400);
        mainShell.open();
        while(!mainShell.isDisposed()) {
            if(!display.readAndDispatch()) {
                display.sleep();
            }
        }

    }
}

