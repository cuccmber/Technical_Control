package edit;

import database.DataBase;
import database.Query;
import info.InfoTable;
import org.eclipse.swt.widgets.Shell;
import search.SelectionQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DriverDeletion {

    private InfoTable infoTable;
    private PreparedStatement statement;
    private Connection connection;
    private String deleteDriver = "DELETE FROM driver WHERE technicalPassportID = ?;";
    private String showAllDrivers = "SELECT * FROM driver";

    public DriverDeletion(InfoTable infoTable){
        this.infoTable = infoTable;
    }

    public void deleteDriver(String passportID){

        DataBase db = new DataBase();

        try {
            connection = db.openConnection();
            statement = connection.prepareStatement(deleteDriver);

            statement.setInt(1, Integer.parseInt(passportID));

            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void redrawTable(){

        infoTable.getTable().removeAll();

        SelectionQuery query = new SelectionQuery(showAllDrivers);

        try {
            infoTable.updateDriverTable(query.show(), infoTable.getTable());
            query.closeAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
