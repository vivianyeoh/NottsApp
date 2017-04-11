package com.example.user.nottspark.View.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.user.nottspark.Controller.LeaverController;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import getresult.example.asus.nottspark.R;


public class ParkerFragment extends Fragment {
    private static ArrayList<Leaver> leaverArrayList;
    private static User user;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, ArrayList<Leaver>> listDataChild;
    private String TAG = "ParkerFragment";
    private ImageButton refresh;
    private String[] redZoneArray = new String[]{
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
    String[] redZoneNum = new String[redZoneArray.length];
    private String[] yellowZoneArray = new String[]{
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
    private int[] numOfRedLeaverZone = new int[redZoneArray.length];
    private ArrayList<Leaver>[] redleaverArrayListByZone = new ArrayList[redZoneArray.length];

    public ParkerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parker, container, false);
        Intent bdlIntent = ((Activity) getContext()).getIntent();
        Bundle extras = bdlIntent.getExtras();
        leaverArrayList = extras.getParcelableArrayList("allLeaverList");
        user = extras.getParcelable("currentSecUser");

        prepareListData();
        expListView = (ExpandableListView) view.findViewById(R.id.parkingspacenum);
        listAdapter = new ExpandableListAdapter(view.getContext(), listDataHeader, listDataChild, redZoneNum);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        refresh = (ImageButton) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                refresh.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Thread mdownloadData = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    leaverArrayList = (ArrayList<Leaver>) (new LeaverController(getContext())).getAllLeaver();
                                    synchronized (this) {
                                        wait(2000);
                                    }
                                    if (leaverArrayList.size() > 0)
                                        Toast.makeText(getContext(), "Number of space is refreshed", Toast.LENGTH_SHORT).show();

                                } catch (Exception ex) {
                                    Log.wtf(TAG, "Error in ParkerFragment: " + ex.getLocalizedMessage());
                                }
                            }
                        };
                        mdownloadData.start();
                        refresh.setEnabled(true);
                    }
                }, 5000);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<Leaver>>();

        if (leaverArrayList.size() > 0) {
            for (int i = 0; i < numOfRedLeaverZone.length; i++) {
                listDataHeader.add(redZoneArray[i]);
                redleaverArrayListByZone[i] = new ArrayList<Leaver>();
            }

            for (int i = 0; i < leaverArrayList.size(); i++) {
                if (leaverArrayList.get(i).getPairingStatus() == 0) {
                    switch (leaverArrayList.get(i).getLocation()) {
                        case "ZONE B - Near Blue Building":
                            numOfRedLeaverZone[0]++;
                            redleaverArrayListByZone[0].add(leaverArrayList.get(i));
                            break;
                        case "ZONE P - Near Civil Mixing Lab":
                            numOfRedLeaverZone[1]++;
                            redleaverArrayListByZone[1].add(leaverArrayList.get(i));
                            break;
                        case "ZONE N1 - Next to Engineering Research Building":
                            numOfRedLeaverZone[2]++;
                            redleaverArrayListByZone[2].add(leaverArrayList.get(i));
                            break;
                        case "ZONE N2 - Next to Engineering Research Building":
                            numOfRedLeaverZone[3]++;
                            redleaverArrayListByZone[3].add(leaverArrayList.get(i));
                            break;
                        case "ZONE J1 - Behind Purple Building":
                            numOfRedLeaverZone[4]++;
                            redleaverArrayListByZone[4].add(leaverArrayList.get(i));
                            break;
                        case "ZONE J2 - Around Nexus/Rawa area":
                            numOfRedLeaverZone[5]++;
                            redleaverArrayListByZone[5].add(leaverArrayList.get(i));
                            break;
                        case "ZONE C - Outside SA Circle":
                            numOfRedLeaverZone[6]++;
                            redleaverArrayListByZone[6].add(leaverArrayList.get(i));
                            break;
                        case "ZONE H - Near Yellow Building/SA Bus Stop":
                            numOfRedLeaverZone[7]++;
                            redleaverArrayListByZone[7].add(leaverArrayList.get(i));
                            break;
                        case "ZONE S - Near Sport Complex":
                            numOfRedLeaverZone[8]++;
                            redleaverArrayListByZone[8].add(leaverArrayList.get(i));

                            break;
                        case "ZONE T - Between Tioman and Langkawi Hall":
                            numOfRedLeaverZone[9]++;
                            redleaverArrayListByZone[9].add(leaverArrayList.get(i));
                            break;
                        case "ZONE L - Near Pangkor Hall":
                            numOfRedLeaverZone[10]++;
                            redleaverArrayListByZone[10].add(leaverArrayList.get(i));
                            break;
                        case "ZONE A - Between Pangkor and Kapas Hall":
                            numOfRedLeaverZone[11]++;
                            redleaverArrayListByZone[11].add(leaverArrayList.get(i));
                            break;
                        case "ZONE K - Behind Kapas Hall":
                            numOfRedLeaverZone[12]++;
                            redleaverArrayListByZone[12].add(leaverArrayList.get(i));
                            break;
                        case "ZONE R1 - Next to Redang Hall":
                            numOfRedLeaverZone[13]++;
                            redleaverArrayListByZone[13].add(leaverArrayList.get(i));
                            break;
                        case "ZONE R2 - Behind Redang Hall":
                            numOfRedLeaverZone[14]++;
                            redleaverArrayListByZone[14].add(leaverArrayList.get(i));
                            break;
                        case "ZONE M - Near Islamic Centre":
                            numOfRedLeaverZone[15]++;
                            redleaverArrayListByZone[15].add(leaverArrayList.get(i));
                            break;
                    }
                }
            }

            for (int i = 0; i < redZoneArray.length; i++)
                listDataChild.put(listDataHeader.get(i), redleaverArrayListByZone[i]); // Header, Child data

            for (int i = 0; i < redZoneNum.length; i++)
                redZoneNum[i] = numOfRedLeaverZone[i] + "";
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}

