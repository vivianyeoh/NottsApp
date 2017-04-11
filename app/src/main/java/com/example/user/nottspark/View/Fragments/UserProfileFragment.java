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
    private EditText profileName, profileEmail, profileContact, profileCarMake, profileCarModel, profileCarPlate;
    private Button editProfile;

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
        View view = inflater.inflate(R.layout.user_profile_fragment, container, false);
        user = getArguments().getParcelable("currentSecUser");

        if (user != null) {
            profileName = (EditText) view.findViewById(R.id.profileName);
            profileEmail = (EditText) view.findViewById(R.id.profileEmail);
            profileContact = (EditText) view.findViewById(R.id.profileContact);
            profileCarMake = (EditText) view.findViewById(R.id.profileCarMake);
            profileCarModel = (EditText) view.findViewById(R.id.profileCarModel);
            profileCarPlate = (EditText) view.findViewById(R.id.profileCarPlate);

            profileName.setText(user.getUserName());
            profileEmail.setText(user.getUserEmail());
            profileContact.setText(user.getUserContactNum());
            profileCarMake.setText(user.getCarMake());
            profileCarModel.setText(user.getCarModel());
            profileCarPlate.setText(user.getCarPlate());

            editProfile = (Button) view.findViewById(R.id.editProfile);
            editProfile.setOnClickListener(new View.OnClickListener() {
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
        getActivity().startActivity(intent);
    }
}


