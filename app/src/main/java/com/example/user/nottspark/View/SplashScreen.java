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
    /**
     * The thread to process splash screen events
     */
    private Thread mdownloadData;
    private int ttlUser;
    private int ttlLeaver;

    /**
     * Called when the activity is first created.
     */
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
                ttlLeaver = ml.getCount();
                allLeaverList = (ArrayList<Leaver>) ml.getLeaverList();
                mu = new MaintainUserDBTable(getApplicationContext());
                ttlUser = mu.getCount();
                allUserList = (ArrayList<User>) mu.getUserList();
                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                }// Run next activity which is your GameActivity
                Intent intent = new Intent();
                intent.setClass(sPlashScreen, LoginActivity.class); //Here You Can Replace MainActivity.class with your GameActivity

                startActivity(intent);
            }
        };
        mdownloadData.start();

    }


}