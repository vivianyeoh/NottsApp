package com.example.user.nottspark.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.user.nottspark.Controller.LeaverController;
import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;


public class SplashScreen extends Activity {

    LeaverController ml;
    UserController mu;
    ArrayList<Leaver> allLeaverList;
    ArrayList<User> allUserList;
    private Thread mdownloadData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final SplashScreen splashScreen = this;

        mdownloadData = new Thread() {
            @Override
            public void run() {
                ml = new LeaverController(getApplicationContext());
                allLeaverList = (ArrayList<Leaver>) ml.getAllLeaver();

                mu = new UserController(getApplicationContext());
                allUserList = (ArrayList<User>) mu.getAllUser();
                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                }

                Intent launchNextActivity;
                launchNextActivity = new Intent();
                Bundle b = new Bundle();
                launchNextActivity.putParcelableArrayListExtra("allLeaverList", allLeaverList);
                launchNextActivity.putParcelableArrayListExtra("allUserList", allUserList);
                launchNextActivity.putExtras(b);
                launchNextActivity.setClass(splashScreen, MainActivity.class);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(launchNextActivity);
            }
        };
        mdownloadData.start();

    }


}