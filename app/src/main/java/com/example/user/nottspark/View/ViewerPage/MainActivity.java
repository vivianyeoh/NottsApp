package com.example.user.nottspark.View.ViewerPage;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import getresult.example.asus.nottspark.R;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Leaver> allLeaverList;
    public static ArrayList<User> allUserList;
    public static User currentUser;
    SessionManager session;
    UserController lc;
    private Boolean exit = false;
    private String TAG = "MainActivity";
    private Thread mdownloadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        lc = new UserController(getApplicationContext());

        Intent i = getIntent();
        allLeaverList = i.getParcelableArrayListExtra("allLeaverList");
        allUserList = i.getParcelableArrayListExtra("allUserList");

        session = new SessionManager(getApplicationContext());
        session.checkLogin(allUserList);
        HashMap<String, String> info = session.getUserDetails();

        if (session.isLoggedIn() && info.get(SessionManager.KEY_USER_ID) != null) {
            String stringUserId = info.get(SessionManager.KEY_USER_ID);
        int currentUserId = 0;
        boolean isInt = false;

        try {
            if (stringUserId != null) {
                currentUserId = Integer.parseInt(stringUserId);
                isInt = true;
            }
        } catch (NumberFormatException ex) {
            Log.wtf(TAG, "info.get(SessionManager.KEY_USER_ID): " + info.get(SessionManager.KEY_USER_ID));
        }
        if (isInt)
            if (currentUserId > 0) {
                final int KEY_USER_ID = Integer.parseInt(info.get(SessionManager.KEY_USER_ID));
                mdownloadData = new Thread() {
                    @Override
                    public void run() {
                        currentUser = lc.getUserByID(KEY_USER_ID);
                        try {
                            synchronized (this) {
                                wait(2000);
                            }
                        } catch (InterruptedException ex) {
                        }
                    }
                };
                mdownloadData.start();
            }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Map"));
        tabLayout.addTab(tabLayout.newTab().setText("Parking Space"));
        tabLayout.addTab(tabLayout.newTab().setText("Leaving"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            final ViewerPageAdapter adapter = new ViewerPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), allLeaverList, currentUser);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                adapter.getItem(position).onResume();
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        }
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_log_out) {
            session.logoutUser();
        }
        return super.onOptionsItemSelected(item);
    }
}