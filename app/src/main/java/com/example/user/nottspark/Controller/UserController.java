package com.example.user.nottspark.Controller;

import android.content.Context;

import com.example.user.nottspark.Database.MaintainUserDBTable;
import com.example.user.nottspark.Model.User;

import java.util.List;

/**
 * Created by user on 16/3/2017.
 */

public class UserController {

    MaintainUserDBTable npd;

    public UserController(Context app_context) {
        npd = new MaintainUserDBTable(app_context);
    }

    public void addUser(User user) {
        npd.addUser(user);
    }

    public void updateUser(User user) {
        npd.updateUser(user);
    }

    public User getUserByID(int id) {
        return npd.getDownload1user(id);
    }

    public List<User> getAllUser() {
        return npd.getUserList();
    }

    public int getCount() {
        return npd.getCount();
    }

    public void deleteUser(int id) {
        npd.deleteUser(id);
    }

    public boolean CheckPasswordUsername(final String username, final String password) {
        return npd.CheckPasswordUsername(username, password);
    }
}
