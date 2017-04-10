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

import com.example.user.nottspark.View.Dialogs.AlertDialogManager;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import getresult.example.asus.nottspark.R;

public class LoginActivity extends AppCompatActivity {

    // Email, password edittext
    EditText txtUsername, txtPassword;
    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();
    // login button
    Button btnLogin;
    // Session Manager Class
    SessionManager session;
    private String TAG = "LoginActivity";
    private Boolean exit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button mRegisterButton = (Button) findViewById(R.id.btnRegister);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                userRegistration();
            }
        });

        session = new SessionManager(getApplicationContext());
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                userLogin();
            }
        });
    }

    public void userLogin() {
        String username = "viusername";
        String password = "vipass";

        if (username.trim().length() > 0 && password.trim().length() > 0) {
            if (username.equals("viusername") && password.equals("vipass")) {

                session.createLoginSession("Android Hive", "anroidhive@gmail.com");
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            } else {
                alert.showAlertDialog(LoginActivity.this, "Login failed..", "Username/Password is incorrect", false);
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
}

