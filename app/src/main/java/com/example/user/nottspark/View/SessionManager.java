package com.example.user.nottspark.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.user.nottspark.Model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class SessionManager {
    public static final String KEY_USER_ID = "Userid";
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

    public void createLoginSession(String userid) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER_ID, userid);
        editor.commit();
    }

    public void checkLogin(ArrayList<User> userList) {
        if (!this.isLoggedIn()) {
            Intent i = new Intent(context, LoginActivity.class);
            i.putParcelableArrayListExtra("allUserList", userList);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> info = new HashMap<String, String>();
        info.put(KEY_USER_ID, pref.getString(KEY_USER_ID, ""));
        return info;
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
