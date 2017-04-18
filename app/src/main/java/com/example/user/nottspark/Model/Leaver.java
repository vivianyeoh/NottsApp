package com.example.user.nottspark.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 27/2/2017.
 */

public class Leaver implements Parcelable {
    public static final Creator<Leaver> CREATOR = new Creator<Leaver>() {
        @Override
        public Leaver createFromParcel(Parcel in) {
            return new Leaver(in);
        }

        @Override
        public Leaver[] newArray(int size) {
            return new Leaver[size];
        }
    };
    private int leaverID;
    private int userID;
    private String location;
    private String leaverDesc;
    private int pairingStatus;//1 for yes
    private String leavingDateTime;

    public Leaver() {
    }

    public Leaver(int userID, String location, String leaverDesc, String leavingTime) {
        this.userID = userID;
        this.location = location;
        this.leaverDesc = leaverDesc.equals("") ? location : leaverDesc;
        pairingStatus = 0;
        this.leavingDateTime = new SimpleDateFormat("dd/MM/yyyy ").format(new Date()) + leavingTime.toUpperCase();

    }

    public Leaver(int leaverID, int userID, String location, String leaverDesc, int pairingStatus, String leavingDateTime) {
        this.leaverID = leaverID;
        this.userID = userID;
        this.location = location;
        this.leaverDesc = leaverDesc;
        this.pairingStatus = pairingStatus;
        this.leavingDateTime = leavingDateTime;
    }

    protected Leaver(Parcel in) {
        leaverID = in.readInt();
        userID = in.readInt();
        location = in.readString();
        leaverDesc = in.readString();
        pairingStatus = in.readInt();
        leavingDateTime = in.readString();
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

    public int getPairingStatus() {
        return pairingStatus;
    }

    public void setPairingStatus(int pairingStatus) {
        this.pairingStatus = pairingStatus;
    }

    public String getDate() {
        return leavingDateTime;
    }

    public void setDate(String leavingDateTime) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(leaverID);
        dest.writeInt(userID);
        dest.writeString(location);
        dest.writeString(leaverDesc);
        dest.writeInt(pairingStatus);
        dest.writeString(leavingDateTime);
    }
}
