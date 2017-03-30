package com.example.user.nottspark.Model;

/**
 * Created by user on 27/2/2017.
 */

public class Car {
    private int KEY_CAR_ID;
    private String KEY_CAR_MAKE;
    private String KEY_CAR_MODEL;
    private String KEY_CAR_PLATE;

    public Car(int carID, String carMake, String carModel, String carPlate) {
        this.KEY_CAR_ID = carID;
        this.KEY_CAR_MAKE = carMake;
        this.KEY_CAR_MODEL = carModel;
        this.KEY_CAR_PLATE = carPlate;
    }

    public Car() {
    }

    public int getCarID() {
        return KEY_CAR_ID;
    }

    public void setCarID(int carID) {
        this.KEY_CAR_ID = carID;
    }

    public String getCarMake() {
        return KEY_CAR_MAKE;
    }

    public void setCarMake(String carMake) {
        this.KEY_CAR_MAKE = carMake;
    }

    public String getCarModel() {
        return KEY_CAR_MODEL;
    }

    public void setCarModel(String carModel) {
        this.KEY_CAR_MODEL = carModel;
    }

    public String getCarPlate() {
        return KEY_CAR_PLATE;
    }

    public void setCarPlate(String carPlate) {
        this.KEY_CAR_PLATE = carPlate;
    }

    @Override
    public String toString() {
        return "ClassPojo [KEY_CAR_ID = " + KEY_CAR_ID + ", KEY_CAR_PLATE = " + KEY_CAR_PLATE + ", KEY_CAR_MODEL = " + KEY_CAR_MODEL + ", KEY_CAR_MAKE = " + KEY_CAR_MAKE + "]";
    }
}