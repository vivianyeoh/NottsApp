package com.example.user.nottspark.View.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.user.nottspark.Controller.LeaverController;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Dialogs.LeaverAdded;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import getresult.example.asus.nottspark.R;

public class LeaverFragment extends Fragment {
    private static EditText leaveTime;
    private static EditText leaverDesc;
    private static Button timePickerButton;
    private static Button btnLeave;
    private static ArrayList<Leaver> leaverArrayList;
    private static User user;
    private FragmentActivity myContext;
    private Spinner zone_spinner;
    private ImageView zone_image;


    public LeaverFragment() {

    }


    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        leaverArrayList = getArguments().getParcelableArrayList("allleaverArrayList");
        user = getArguments().getParcelable("currentSecUser");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        String[] redZoneArray = new String[]{
                "ZONE B - Near Blue Building",
                "ZONE P - Near Civil Mixing Lab",
                "ZONE N1 - Next to Engineering Research Building",
                "ZONE N2 - Next to Engineering Research Building",
                "ZONE J1 - Behind Purple Building",
                "ZONE J2 - Around Nexus/Rawa area",
                "ZONE C - Outside SA Circle",
                "ZONE H - Near Yellow Building/SA Bus Stop",
                "ZONE S - Near Sport Complex",
                "ZONE T - Between Tioman and Langkawi Hall",
                "ZONE L - Near Pangkor Hall",
                "ZONE A - Between Pangkor and Kapas Hall",
                "ZONE K - Behind Kapas Hall",
                "ZONE R1 - Next to Redang Hall",
                "ZONE R2 - Behind Redang Hall",
                "ZONE M - Near Islamic Centre"};

        String[] yellowZoneArray = new String[]{
                "ZONE B1 - Between Trent and Blue Building",
                "ZONE B2 - Behind Blue Building",
                "ZONE B3 - Between Blue Building",
                "ZONE C - Near Red Building",
                "ZONE D - Near Purple Building",
                "ZONE D2- Between Purple Building and Civil Mixing Lab",
                "ZONE E1 - Between Purple and Orange Building",
                "ZONE E2 - Near Orange and Engineering Research Building",
                "ZONE N - Near Engineering Research Building",
                "ZONE F3 - Near F3 Building",
                "ZONE F4 - Near F4 Building"};

        zone_image = (ImageView) view.findViewById(R.id.zone_image);
        zone_spinner = (Spinner) view.findViewById(R.id.zone_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_row, redZoneArray);
        zone_spinner.setAdapter(adapter);
        zone_spinner.setPrompt("Parking Space Location");

        zone_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                int position = zone_spinner.getSelectedItemPosition();
                switch (position) {
                    case 0:
                        zone_image.setImageResource(R.drawable.red_area_2);
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        zone_image.setImageResource(R.drawable.red_area_1);
                        break;
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 13:
                    case 14:
                    case 15:
                        zone_image.setImageResource(R.drawable.red_area_3);
                        break;
                    case 11:
                    case 12:
                        zone_image.setImageResource(R.drawable.red_area_4);
                        break;
                    default:
                        zone_image.setImageResource(R.drawable.fullmap_redpermit);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                zone_image.setImageResource(R.drawable.red_area_1);
            }

        });

        leaveTime = (EditText) view.findViewById(R.id.leaveTime);
        leaverDesc = (EditText) view.findViewById(R.id.leaverDesc);
        leaveTime.setText(new SimpleDateFormat("hh:mm aa").format(new Date()));
        leaveTime.setKeyListener(null);

        timePickerButton = (Button) view.findViewById(R.id.btnTimepicker);
        timePickerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        btnLeave = (Button) view.findViewById(R.id.btnLeave);
        btnLeave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                uploadLeaverData();
                LeaverAdded dialogFragment = new LeaverAdded();
                dialogFragment.show(getActivity().getFragmentManager(), "1 Leaver Added");
            }
        });

        return view;
    }

    public void uploadLeaverData() {
        String location = zone_spinner.getSelectedItem().toString();
        String time = leaveTime.getText().toString();
        String leaveDesc = leaverDesc.getText().toString();
        LeaverController lc = new LeaverController(getContext());
        Leaver l = new Leaver(user.getUserID(), location, leaveDesc, time);
        lc.addLeaver(l);
    }


    public void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(myContext.getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            return new TimePickerDialog(getActivity(), this, hour, minute, false);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String ampm = "";
            if (hourOfDay > 12) {
                hourOfDay -= 12;
                ampm = "PM";
            } else if (hourOfDay == 0) {
                hourOfDay += 12;
                ampm = "AM";
            } else if (hourOfDay == 12)
                ampm = "PM";
            else
                ampm = "AM";
            leaveTime.setText(String.format("%02d:%02d %.2s", hourOfDay, minute, ampm));
        }

    }


}

