package com.example.user.nottspark.View.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.EditProfileActivity;
import com.rey.material.widget.EditText;

import getresult.example.asus.nottspark.R;

public class UserProfileFragment extends Fragment {
    private static User user;
    private EditText profileName, profileUserName, profileEmail, profileContact, profileCarMake, profileCarModel, profileCarPlate, profileAccType;
    private Button btnEditProfile;

    public UserProfileFragment() {

    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        user = getArguments().getParcelable("currentSecUser");


        if (user != null) {
            profileName = (EditText) view.findViewById(R.id.profileName);
            profileUserName = (EditText) view.findViewById(R.id.profileUserName);
            profileEmail = (EditText) view.findViewById(R.id.profileEmail);
            profileContact = (EditText) view.findViewById(R.id.profileContact);
            profileCarMake = (EditText) view.findViewById(R.id.profileCarMake);
            profileCarModel = (EditText) view.findViewById(R.id.profileCarModel);
            profileCarPlate = (EditText) view.findViewById(R.id.profileCarPlate);
            profileAccType = (EditText) view.findViewById(R.id.profileAccType);
            profileName.setKeyListener(null);
            profileUserName.setKeyListener(null);
            profileEmail.setKeyListener(null);
            profileContact.setKeyListener(null);
            profileCarMake.setKeyListener(null);
            profileCarModel.setKeyListener(null);
            profileCarPlate.setKeyListener(null);
            profileAccType.setKeyListener(null);
            profileName.setText(user.getUserName());
            profileUserName.setText(user.getUserUsername());
            profileEmail.setText(user.getUserEmail());
            profileContact.setText(user.getUserContactNum());
            profileCarMake.setText(user.getCarMake());
            profileCarModel.setText(user.getCarModel());
            profileCarPlate.setText(user.getCarPlate());
            profileAccType.setText(user.getUserAccountType());
            btnEditProfile = (Button) view.findViewById(R.id.btnEditProfile);
            btnEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editProfile();
                }
            });
        }
        return view;
    }

    private void editProfile() {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        getActivity().startActivity(intent);
    }
}


