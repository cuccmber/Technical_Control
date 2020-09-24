package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Query {
    public static String countCars = "SELECT checkupDate, COUNT(carID) " +
                "FROM checkup " +
                "WHERE result = 1 " +
                "AND checkupDate BETWEEN '2015-08-08' AND '2019-02-02'" +
                " GROUP BY checkupDate;";


    public static String showInspector = "SELECT fullName, inspectorRank, carID " +
                "FROM inspector " +
                "JOIN checkup ON inspector.inspectorID = checkup.inspectorID " +
                "WHERE checkupDate BETWEEN '2018-08-08' AND '2019-02-02';";


    public static String showHistory = "SELECT checkupDate, result " +
                "FROM checkup " +
                "JOIN driver ON driver.carID = checkup.carID " +
                "WHERE driver.engineID = '5542';";

    public static void printCountCars(ResultSet resultSet) throws SQLException {
        int date = resultSet.getInt(1);
        int count = resultSet.getInt(2);
        System.out.println(date + ": " + count);
    }
}
