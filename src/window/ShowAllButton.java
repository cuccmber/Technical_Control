package window;

import info.InfoTable;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import search.SelectionQuery;

import java.sql.SQLException;

public class ShowAllButton {

    private Shell shell;
    private String showAllDrivers = "SELECT * FROM driver";
    private String showAllInspectors = "SELECT * FROM inspector";
    private String showAllCheckups = "SELECT * FROM checkup";

    public ShowAllButton(Shell shell){
        this.shell = shell;
    }

    public void overrideButton (Button driverButton, Button inspectorButton, Button checkupButton){
        if(driverButton.getSelection()){

            InfoTable infoTable = new InfoTable(shell, "driver");
            infoTable.drawTable(InfoTable.driverTitles);
            SelectionQuery query = new SelectionQuery(showAllDrivers);

            try {
                infoTable.updateDriverTable(query.show(), infoTable.getTable());
                query.closeAll();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if(inspectorButton.getSelection()){

            InfoTable infoTable = new InfoTable(shell, "inspector");
            infoTable.drawTable(InfoTable.inspectorTitles);
            SelectionQuery query = new SelectionQuery(showAllInspectors);

            try {
                infoTable.updateInspectorTable(query.show(), infoTable.getTable());
                query.closeAll();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if(checkupButton.getSelection()){
            InfoTable infoTable = new InfoTable(shell, "checkup");
            infoTable.drawTable(InfoTable.checkupTitles);
            SelectionQuery query = new SelectionQuery(showAllCheckups);

            try {
                infoTable.updateCheckupTable(query.show(), infoTable.getTable());
                query.closeAll();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
