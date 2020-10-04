package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.util.Date;

public class DriverInsertionWindow {
    private Shell subShell;

    private String insertionString = "INSERT INTO driver (carID, engineID, color, brand, technicalPassportID, licenceID, " +
            "fullName, address, birthday, sex) VALUES (";

    //getters

    public DriverInsertionWindow(Shell parent) {
        subShell = new Shell(parent);
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 10;
        gridLayout.horizontalSpacing = 30;
        gridLayout.verticalSpacing = 10;
        subShell.setLayout(gridLayout);

    }

    public void drawWindow(){


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

        //limit text length
        Text carIDText = new Text(subShell, SWT.NONE);
        Text engineIDText = new Text(subShell, SWT.NONE);
        Text colorText = new Text(subShell, SWT.NONE);
        Text brandText = new Text(subShell, SWT.NONE);
        Text passportIDText = new Text(subShell, SWT.NONE);
        Text licenceIDText = new Text(subShell, SWT.NONE);
        Text fullNameText = new Text(subShell, SWT.NONE);
        Text addressText = new Text(subShell, SWT.NONE);
        Text birthdayText = new Text(subShell, SWT.NONE);
        Text sexText = new Text(subShell, SWT.NONE);

        Button insertButton = new Button(subShell, SWT.PUSH);
        insertButton.setText("Insert");
        insertButton.addSelectionListener(new SelectionAdapter(){

            @Override
            public void widgetSelected(SelectionEvent e){

                insertionString += ("'" + carIDText.getText() + "', '" + engineIDText.getText() + "', '" +
                        colorText.getText() + "', '" + brandText.getText() + "', '" + passportIDText.getText() +
                        "', '" + licenceIDText.getText() + "', '" + fullNameText.getText() + "', '" +
                        addressText.getText() + "', '" + birthdayText.getText() +"');");
                System.out.println(insertionString);

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
