package com.example.user.nottspark.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.View.Dialogs.LoginDialog;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import getresult.example.asus.nottspark.R;

public class LoginActivity extends AppCompatActivity {
    UserController lc;
    EditText txtUsername, txtPassword;
    LoginDialog alert = new LoginDialog();
    Button btnLogin;
    SessionManager session;
    private String TAG = "LoginActivity";
    private Boolean exit = false;
    private Thread mdownloadId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lc = new UserController(getApplicationContext());

        session = new SessionManager(getApplicationContext());
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                btnLogin.setEnabled(false);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Thread mLoginBtn = new Thread() {
                            @Override
                            public void run() {
                                userLogin();
                                try {
                                    synchronized (this) {
                                        wait(2000);
                                    }
                                } catch (Exception ex) {
                                    Log.wtf(TAG, "Error in Login Button: " + ex.getLocalizedMessage());
                                }
                            }
                        };
                        mLoginBtn.start();
                        btnLogin.setEnabled(true);
                    }
                }, 5000);
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
            mdownloadId = new Thread() {
                @Override
                public void run() {
                    int id = lc.checkPasswordUsername(username, password);
                    Log.wtf(TAG, "id: " + id);
                    try {
                        synchronized (this) {
                            wait(2000);
                        }
                    } catch (InterruptedException ex) {
                    }
                    if (id != 0 && id != -1) {
                        session.createLoginSession(id + "");
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            };
            mdownloadId.start();

        }
        alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
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

