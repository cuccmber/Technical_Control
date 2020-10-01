package Controller;

import Model.Checkup;
import Model.Driver;
import Model.Inspector;

import java.util.ArrayList;
import java.util.Date;                  //or .sql?

public class Controller {

    private ArrayList<Driver> drivers;
    private ArrayList<Inspector> inspectors;
    private ArrayList<Checkup> checkups;

    public void addDriver(int carID, int engineID, String color, String brand, int technicalPassportID,
                          int licenceID, String fullName, String address, Date birthday, String sex, int inspectorID){
        Driver driver = new Driver();
        driver.setCarID(carID);
        driver.setEngineID(engineID);
        driver.setColor(color);
        driver.setBrand(brand);
        driver.setTechnicalPassportID(technicalPassportID);
        driver.setLicenceID(licenceID);
        driver.setFullName(fullName);
        driver.setAddress(address);
        driver.setBirthday(birthday);
        driver.setSex(sex);
        driver.setInspectorID(inspectorID);

        drivers.add(driver);
    };
    public void editDriver(){};
    public void removeDriver(){};

    public void addInspector(int inspectorID, String fullName, String post, String inspectorRank){
        Inspector inspector = new Inspector();
        inspector.setInspectorID(inspectorID);
        inspector.setFullName(fullName);
        inspector.setPost(post);
        inspector.setInspectorRank(inspectorRank);

        inspectors.add(inspector);
    };
    public void editInspector(){};
    public void removeInspector(){};

    public void addCheckup(Date date, boolean result, int checkupID){
        Checkup checkup = new Checkup();
        checkup.setDate(date);
        checkup.setResult(result);
        checkup.setCheckupID(checkupID);

        checkups.add(checkup);
    };
    public void editCheckup(){};
    public void removeCheckup(){};

    public void countCars(){};
    public void showInspector(){};
    public void showHistory(){};



}
