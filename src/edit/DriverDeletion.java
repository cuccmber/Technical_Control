package edit;

import database.DataBase;
import database.Query;
import info.InfoTable;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

public class DriverDeletion {

    private Shell shell;
    private InfoTable infoTable;
    private DataBase db;

    public DriverDeletion(Shell shell, InfoTable infoTable){
        this.shell = shell;
        this.infoTable = infoTable;
        db = new DataBase();

    }

    public void deleteDriver(String passportID){

        String deletionString = Query.deleteDriver + passportID + "');";

        try {
            db.openConnection();
            db.updateQuery(deletionString);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //MessageBox box = new MessageBox(shell, SWT.OK);
        //box.setText("Info");
        //box.setMessage("A driver has been deleted successfully!");
        //box.open();

    }

    public void redrawTable(){

        infoTable.getTable().removeAll();

        try {
            infoTable.updateDriverTable(db.selectQuery(Query.showAllDrivers), infoTable.getTable());
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
