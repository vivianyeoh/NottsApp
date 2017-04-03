package com.example.user.nottspark.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.user.nottspark.Controller.LeaverController;
import com.example.user.nottspark.Controller.TransactionController;
import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.Transaction;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.List;

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
        LeaverController lc = new LeaverController(getApplicationContext());
        lc.getAllLeaver();
        TransactionController tc = new TransactionController(getApplicationContext());
        List<Transaction> t = tc.getAllTransaction();
        UserController uc = new UserController(getApplicationContext());
        uc.getAllUser();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

