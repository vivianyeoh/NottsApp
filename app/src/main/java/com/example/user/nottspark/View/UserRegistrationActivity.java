package com.example.user.nottspark.View;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Dialogs.CustDialog;
import com.rey.material.widget.EditText;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;


public class UserRegistrationActivity extends AppCompatActivity {

    private static ArrayList<User> userArrayList;
    private EditText mUsernameField, mPasswordField, mPasswordVerifyField, mName, mContact, mEmail, mCarModel, mCarPlate;
    private Spinner mAccType, mCarMake;
    private Button btnAddUser, btnCancel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        userArrayList = new ArrayList<>();
        mUsernameField = (EditText) findViewById(R.id.username);
        mPasswordField = (EditText) findViewById(R.id.password);
        mPasswordVerifyField = (EditText) findViewById(R.id.passwordReconfirm);

        mName = (EditText) findViewById(R.id.accName);
        mContact = (EditText) findViewById(R.id.contactNum);
        mEmail = (EditText) findViewById(R.id.regEmail);
        mAccType = (Spinner) findViewById(R.id.accTypeReg);

        mCarMake = (Spinner) findViewById(R.id.carMakeReg);
        mCarModel = (EditText) findViewById(R.id.carModelReg);
        mCarPlate = (EditText) findViewById(R.id.carPlateReg);

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean allowPasswordAdd = false;
                boolean allowAdd = false;
                boolean hasEntered = true;

                if (!mUsernameField.getText().toString().trim().equals(""))
                    hasEntered = true;
                if (!mPasswordField.getText().toString().trim().equals("") && !mPasswordField.getText().toString().trim().equals("")) {
                    hasEntered = true;
                }
                if (!mPasswordVerifyField.getText().toString().trim().equals("") && !mPasswordVerifyField.getText().toString().trim().equals("")) {
                    hasEntered = true;
                }
                if (!mName.getText().toString().trim().equals(""))
                    hasEntered = true;
                if (!mContact.getText().toString().trim().equals(""))
                    hasEntered = true;
                if (!mEmail.getText().toString().trim().equals(""))
                    hasEntered = true;
                if (!(mAccType.getSelectedItem()).toString().trim().equals(""))
                    hasEntered = true;
                if (!(mCarMake.getSelectedItem()).toString().trim().equals(""))
                    hasEntered = true;
                if (!mCarModel.getText().toString().trim().equals(""))
                    hasEntered = true;
                if (!mCarPlate.getText().toString().trim().equals(""))
                    hasEntered = true;

                if (hasEntered) {
                    Thread mdownloadData = new Thread() {
                        @Override
                        public void run() {
                            UserController ul = new UserController(getApplicationContext());
                            userArrayList = (ArrayList<User>) ul.getAllUser();

                            try {
                                synchronized (this) {
                                    wait(2000);
                                }
                            } catch (InterruptedException ex) {
                            }

                        }
                    };
                    mdownloadData.start();
                    boolean hasSameUsername = false;
                    if (userArrayList.size() > 0) {
                        for (User u : userArrayList)
                            if (u.getUserUsername().equals(mUsernameField.getText().toString().trim())) {
                                hasSameUsername = true;
                                break;
                            }

                        if (!hasSameUsername) {
                            if (mPasswordField.getText().toString().equals(mPasswordVerifyField.getText().toString())) {
                                String check = PasswordValidation.ValidatePassword(mPasswordField.getText().toString());
                                if (check.equals("")) {
                                    allowAdd = true;
                                    allowPasswordAdd = true;
                                } else {
                                    failedUpdate(check);
                                }
                            } else {
                                failedUpdate("1st password and 2nd password are not matched!");
                            }
                        } else {
                            failedUpdate("Username already exist!");
                        }
                    }
                } else {
                    failedUpdate("Please fill in all the columns!");
                }

                if (allowAdd && allowPasswordAdd) {
                    User userAdd = new User(mUsernameField.getText().toString().trim(), mName.getText().toString().trim(), mContact.getText().toString().trim(),
                            mEmail.getText().toString().trim(), (mCarMake.getSelectedItem()).toString().trim(),
                            mCarModel.getText().toString().trim(), mCarPlate.getText().toString().trim().toUpperCase(),
                            (mAccType.getSelectedItem()).toString().trim(), mPasswordField.getText().toString().trim());

                    updateInDatabase(userAdd);
                    refreshActivities(userAdd);

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancelRegister();
            }
        });

    }

    public void failedUpdate(String msg) {
        CustDialog alert = new CustDialog();
        alert.showAlertDialog(this, "Edit Profile", msg);

    }

    public void updateInDatabase(final User u) {
        Thread mdownloadData = new Thread() {
            @Override
            public void run() {
                UserController uc = new UserController(getApplicationContext());
                uc.addUser(u);
                try {
                    synchronized (this) {
                        wait(1000);
                    }
                } catch (InterruptedException ex) {
                }
            }
        };
        mdownloadData.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void cancelRegister() {
        this.setResult(RESULT_CANCELED, getIntent());
        onBackPressed();
    }

    public void refreshActivities(User user) {
        getIntent().putExtra("user", user);
        this.setResult(RESULT_OK, getIntent());
        onBackPressed();
    }
}