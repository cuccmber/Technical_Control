package View;

import org.eclipse.swt.SWT;
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

        Button driverButton = new Button(viewAddRemoveGroup, SWT.PUSH);
        driverButton.setText("Show Driver Data");

        Button inspectorButton = new Button(viewAddRemoveGroup, SWT.PUSH);
        inspectorButton.setText("Show Inspector Data");


        Button checkupButton = new Button(viewAddRemoveGroup, SWT.PUSH);
        checkupButton.setText("Show Check Up Data");


        Button countCarsButton = new Button(queryGroup, SWT.RADIO);
        countCarsButton.setText("Calculate how many cars completed check up (grouped by days)");
        Button showInspectorButton = new Button(queryGroup, SWT.RADIO);
        showInspectorButton.setText("Show list of inspectors examined cars (grouped by days)");
        Button showHistoryButton = new Button(queryGroup, SWT.RADIO);
        showHistoryButton.setText("Show history of check ups");


        Label fromLabel = new Label(queryGroup, SWT.NONE);
        fromLabel.setText("From (YYYY-MM-DD):");
        Text fromText = new Text(queryGroup, SWT.NONE);
        Label tillLabel = new Label(queryGroup, SWT.NONE);
        tillLabel.setText("Till (YYYY-MM-DD):");
        Text tillText = new Text(queryGroup, SWT.NONE);


        Button calculateButton = new Button(queryGroup, SWT.PUSH);
        calculateButton.setText("Calculate");


        mainShell.setBounds(400, 200, 1000, 400);
        mainShell.open();
        while(!mainShell.isDisposed()) {
            if(!display.readAndDispatch()) {
                display.sleep();
            }
        }

    }
}

