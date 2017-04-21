package com.example.user.nottspark.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.user.nottspark.Model.User;
import com.rey.material.widget.EditText;

import getresult.example.asus.nottspark.R;

public class UserProfileActivity extends AppCompatActivity {
    private static User user;
    private EditText profileName, profileUserName, profileEmail, profileContact, profileCarMake, profileCarModel, profileCarPlate, profileAccType;
    private Button btnEditProfile;

    public UserProfileActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        user = getIntent().getParcelableExtra("currentSecUser");

        if (user != null) {
            profileName = (EditText) findViewById(R.id.profileName);
            profileUserName = (EditText) findViewById(R.id.profileUserName);
            profileEmail = (EditText) findViewById(R.id.profileEmail);
            profileContact = (EditText) findViewById(R.id.profileContact);
            profileCarMake = (EditText) findViewById(R.id.profileCarMake);
            profileCarModel = (EditText) findViewById(R.id.profileCarModel);
            profileCarPlate = (EditText) findViewById(R.id.profileCarPlate);
            profileAccType = (EditText) findViewById(R.id.profileAccType);
            btnEditProfile = (Button) findViewById(R.id.btnEditProfile);

            profileName.setKeyListener(null);
            profileUserName.setKeyListener(null);
            profileEmail.setKeyListener(null);
            profileContact.setKeyListener(null);
            profileCarMake.setKeyListener(null);
            profileCarModel.setKeyListener(null);
            profileCarPlate.setKeyListener(null);
            profileAccType.setKeyListener(null);
            update(user);
        }
    }

    private void editProfile() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("currentSecUser", user);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivityForResult(intent, 6);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            user = data.getParcelableExtra("user");
            update(user);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        SessionManager session = new SessionManager(getBaseContext());
        User sessUser = session.getUserDetails();
        user = sessUser;
        update(sessUser);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void update(User user) {
        profileName.setText(user.getUserName());
        profileUserName.setText(user.getUserUsername());
        profileEmail.setText(user.getUserEmail());
        profileContact.setText(user.getUserContactNum());
        profileCarMake.setText(user.getCarMake());
        profileCarModel.setText(user.getCarModel());
        profileCarPlate.setText(user.getCarPlate());
        profileAccType.setText(user.getUserAccountType());
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                editProfile();
            }
        });
    }
}


