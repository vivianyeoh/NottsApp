package com.example.user.nottspark.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 27/2/2017.
 */

public class Leaver {
    private int leaverID;
    private User userID;
    private String location;
    private String leaverDesc;
    private boolean nowOrAfter10Min, pairingStatus;//1 for yes
    private String leavingDate;
    private String leavingTime;

    public Leaver() {
    }


    public Leaver(int leaverID, User userID, String location, String leaverDesc, boolean pairingStatus, boolean nowOrAfter10Min) {
        this.leaverID = leaverID;
        this.userID = userID;
        this.location = location;
        this.leaverDesc = leaverDesc;
        this.pairingStatus = pairingStatus;
        this.nowOrAfter10Min = nowOrAfter10Min;
        this.leavingDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        this.leavingTime = new SimpleDateFormat("h:mm:ss a").format(new Date());
    }

    public Leaver(int leaverID, User userID, String location, String leaverDesc, boolean nowOrAfter10Min, boolean pairingStatus, String leavingDate, String leavingTime) {
        this.leaverID = leaverID;
        this.userID = userID;
        this.location = location;
        this.leaverDesc = leaverDesc;
        this.nowOrAfter10Min = nowOrAfter10Min;
        this.pairingStatus = pairingStatus;
        this.leavingDate = leavingDate;
        this.leavingTime = leavingTime;
    }

    public int getLeaverID() {
        return leaverID;
    }

    public void setLeaverID(int leaverID) {
        this.leaverID = leaverID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
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

    public boolean isNowOrAfter10Min() {
        return nowOrAfter10Min;
    }

    public void setNowOrAfter10Min(boolean nowOrAfter10Min) {
        this.nowOrAfter10Min = nowOrAfter10Min;
    }

    public boolean isPairingStatus() {
        return pairingStatus;
    }

    public void setPairingStatus(boolean pairingStatus) {
        this.pairingStatus = pairingStatus;
    }

    public String getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(String leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(String leavingTime) {
        this.leavingTime = leavingTime;
    }

    @Override
    public String toString() {
        return "Leaver{" +
                "leaverID=" + leaverID +
                ", userID=" + userID +
                ", location='" + location + '\'' +
                ", leaverDesc='" + leaverDesc + '\'' +
                ", nowOrAfter10Min=" + nowOrAfter10Min +
                ", pairingStatus=" + pairingStatus +
                ", leavingDate='" + leavingDate + '\'' +
                ", leavingTime='" + leavingTime + '\'' +
                '}';
    }
}
