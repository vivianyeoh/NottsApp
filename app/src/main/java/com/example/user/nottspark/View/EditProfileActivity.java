package com.example.user.nottspark.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Dialogs.CustDialog;
import com.example.user.nottspark.View.ViewerPage.MainActivity;
import com.rey.material.widget.EditText;

import getresult.example.asus.nottspark.R;


public class EditProfileActivity extends AppCompatActivity {

    private EditText mUsernameField, mPasswordField, mPasswordVerifyField, mName, mContact, mEmail, mCarModel, mCarPlate;
    private Spinner mAccType, mCarMake;
    private User user;
    private Button btnAddUser, btnCancel;

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

        mName = (EditText) findViewById(R.id.accName);
        mContact = (EditText) findViewById(R.id.contactNum);
        mEmail = (EditText) findViewById(R.id.regEmail);
        mAccType = (Spinner) findViewById(R.id.accTypeReg);

        mCarMake = (Spinner) findViewById(R.id.carMakeReg);
        mCarModel = (EditText) findViewById(R.id.carModelReg);
        mCarPlate = (EditText) findViewById(R.id.carPlateReg);

        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        mUsernameField.setText(user.getUserUsername());
        mPasswordField.setText("********");
        mPasswordVerifyField.setText("********");
        mName.setText(user.getUserName());
        mContact.setText(user.getUserContactNum());
        mEmail.setText(user.getUserEmail());
        selectSpinnerItemByValue(mAccType, user.getUserAccountType());
        selectSpinnerItemByValue(mCarMake, user.getCarMake());
        mCarModel.setText(user.getCarModel());
        mCarPlate.setText(user.getCarPlate());

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                boolean hasChanged = false;
                boolean passwordChanged = false;
                boolean allowUpdate = false;
                boolean allowPasswordUpdate = false;
                if (!mUsernameField.getText().toString().trim().equals(user.getUserUsername()))
                    hasChanged = true;
                if (!mPasswordField.getText().toString().trim().equals("********") && !mPasswordField.getText().toString().trim().equals("")) {
                    hasChanged = true;
                    passwordChanged = true;
                }
                if (!mPasswordVerifyField.getText().toString().trim().equals("********") && !mPasswordVerifyField.getText().toString().trim().equals("")) {
                    hasChanged = true;
                    passwordChanged = true;
                }
                if (!mName.getText().toString().trim().equals(user.getUserName()))
                    hasChanged = true;
                if (!mContact.getText().toString().trim().equals(user.getUserContactNum()))
                    hasChanged = true;
                if (!mEmail.getText().toString().trim().equals(user.getUserEmail()))
                    hasChanged = true;
                if (!(mAccType.getSelectedItem()).toString().trim().equals(user.getUserAccountType()))
                    hasChanged = true;
                if (!(mCarMake.getSelectedItem()).toString().trim().equals(user.getCarMake()))
                    hasChanged = true;
                if (!mCarModel.getText().toString().trim().equals(user.getCarModel()))
                    hasChanged = true;
                if (!mCarPlate.getText().toString().trim().equals(user.getCarPlate()))
                    hasChanged = true;

                if (hasChanged) {
                    if (passwordChanged) {
                        if (mPasswordField.getText().toString().equals(mPasswordVerifyField.getText().toString())) {
                            String check = PasswordValidation.ValidatePassword(mPasswordField.getText().toString());

                            if (check.equals("")) {
                                allowUpdate = true;
                                allowPasswordUpdate = true;
                            } else {
                                failedUpdate(check);
                            }
                        } else {
                            failedUpdate("1st password and 2nd password are not matched!");

                        }
                    } else {
                        allowUpdate = true;
                    }
                } else {
                    failedUpdate("No Data has changed!");
                }
                if (allowUpdate) {
                    User userUpdate = null;
                    if (allowPasswordUpdate) {
                        userUpdate = new User(user.getUserID(), mUsernameField.getText().toString().trim(), mName.getText().toString().trim(), mContact.getText().toString().trim(),
                                mEmail.getText().toString().trim(), (mCarMake.getSelectedItem()).toString().trim(),
                                mCarModel.getText().toString().trim(), mCarPlate.getText().toString().trim().toUpperCase(), user.getRegisterDate(),
                                (mAccType.getSelectedItem()).toString().trim(), mPasswordField.getText().toString().trim());
                    } else {
                        userUpdate = new User(user.getUserID(), mUsernameField.getText().toString().trim(), mName.getText().toString().trim(), mContact.getText().toString().trim(),
                                mEmail.getText().toString().trim(), (mCarMake.getSelectedItem()).toString().trim(),
                                mCarModel.getText().toString().trim(), mCarPlate.getText().toString().trim(), user.getRegisterDate(),
                                (mAccType.getSelectedItem()).toString().trim(), user.getUserPassword());
                    }
                    successfulUpdate();
                    updateInDatabase(userUpdate);
                    refreshActivities(userUpdate);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                onBackPressed();
            }
        });

    }

    public void successfulUpdate() {
        CustDialog alert = new CustDialog();
        alert.showAlertDialog(this, "Edit Profile", "Data is updated");
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
                uc.updateUser(u);
                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                }
            }
        };
        mdownloadData.start();

    }

    public void refreshActivities(User u) {

    }

}