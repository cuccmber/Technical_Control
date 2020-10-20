package edit;

import database.DataBase;
import database.Query;
import info.InfoTable;
import org.eclipse.swt.widgets.Shell;
import search.SelectionQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InspectorDeletion {

    private InfoTable infoTable;
    private PreparedStatement statement;
    private Connection connection;
    private String deleteInspector = "DELETE FROM inspector WHERE inspectorID = ?;";
    public static String showAllInspectors = "SELECT * FROM inspector";



    public InspectorDeletion(InfoTable infoTable){
        this.infoTable = infoTable;
    }

    public void deleteInspector(String inspectorID) {

        DataBase db = new DataBase();

        try {
            connection = db.openConnection();
            statement = connection.prepareStatement(deleteInspector);

            statement.setInt(1, Integer.parseInt(inspectorID));

            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void redrawTable(){

        infoTable.getTable().removeAll();

        SelectionQuery query = new SelectionQuery(showAllInspectors);

        try {
            infoTable.updateInspectorTable(query.show(), infoTable.getTable());
            query.closeAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
