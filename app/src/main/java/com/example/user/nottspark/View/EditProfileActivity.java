package com.example.user.nottspark.View;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.PasswordValidation;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import getresult.example.asus.nottspark.R;


public class EditProfileActivity extends AppCompatActivity {

    private EditText mUsernameField, mPasswordField, mPasswordVerifyField, mName, mContact, mEmail, mCarModel, mCarPlate;
    private Spinner mAccType, mCarMake;
    private User user;

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItem(position).equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = MainActivity.getUserinfo();
        setContentView(R.layout.activity_user_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

        Button mSignUp = (Button) findViewById(R.id.btnRegister);
        mUsernameField.setText(user.getUserUsername());
        mPasswordField.setText("");
        mPasswordVerifyField.setText("");
        selectSpinnerItemByValue(mAccType, user.getUserAccountType());
        mName.setText(user.getUserName());
        mContact.setText(user.getUserContactNum());
        mEmail.setText(user.getUserEmail());
        selectSpinnerItemByValue(mCarMake, user.getCar().getCarMake());
        mCarModel.setText(user.getCar().getCarModel());
        mCarPlate.setText(user.getCar().getCarPlate());

        if(PasswordValidation.ValidatePassword("ADD YOUR PASSWORD HERE")){
            // WHAT TO DO AFTER PASSWORD IS VALIDATED?
        }
    }

}