package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Window {
    private Display display = new Display();
    private Shell mainShell = new Shell(display);

    public void showWindow(){
        FillLayout layout = new FillLayout(SWT.VERTICAL);
        mainShell.setLayout(layout);

        Button driverButton = new Button(mainShell, SWT.PUSH);
        driverButton.setText("Show Driver Data");

        Button inspectorButton = new Button(mainShell, SWT.PUSH);
        inspectorButton.setText("Show Inspector Data");

        Button checkupButton = new Button(mainShell, SWT.PUSH);
        checkupButton.setText("Show Check Up Data");

        Label countCarsLabel = new Label(mainShell, SWT.NONE);
        countCarsLabel.setText("Calculate how many cars completed check up (grouped by days)");
        Button countCarsButton = new Button(mainShell, SWT.PUSH);
        countCarsButton.setText("Calculate");

        Label showInspectorLabel = new Label(mainShell, SWT.NONE);
        showInspectorLabel.setText("Show list of inspectors examined cars (grouped by days).");
        Button showInspectorButton = new Button(mainShell, SWT.PUSH);
        showInspectorButton.setText("Show");

        Label showHistoryLabel = new Label(mainShell, SWT.NONE);
        showHistoryLabel.setText("Show history of check ups");
        Button showHistoryButton = new Button(mainShell, SWT.PUSH);
        showHistoryButton.setText("Show");

        mainShell.open();
        while(!mainShell.isDisposed()) {
            if(!display.readAndDispatch()) {
                display.sleep();
            }
        }

    }
}

