package Controller;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Query {

    public static String showAllDrivers = "SELECT * FROM driver";
    public static String showAllInspectors = "SELECT * FROM inspector";
    public static String showAllCheckups = "SELECT * FROM checkup";
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

    public static String addNewDriver = "INSERT INTO driver";
    public static String addNewInspector = "INSERT INTO inspector";
    public static String addNewCheckup = "INSERT INTO checkup";

    public static String deleteDriver = "DELETE FROM driver WHERE (technicalPassportID = '";
    public static String deleteInspector = "DELETE FROM inspector WHERE (inspectorID = '";
    public static String deleteCheckupOne = "DELETE FROM checkup WHERE (checkupDate = '";
    public static String deleteCheckupTwo = "' AND carID = '";

}
