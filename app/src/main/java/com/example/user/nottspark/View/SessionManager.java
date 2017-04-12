package com.example.user.nottspark.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.user.nottspark.Model.User;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SessionManager {
    public static final String KEY_USER = "User";
    private static final String PREF_NAME = "NottsPark";
    private static final String IS_LOGIN = "IsLoggedIn";
    SharedPreferences pref;
    Editor editor;
    Context context;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(User user) {
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER, userJson);
        editor.commit();
    }

    public void checkLogin(ArrayList<User> userList) {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.putParcelableArrayListExtra("allUserList", userList);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            i.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
            ((Activity) context).startActivityForResult(i, 2);
        }
    }


    public User getUserDetails() {
        Gson gson = new Gson();
        String json = pref.getString(KEY_USER, "");
        return gson.fromJson(json, User.class);
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, SplashScreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(IS_LOGIN, false);
    }
}
