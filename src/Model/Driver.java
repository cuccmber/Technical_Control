package Model;

import java.util.Date;

public class Driver {
    private int carID;
    private int engineID;
    private String color;
    private String brand;
    private int technicalPassportID; //primary_key
    private int licenceID;
    private String fullName;
    private String address;
    private Date birthday;
    private String sex;
    private int inspectorID;

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public int getCarID() {
        return carID;
    }

    public void setEngineID(int engineID) {
        this.engineID = engineID;
    }

    public int getEngineID() {
        return engineID;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setTechnicalPassportID(int technicalPassportID){
        this.technicalPassportID = technicalPassportID;
    }

    public int getTechnicalPassportID() {
        return technicalPassportID;
    }

    public void setLicenceID(int licenceID){
        this.licenceID = licenceID;
    }

    public int getLicenceID() {
        return licenceID;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setInspectorID(int inspectorID){
        this.inspectorID = inspectorID;
    }

    public int getInspectorID() {
        return inspectorID;
    }
}
