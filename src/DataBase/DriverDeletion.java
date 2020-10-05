package DataBase;

import DataBase.DataBase;
import DataBase.Query;
import View.InfoTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

public class DriverDeletion {

    Shell shell;
    InfoTable infoTable;

    public DriverDeletion(Shell shell, InfoTable infoTable){
        this.shell = shell;
        this.infoTable = infoTable;
    }

    public void deleteDriver(String passportID){

        String deletionString = Query.deleteDriver + passportID + "');";

        DataBase db = new DataBase();
        try {
            db.openConnection();
            db.updateQuery(deletionString);
            infoTable.getTable().removeAll();
            infoTable.updateDriverTable(db.selectQuery(Query.showAllDrivers), infoTable.getTable());
            db.closeConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        MessageBox box = new MessageBox(shell, SWT.OK);
        box.setText("Info");
        box.setMessage("A driver has been deleted successfully!");
        box.open();

    }
}
