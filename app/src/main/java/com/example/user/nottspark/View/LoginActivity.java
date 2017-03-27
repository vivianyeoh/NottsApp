package com.example.user.nottspark.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import getresult.example.asus.nottspark.R;

public class LoginActivity extends AppCompatActivity {

    private EditText mUsername,mPassword;
    private UserController db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new UserController(getApplicationContext());

        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        Button mUsernameSignInButton = (Button) findViewById(R.id.btnSignIn);
        mUsernameSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                List<User> users = db.getAllUser();
                for (User user: users){
                    if ((Objects.equals(mUsername.getText().toString(),user.getUserUsername())) && (Objects.equals(mPassword.getText().toString(),user.getUserPassword())))
                    attemptLogin();
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

    public void userRegistration() {
        Intent intent = new Intent(this, UserRegistrationActivity.class);
        startActivity(intent);
    }

    public void attemptLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
//
}

