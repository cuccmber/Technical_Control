package DataBase;

import View.InfoTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

public class CheckupDeletion {

    Shell shell;
    InfoTable infoTable;
    DataBase db;

    public CheckupDeletion(Shell shell, InfoTable infoTable){
        this.shell = shell;
        this.infoTable = infoTable;
        db = new DataBase();

    }

    public void deleteCheckup(String checkupDate, String carID){

        String deletionString = Query.deleteCheckupOne + checkupDate + Query.deleteCheckupTwo + carID + "');";

        try {
            db.openConnection();
            db.updateQuery(deletionString);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //MessageBox box = new MessageBox(shell, SWT.OK);
        //box.setText("Info");
        //box.setMessage("A check up has been deleted successfully!");
        //box.open();

    }

    public void redrawTable(){

        infoTable.getTable().removeAll();

        try {
            infoTable.updateCheckupTable(db.selectQuery(Query.showAllCheckups), infoTable.getTable());
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
