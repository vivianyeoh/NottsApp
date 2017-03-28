package com.example.user.nottspark.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.user.nottspark.Controller.CarController;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import getresult.example.asus.nottspark.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button mUsernameSignInButton = (Button) findViewById(R.id.btnSignIn);
        mUsernameSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();

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
        CarController cc = new CarController(getApplicationContext());
        Log.wtf("cc.getCount()", "cc.getCount(): " + cc.getCount());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
//
}

