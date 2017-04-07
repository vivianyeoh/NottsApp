package com.example.user.nottspark.View;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Dialogs.FailedLogin;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;

public class LoginActivity extends AppCompatActivity {

    private String TAG = "LoginActivity";
    private ArrayList<Leaver> allLeaverList;
    private ArrayList<User> allUserList;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent i = getIntent();
        allLeaverList = i.getParcelableArrayListExtra("allLeaverList");
        allUserList = i.getParcelableArrayListExtra("allUserList");
        if (allUserList.size() > 0) {
            user = allUserList.get(0);

            Log.wtf(TAG, "getLeaver" + allLeaverList.get(0).toString());
            Log.wtf(TAG, "allUserList" + allUserList.get(0).toString());
            Button mUsernameSignInButton = (Button) findViewById(R.id.btnSignIn);
            mUsernameSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    successLogin();
                }
            });
        } else {
            Log.wtf(TAG, "No User Data Retrieved");
        }


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

    public void successLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putParcelableArrayListExtra("allLeaverList", allLeaverList);
        intent.putExtra("mainUser", user);
        startActivity(intent);
    }

    public void failedLogin() {
        DialogFragment dialog = new FailedLogin();
        dialog.show(getFragmentManager(), "Log Out");
    }
}

