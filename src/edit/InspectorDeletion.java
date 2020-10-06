package edit;

import database.DataBase;
import database.Query;
import info.InfoTable;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

public class InspectorDeletion {

    private Shell shell;
    private InfoTable infoTable;
    private DataBase db;

    public InspectorDeletion(Shell shell, InfoTable infoTable){
        this.shell = shell;
        this.infoTable = infoTable;
        db = new DataBase();
    }

    public void deleteInspector(String inspectorID) {

        String deletionString = Query.deleteInspector + inspectorID + "');";

        try {
            db.openConnection();
            db.updateQuery(deletionString);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //MessageBox box = new MessageBox(shell, SWT.OK);
        //box.setText("Info");
        //box.setMessage("An inspector has been deleted successfully!");
        //box.open();

    }

    public void redrawTable(){

        infoTable.getTable().removeAll();

        try {
            infoTable.updateInspectorTable(db.selectQuery(Query.showAllInspectors), infoTable.getTable());
            db.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
