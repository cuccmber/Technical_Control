package Model;

import java.util.Date;

public class Checkup {
    private Date date;
    private boolean result;
    private int checkupID; //primary key

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setCheckupID(int checkupID) {
        this.checkupID = checkupID;
    }

    public int getCheckupID() {
        return checkupID;
    }

}
