package window;

import database.DataBase;
import database.Query;
import info.InfoTable;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

public class ShowAll {

    private Shell shell;

    public ShowAll(Shell shell){
        this.shell = shell;
    }

    public void overrideButton (Button driverButton, Button inspectorButton, Button checkupButton){
        if(driverButton.getSelection()){
            InfoTable infoTable = new InfoTable(shell, "driver");
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
        else if(inspectorButton.getSelection()){
            InfoTable infoTable = new InfoTable(shell, "inspector");
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
        else if(checkupButton.getSelection()){
            InfoTable infoTable = new InfoTable(shell, "checkup");
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
}
