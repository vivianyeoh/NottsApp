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
            if (getCarByID(car.getCarID()) != null) return true;
        }
        return false;
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

    public boolean updateCar(Car car) {
        if (car != null) {
            npd.updateCar(car);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteCar(int id) {
//        Car temp=getCarByID(id);
//        if (temp!= null) {
        npd.deleteCar(id);
        return true;
//        } else
//            return false;
    }
}
