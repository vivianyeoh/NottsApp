package com.example.user.nottspark.Controller;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.example.user.nottspark.Database.NottsParkDatabase;
import com.example.user.nottspark.Model.Car;

import java.util.List;

/**
 * Created by user on 16/3/2017.
 */

public class CarController {

    NottsParkDatabase npd;

    public CarController(Context app_context) {
        npd = new NottsParkDatabase(app_context);
    }

    public CarController(FragmentActivity fragmentActivity, Context app_context) {
        npd = new NottsParkDatabase(fragmentActivity, app_context);
    }

    public int addCar(Car car) {
        if(getCar(car.getCarID())==null) {
            npd.addCar(car);
            return 1;
        }else
            return -1;
    }

    public Car getCar(int id) {
        return npd.getCar(id);
    }

    public List<Car> getAllCar() {
        return npd.getAllCar();
    }

    public List<Car> getAllCarsByID(int id) {
        return npd.getAllCarsByID(id);
    }

    public int getCount() {
        return npd.getCount("Car");
    }

    public int updateCar(Car car) {
        if (car != null) {
            return npd.updateCar(car);
        }else{
            return -1;
        }
    }

    public int deleteCar(int id) {
        if (getCar(id) != null) {
            deleteCar(id);
            return 1;
        } else
            return -1;
    }
}
