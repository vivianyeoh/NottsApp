package com.example.user.nottspark.View.ViewerPage;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.nottspark.Model.Car;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Dialogs.LogOutDialog;

import getresult.example.asus.nottspark.R;

public class MainActivity extends AppCompatActivity {
    public static Car car1 = new Car(6001, "Proton", "Saga", "CAD 2035");//testing please delete
    public static User userinfo = new User(20002, "admin2012", "Admin", "0124547896", "admin@ne.com", car1, "05/01/2017 3:00pm", "Student", "root");//testing please delete

    public static User getUserinfo() {
        return userinfo;
    }

    public static void setUserinfo(User userinfo) {
        userinfo = userinfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Map"));
        tabLayout.addTab(tabLayout.newTab().setText("Parking Space"));
        tabLayout.addTab(tabLayout.newTab().setText("Leaving"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ViewerPageAdapter adapter = new ViewerPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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
            DialogFragment dialog = new LogOutDialog();
            dialog.show(getSupportFragmentManager(), "Log Out");
        }

        return super.onOptionsItemSelected(item);
    }
}