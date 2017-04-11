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

import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Dialogs.LoginDialog;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;

public class LoginActivity extends AppCompatActivity {
    final static LoginDialog alert = new LoginDialog();
    private static int userId;
    private static User user;
    EditText txtUsername, txtPassword;
    Button btnLogin;
    SessionManager session;
    private String TAG = "LoginActivity";
    private Boolean exit = false;
    private ArrayList<User> allUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        allUserList = getIntent().getParcelableArrayListExtra("allUserList");
        session = new SessionManager(getApplicationContext());
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                btnLogin.setEnabled(false);
                userLogin();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnLogin.setEnabled(true);
                    }
                }, 2000);
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

    public void userLogin() {

        final String username = txtUsername.getText().toString().trim();
        final String password = txtPassword.getText().toString().trim();
        if (username.length() > 0 && password.length() > 0) {

            userId = checkUserNamePassword(username, password);
            if (userId != -1) {
                session.createLoginSession(user);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            } else {
                alert.showAlertDialog(LoginActivity.this, "Login failed..", "Wrong username and password", false);
            }
        } else {
            alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
        }
    }

    public void userRegistration() {
        Intent intent = new Intent(this, UserRegistrationActivity.class);
        startActivity(intent);
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
}

