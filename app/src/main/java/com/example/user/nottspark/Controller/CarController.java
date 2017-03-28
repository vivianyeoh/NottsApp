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

    public boolean addCar(Car car) {
        if (getCarByID(car.getCarID()) == null) {
            npd.addCar(car);
            return true;
        } else
            return false;
    }

    public Car getCarByID(int id) {
        return npd.getCarById(id);
    }

    public List<Car> getAllCar() {
        return npd.getAllCar();
    }

    public int getCount() {
        return npd.getCount();
    }

//    public int updateCar(Car car) {
//        if (car != null) {
//            return npd.updateCar(car);
//        } else {
//            return -1;
//        }
//    }

//    public int deleteCar(int id) {
//        if (getCar(id) != null) {
//            deleteCar(id);
//            return 1;
//        } else
//            return -1;
//    }
}
