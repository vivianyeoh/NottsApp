package com.example.user.nottspark.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 27/2/2017.
 */

public class Leaver {
    private int leaverID;
    private int userID;
    private String location;
    private String leaverDesc;
    private int pairingStatus;//1 for yes
    private String leavingDateTime;

    public Leaver() {
    }


    public Leaver(int leaverID, int userID, String location, String leaverDesc, int pairingStatus) {
        this.leaverID = leaverID;
        this.userID = userID;
        this.location = location;
        this.leaverDesc = leaverDesc;
        this.pairingStatus = pairingStatus;
        this.leavingDateTime = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a").format(new Date());
    }

    public Leaver(int leaverID, int userID, String location, String leaverDesc, int pairingStatus, String leavingDateTime) {
        this.leaverID = leaverID;
        this.userID = userID;
        this.location = location;
        this.leaverDesc = leaverDesc;
        this.pairingStatus = pairingStatus;
        this.leavingDateTime = leavingDateTime;
    }

    public int getLeaverID() {
        return leaverID;
    }

    public void setLeaverID(int leaverID) {
        this.leaverID = leaverID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLeaverDesc() {
        return leaverDesc;
    }

    public void setLeaverDesc(String leaverDesc) {
        this.leaverDesc = leaverDesc;
    }

    public int isPairingStatus() {
        return pairingStatus;
    }

    public void setPairingStatus(int pairingStatus) {
        this.pairingStatus = pairingStatus;
    }

    public String getLeavingTime() {
        return leavingDateTime;
    }

    public void setLeavingTime(String leavingDateTime) {
        this.leavingDateTime = leavingDateTime;
    }

    @Override
    public String toString() {
        return "Leaver{" +
                "leaverID=" + leaverID +
                ", userID=" + userID +
                ", location='" + location + '\'' +
                ", leaverDesc='" + leaverDesc + '\'' +
                ", pairingStatus=" + pairingStatus +
                ", leavingDateTime='" + leavingDateTime + '\'' +
                '}';
    }
}
