package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Query {

    public static String showAllCheckups = "SELECT * FROM checkup";
    public static String showAllDrivers = "SELECT * FROM driver";
    public static String showAllInspectors = "SELECT * FROM inspector";
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

    public static void printAllCheckups(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            Date date = resultSet.getDate(1);
            boolean check = resultSet.getBoolean(2);
            int carID = resultSet.getInt(3);
            int inspectorID = resultSet.getInt(4);
            System.out.println(date + " " + check + " " + carID + " " + inspectorID);
        }
    }
    public static void printAllDrivers(ResultSet resultSet) throws SQLException {
         while (resultSet.next()) {
             int carID = resultSet.getInt(1);
             int engineID = resultSet.getInt(2);
             String color = resultSet.getString(3);
             String brand = resultSet.getString(4);
             int technicalPassportID = resultSet.getInt(5);
             int licenceID = resultSet.getInt(6);
             String fullName = resultSet.getString(7);
             String address = resultSet.getString(8);
             Date date = resultSet.getDate(9);
             String sex = resultSet.getString(10);
             System.out.println(carID + engineID + brand + technicalPassportID + licenceID + fullName + address + date + sex);
         }
    }

    public static void printAllInspectors(ResultSet  resultSet) throws SQLException {
        while (resultSet.next()) {
            String fullName = resultSet.getString(1);
            int inspectorID = resultSet.getInt(2);
            String post = resultSet.getString(3);
            String inspectorRank = resultSet.getString(4);
        }
    }
    public static void printCountCars(ResultSet resultSet) throws SQLException {
        int date = resultSet.getInt(1);
        int count = resultSet.getInt(2);
        System.out.println(date + ": " + count);
    }
}
