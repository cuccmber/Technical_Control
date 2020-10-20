package database;

public class Query {

    public static String showAllDrivers = "SELECT * FROM driver";
    public static String showAllInspectors = "SELECT * FROM inspector";
    public static String showAllCheckups = "SELECT * FROM checkup";

    public static String deleteDriver = "DELETE FROM driver WHERE (technicalPassportID = '";
    public static String deleteInspector = "DELETE FROM inspector WHERE (inspectorID = '";
    public static String deleteCheckupOne = "DELETE FROM checkup WHERE (checkupDate = '";
    public static String deleteCheckupTwo = "' AND carID = '";


}
