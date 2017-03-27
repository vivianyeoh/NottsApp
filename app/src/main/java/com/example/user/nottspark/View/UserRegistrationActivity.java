package com.example.user.nottspark.View;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.Car;
import com.example.user.nottspark.Model.User;

import java.util.Objects;

import getresult.example.asus.nottspark.R;


public class UserRegistrationActivity extends AppCompatActivity {

    private EditText mUsernameField, mPasswordField, mPasswordVerifyField, mName, mContact, mEmail, mCarModel, mCarPlate;
    private Spinner mAccType, mCarMake;
    private UserController db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new UserController(getApplicationContext());

        mUsernameField = (EditText) findViewById(R.id.username);
        mPasswordField = (EditText) findViewById(R.id.password);
        mPasswordVerifyField = (EditText) findViewById(R.id.passwordReconfirm);
        mAccType = (Spinner) findViewById(R.id.accTypeReg);

        mName = (EditText) findViewById(R.id.accName);
        mContact = (EditText) findViewById(R.id.contactNum);
        mEmail = (EditText) findViewById(R.id.regEmail);

        mCarMake = (Spinner) findViewById(R.id.carMakeReg);
        mCarModel = (EditText) findViewById(R.id.carModelReg);
        mCarPlate = (EditText) findViewById(R.id.carPlateReg);

        Button mSignUp = (Button) findViewById(R.id.btnAddUser);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_username = mUsernameField.getText().toString();
                String new_pw = mPasswordField.getText().toString();
                String new_pw_verify = mPasswordVerifyField.getText().toString();
                String new_accType = mAccType.getSelectedItem().toString();

                String new_name = mName.getText().toString();
                String new_contact = mContact.getText().toString();
                String new_email = mEmail.getText().toString();

                String new_car_make = mCarMake.getSelectedItem().toString();
                String new_car_model = mCarModel.getText().toString();
                String new_car_plate = mCarPlate.getText().toString();
                if (PasswordValidation.ValidatePassword(new_pw)) {
                    if (Objects.equals(new_pw, new_pw_verify)) {
                        db.addUser(new User(1, new_username, new_name, new_contact, new_email, new Car(1, new_car_make, new_car_model, new_car_plate), new_accType, new_pw));
                    }
                }
            }
        });
    }
}