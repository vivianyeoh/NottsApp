package com.example.user.nottspark.Controller;

import android.content.Context;

import com.example.user.nottspark.Database.MaintainCarDBTable;
import com.example.user.nottspark.Model.Car;

import java.util.List;

/**
 * Created by user on 16/3/2017.
 */

public class CarController {

    MaintainCarDBTable npd;

    public CarController(Context app_context) {
        npd = new MaintainCarDBTable(app_context);
    }

    public void addCar(Car car) {
        npd.addCar(car);
    }

    public void updateCar(Car car) {
        npd.updateCar(car);
    }

    public Car getCarByID(int id) {
        return npd.getDownload1car(id);
    }

    public List<Car> getAllCar() {
        return npd.getCarList();
    }

    public int getCount() {
        return npd.getCount();
    }

    public void deleteCar(int id) {
        npd.deleteCar(id);
    }
}
