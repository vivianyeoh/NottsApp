package com.example.user.nottspark.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.user.nottspark.Database.MaintainLeaverDBTable;
import com.example.user.nottspark.Database.MaintainUserDBTable;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;

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

                Intent i = new Intent();
                Bundle b = new Bundle();
                i.putParcelableArrayListExtra("allLeaverList", allLeaverList);
                i.putParcelableArrayListExtra("allUserList", allUserList);
                i.putExtras(b);
                i.setClass(sPlashScreen, LoginActivity.class);
                startActivity(i);
            }
        };
        mdownloadData.start();

    }


}