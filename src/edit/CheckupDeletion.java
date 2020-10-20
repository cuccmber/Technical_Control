package edit;

import database.DataBase;
import database.Query;
import info.InfoTable;
import org.eclipse.swt.widgets.Shell;
import search.SelectionQuery;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CheckupDeletion {

    InfoTable infoTable;
    private PreparedStatement statement;
    private Connection connection;
    public static String deleteCheckup = "DELETE FROM checkup WHERE (checkupDate = ? AND carID = ?);";
    public static String showAllCheckups = "SELECT * FROM checkup";

    public CheckupDeletion(InfoTable infoTable){
        this.infoTable = infoTable;

    }

    public void deleteCheckup(String checkupDate, String carID){

        DataBase db = new DataBase();

        try {
            connection = db.openConnection();
            statement = connection.prepareStatement(deleteCheckup);

            statement.setDate(1, Date.valueOf(checkupDate));
            statement.setInt(2, Integer.parseInt(carID));

            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void redrawTable(){

        infoTable.getTable().removeAll();

        SelectionQuery query = new SelectionQuery(showAllCheckups);

        try {
            infoTable.updateCheckupTable(query.show(), infoTable.getTable());
            query.closeAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
