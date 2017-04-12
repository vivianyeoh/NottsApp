package com.example.user.nottspark.View;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Dialogs.SuccessRegister;
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
        user = MainActivity.currentUser;
        setContentView(R.layout.activity_user_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
        selectSpinnerItemByValue(mCarMake, user.getCarMake());
        mCarModel.setText(user.getCarModel());
        mCarPlate.setText(user.getCarPlate());

        if (PasswordValidation.ValidatePassword("ADD YOUR PASSWORD HERE")) {
            // WHAT TO DO AFTER PASSWORD IS VALIDATED?
        }
    }

    public void successfulUpdate() {
        DialogFragment sr = new SuccessRegister();
        sr.show(getFragmentManager(), "Successful Register");

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}