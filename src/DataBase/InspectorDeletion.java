package DataBase;

import View.InfoTable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import java.sql.SQLException;

public class InspectorDeletion {

    Shell shell;
    InfoTable infoTable;

    public InspectorDeletion(Shell shell, InfoTable infoTable){
        this.shell = shell;
        this.infoTable = infoTable;
    }

    public void deleteInspector(String inspectorID) {

        String deletionString = Query.deleteInspector + inspectorID + "');";

        DataBase db = new DataBase();
        try {
            db.openConnection();
            db.updateQuery(deletionString);
            infoTable.getTable().removeAll();
            infoTable.updateInspectorTable(db.selectQuery(Query.showAllInspectors), infoTable.getTable());
            db.closeConnection();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        MessageBox box = new MessageBox(shell, SWT.OK);
        box.setText("Info");
        box.setMessage("An inspector has been deleted successfully!");
        box.open();

    }
}
