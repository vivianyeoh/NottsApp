package com.example.user.nottspark.View.ViewerPage;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.LoginActivity;
import com.example.user.nottspark.View.SessionManager;
import com.example.user.nottspark.View.UserProfileActivity;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Leaver> allLeaverList;
    public static ArrayList<User> allUserList;
    public static User currentUser;
    private static ViewerPageAdapter adapter;
    SessionManager session;
    UserController lc;
    Bundle savedInstanceState;
    private Boolean exit = false;
    private String TAG = "MainActivity";

    public static void setAllLeaverList(ArrayList<Leaver> allLeaverList) {
        MainActivity.allLeaverList = allLeaverList;
    }

    public static void setAllUserList(ArrayList<User> allUserList) {
        MainActivity.allUserList = allUserList;
    }

    public static void setCurrentUser(User currentUser) {
        MainActivity.currentUser = currentUser;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedInstanceState = this.savedInstanceState;
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        lc = new UserController(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayUseLogoEnabled(false);


        Intent i = getIntent();//get intent from splash activity
        allLeaverList = i.getParcelableArrayListExtra("allLeaverList");
        allUserList = i.getParcelableArrayListExtra("allUserList");

        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            currentUser = session.getUserDetails();
        } else {
            Intent loginIntent = new Intent(getBaseContext(), LoginActivity.class);
            loginIntent.putParcelableArrayListExtra("allUserList", allUserList);
            loginIntent.putParcelableArrayListExtra("allLeaverList", allLeaverList);
            this.startActivityForResult(loginIntent, 2);
        }
        setTabViewerPage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setCurUser(currentUser);
        adapter.setLeaverArrayList(allLeaverList);
        adapter.setUserArrayList(allUserList);
        adapter.notifyDataSetChanged();
    }

    public void setTabViewerPage() {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Map"));
        tabLayout.addTab(tabLayout.newTab().setText("Parking Space"));
        tabLayout.addTab(tabLayout.newTab().setText("Leaving"));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        adapter = new ViewerPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), allLeaverList, allUserList, currentUser);
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

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
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
        if (id == R.id.menu_profile) {
            Intent i = new Intent(this, UserProfileActivity.class);
            i.putExtra("currentSecUser", currentUser);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 2) {
            currentUser = data.getParcelableExtra("user");
            allLeaverList = data.getParcelableArrayListExtra("allLeaverList");
            allUserList = data.getParcelableArrayListExtra("allUserList");
        }
    }
}