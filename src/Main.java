import Model.DataBase;
import Model.Query;
import View.MainWindow;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Controller
        //MainWindow window = new MainWindow();
        //window.showWindow();

        DataBase db = new DataBase();
        Query.printAllCheckups(db.createQuery(Query.showAllCheckups));
    }

}
