package com.example.user.nottspark.Model;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 27/2/2017.
 */

public class Transaction {
    private int transID;
    private int parkerID;
    private int leaverID;
    private String exchangeStatus;
    private String exchangeTime;

    public Transaction() {
    }

    public Transaction(int transID, int parkerID, int leaverID, String exchangeStatus) {
        this.transID = transID;
        this.parkerID = parkerID;
        this.leaverID = leaverID;
        this.exchangeStatus = exchangeStatus;
        this.exchangeTime = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }

    public Transaction(int transID, int parkerID, int leaverID, String exchangeStatus, String exchangeTime) {
        this.transID = transID;
        this.parkerID = parkerID;
        this.leaverID = leaverID;
        this.exchangeStatus = exchangeStatus;
        this.exchangeTime = exchangeTime;
    }

    public int getTransID() {
        return transID;
    }

    public void setTransID(int transID) {
        this.transID = transID;
    }

    public int getParkerID() {
        return parkerID;
    }

    public void setParkerID(int parkerID) {
        this.parkerID = parkerID;
    }

    public int getLeaverID() {
        return leaverID;
    }

    public void setLeaverID(int leaverID) {
        this.leaverID = leaverID;
    }

    public String getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public String getExchangeTime() {
        return exchangeTime;
    }

    public void setExchangeTime(String exchangeTime) {
        this.exchangeTime = exchangeTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transID=" + transID +
                ", parkerID=" + parkerID +
                ", leaverID=" + leaverID +
                ", exchangeStatus='" + exchangeStatus + '\'' +
                ", exchangeTime='" + exchangeTime + '\'' +
                '}';
    }
}
