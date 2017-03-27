package com.example.user.nottspark.Model;

/**
 * Created by user on 27/2/2017.
 */

public class Car {
    private int carID;
    private String carMake;
    private String carModel;
    private String carPlate;

    public Car(int carID, String carMake, String carModel, String carPlate) {
        this.carID = carID;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carPlate = carPlate;
    }

    public Car() {
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }
}
