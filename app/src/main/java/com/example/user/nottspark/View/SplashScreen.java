package com.example.user.nottspark.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.user.nottspark.Database.MaintainLeaverDBTable;
import com.example.user.nottspark.Database.MaintainUserDBTable;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;


public class SplashScreen extends Activity {

    MaintainLeaverDBTable ml;
    MaintainUserDBTable mu;
    ArrayList<Leaver> allLeaverList;
    ArrayList<User> allUserList;
    private Thread mdownloadData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Splash screen view
        setContentView(R.layout.activity_splash_screen);

        final SplashScreen sPlashScreen = this;

        mdownloadData = new Thread() {
            @Override
            public void run() {
                ml = new MaintainLeaverDBTable(getApplicationContext());
                allLeaverList = (ArrayList<Leaver>) ml.getLeaverList();

                mu = new MaintainUserDBTable(getApplicationContext());
                allUserList = (ArrayList<User>) mu.getUserList();
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
                launchNextActivity.setClass(sPlashScreen, MainActivity.class);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(launchNextActivity);
            }
        };
        mdownloadData.start();

    }


}