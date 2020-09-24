import Model.DataBase;
import Model.Query;

public class Main {
    public static void main(String[] args) {
        //Controller
        //Window window = new Window();
        //window.showWindow();

        DataBase db = new DataBase();
        db.createQuery(Query.countCars);
    }

}
