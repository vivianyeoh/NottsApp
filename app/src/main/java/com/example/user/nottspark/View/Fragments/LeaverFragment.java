package com.example.user.nottspark.View.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import getresult.example.asus.nottspark.R;

public class LeaverFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private User user;
    private EditText new_desc;
    private Spinner zone_spinner;
    private FragmentActivity myContext;
    private ImageView zone_image;

    public LeaverFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = MainActivity.getUserinfo();

    }

    @Override
    public void onAttach(Activity activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaver, container, false);
//        final NottsParkDatabase db = new NottsParkDatabase(null);
        zone_image = (ImageView) view.findViewById(R.id.zone_image);
        zone_spinner = (Spinner) view.findViewById(R.id.zone_spinner);
        zone_spinner.setOnItemSelectedListener(this);
        new_desc = (EditText) view.findViewById(R.id.leaverDescription);

        Button leaveButton = (Button) view.findViewById(R.id.btnLeave);
        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String temp_building = new_building.getText().toString();
//                String temp_vehicle = zone_spinner.getSelectedItem().toString();
//                String temp_desc = new_desc.getText().toString();
////                db.addLeaver(new Leaver(1, LoginActivity.current_user, temp_building, temp_vehicle, temp_desc, "Leaving"));
            }
        });
        return view;
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        int position = zone_spinner.getSelectedItemPosition();
        switch (position) {
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                zone_image.setImageResource(R.drawable.area1);
                break;
            case 0:
            case 1:
            case 2:
            case 3:
                zone_image.setImageResource(R.drawable.area2);
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
                zone_image.setImageResource(R.drawable.area3);
                break;
            case 20:
            case 22:
                zone_image.setImageResource(R.drawable.area4);
                break;
            default:
                zone_image.setImageResource(R.drawable.sitelayout);
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        zone_image.setImageResource(R.drawable.area2);
    }

}

