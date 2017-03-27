package com.example.user.nottspark.Controller;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.example.user.nottspark.Database.NottsParkDatabase;
import com.example.user.nottspark.Model.User;

import java.util.List;

/**
 * Created by user on 16/3/2017.
 */

public class UserController {

    NottsParkDatabase npd;

    public UserController(Context app_context) {
        npd = new NottsParkDatabase(app_context);
    }

    public UserController(FragmentActivity fragmentActivity, Context app_context) {
        npd = new NottsParkDatabase(fragmentActivity, app_context);
    }

    public int addUser(User user) {
        if(getUser(user.getUserID())==null) {
            npd.addUser(user);
            return 1;
        }else
            return -1;
    }

    public User getUser(int id) {
        return npd.getUser(id);
    }

    public List<User> getAllUser() {
        return npd.getAllUser();
    }

    public List<User> getAllUsersByID(int id) {
        return npd.getAllUsersByID(id);
    }

    public int getCount() {
        return npd.getCount("User");
    }

    public int updateUser(User user) {
        if (user != null) {
            return npd.updateUser(user);
        }else{
            return -1;
        }
    }

    public int deleteUser(int id) {
        if (getUser(id) != null) {
            deleteUser(id);
            return 1;
        } else
            return -1;
    }
}
