package com.example.user.nottspark.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Dialogs.CustDialog;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;

public class LoginActivity extends AppCompatActivity {
    private static int userId;
    private static User user;
    private static ArrayList<User> allUserList;
    private static ArrayList<Leaver> allLeaverList;
    EditText txtUsername, txtPassword;
    Button btnLogin;
    SessionManager session;
    private String TAG = "LoginActivity";
    private Boolean exit = false;
    private CustDialog dialogFragment = new CustDialog();

    public static ArrayList<User> getAllUserList() {
        return allUserList;
    }

    public static void setAllUserList(ArrayList<User> allUserList2) {
        allUserList = allUserList2;
        MainActivity.setAllUserList(allUserList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        allUserList = getIntent().getParcelableArrayListExtra("allUserList");
        allLeaverList = getIntent().getParcelableArrayListExtra("allLeaverList");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        session = new SessionManager(getApplicationContext());
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                if (username.length() > 0 && password.length() > 0) {

                    userId = checkUserNamePassword(username, password);
                    if (userId != -1) {
                        session.createLoginSession(user);
                        returnMain();
                    } else {
                        displayMsg("Wrong username and password");
                    }
                } else {
                    displayMsg("Please enter username and password");
                }
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.btnRegister);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegistration();
            }
        });
    }

    public void displayMsg(String msg) {
        CustDialog alert = new CustDialog();
        alert.showAlertDialog(this, "Login Failed", msg);
    }

    public void userRegistration() {
        Intent registerIntent = new Intent(getBaseContext(), UserRegistrationActivity.class);
        this.startActivityForResult(registerIntent, 4);

//        Intent intent = new Intent(this, UserRegistrationActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    public int checkUserNamePassword(String username, String password) {
        for (User l : allUserList)
            if (l.getUserUsername().equals(username) && l.getUserPassword().equals(password)) {
                user = l;
                return l.getUserID();
            }
        return -1;
    }

    public void returnMain() {
        getIntent().putExtra("user", user);
        getIntent().putExtra("allLeaverList", allLeaverList);
        getIntent().putExtra("allUserList", allUserList);
        this.setResult(RESULT_OK, getIntent());
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 4 && resultCode == RESULT_OK) {
            user = data.getParcelableExtra("user");
            allUserList.add(user);
            txtUsername.setText(user.getUserUsername());
            txtPassword.setText(user.getUserPassword());
        }
    }
}

