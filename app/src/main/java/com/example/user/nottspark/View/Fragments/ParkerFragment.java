package com.example.user.nottspark.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.nottspark.Controller.LeaverController;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.View.LeaverListActivity;
import com.example.user.nottspark.View.ListViewElementAdapter;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;


public class ParkerFragment extends Fragment {
    private static ArrayList<Leaver> leaverList;
    private String TAG = "ParkerFragment";
    private ListView listView;
    private ListViewElementAdapter adapter;
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
    String[] yellowZoneNum = new String[yellowZoneArray.length];
    private int[] numOfRedLeaverZone = new int[redZoneArray.length];
    private int[] numOfYellowLeaverZone = new int[redZoneArray.length];
    private ArrayList<Leaver>[] redLeaverListByZone = new ArrayList[redZoneArray.length];


    public ParkerFragment() {
        leaverList = MainActivity.allLeaverList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parker, container, false);
        listView = (ListView) view.findViewById(R.id.parkingspacenum);
        refreshList();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), LeaverListActivity.class);
                intent.putParcelableArrayListExtra("numOfRedLeaverZone", redLeaverListByZone[position]);
                String title = redZoneArray[position];
                intent.putExtra("TitleOfArray", title);
                getActivity().startActivity(intent);
            }
        });

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
                                    leaverList = (ArrayList<Leaver>) (new LeaverController(getContext())).getAllLeaver();
                                    synchronized (this) {
                                        wait(2000);
                                    }
                                    if (leaverList.size() > 0)
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
                refreshList();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    public void refreshList() {
        leaverList = MainActivity.allLeaverList;
        if (leaverList.size() > 0) {
            for (int i = 0; i < numOfRedLeaverZone.length; i++) {
                numOfRedLeaverZone[i] = 0;
                redLeaverListByZone[i] = new ArrayList<Leaver>();
            }
            for (int i = 0; i < numOfYellowLeaverZone.length; i++) {
                numOfYellowLeaverZone[i] = 0;
            }

            for (int i = 0; i < leaverList.size(); i++) {
                if (leaverList.get(i).getPairingStatus() == 0) {
                    switch (leaverList.get(i).getLocation()) {
                        case "ZONE B - Near Blue Building":
                            numOfRedLeaverZone[0]++;
                            redLeaverListByZone[0].add(leaverList.get(i));
                            break;
                        case "ZONE P - Near Civil Mixing Lab":
                            numOfRedLeaverZone[1]++;
                            redLeaverListByZone[1].add(leaverList.get(i));
                            break;
                        case "ZONE N1 - Next to Engineering Research Building":
                            numOfRedLeaverZone[2]++;
                            redLeaverListByZone[2].add(leaverList.get(i));
                            break;
                        case "ZONE N2 - Next to Engineering Research Building":
                            numOfRedLeaverZone[3]++;
                            redLeaverListByZone[3].add(leaverList.get(i));
                            break;
                        case "ZONE J1 - Behind Purple Building":
                            numOfRedLeaverZone[4]++;
                            redLeaverListByZone[4].add(leaverList.get(i));
                            break;
                        case "ZONE J2 - Around Nexus/Rawa area":
                            numOfRedLeaverZone[5]++;
                            redLeaverListByZone[5].add(leaverList.get(i));
                            break;
                        case "ZONE C - Outside SA Circle":
                            numOfRedLeaverZone[6]++;
                            redLeaverListByZone[6].add(leaverList.get(i));
                            break;
                        case "ZONE H - Near Yellow Building/SA Bus Stop":
                            numOfRedLeaverZone[7]++;
                            redLeaverListByZone[7].add(leaverList.get(i));
                            break;
                        case "ZONE S - Near Sport Complex":
                            numOfRedLeaverZone[8]++;
                            redLeaverListByZone[8].add(leaverList.get(i));

                            break;
                        case "ZONE T - Between Tioman and Langkawi Hall":
                            numOfRedLeaverZone[9]++;
                            redLeaverListByZone[9].add(leaverList.get(i));
                            break;
                        case "ZONE L - Near Pangkor Hall":
                            numOfRedLeaverZone[10]++;
                            redLeaverListByZone[10].add(leaverList.get(i));
                            break;
                        case "ZONE A - Between Pangkor and Kapas Hall":
                            numOfRedLeaverZone[11]++;
                            redLeaverListByZone[11].add(leaverList.get(i));
                            break;
                        case "ZONE K - Behind Kapas Hall":
                            numOfRedLeaverZone[12]++;
                            redLeaverListByZone[12].add(leaverList.get(i));
                            break;
                        case "ZONE R1 - Next to Redang Hall":
                            numOfRedLeaverZone[13]++;
                            redLeaverListByZone[13].add(leaverList.get(i));
                            break;
                        case "ZONE R2 - Behind Redang Hall":
                            numOfRedLeaverZone[14]++;
                            redLeaverListByZone[14].add(leaverList.get(i));
                            break;
                        case "ZONE M - Near Islamic Centre":
                            numOfRedLeaverZone[15]++;
                            redLeaverListByZone[15].add(leaverList.get(i));
                            break;
                    }
                }
            }

            for (int i = 0; i < redZoneNum.length; i++)
                redZoneNum[i] = numOfRedLeaverZone[i] + "";

            for (int i = 0; i < leaverList.size(); i++) {
                if (leaverList.get(i).getPairingStatus() == 0) {

                    switch (leaverList.get(i).getLocation()) {
                        case "ZONE B1 - Between Trent and Blue Building":
                            numOfYellowLeaverZone[0]++;
                            break;
                        case "ZONE B2 - Behind Blue Building":
                            numOfYellowLeaverZone[1]++;
                            break;
                        case "ZONE B3 - Between Blue Building":
                            numOfYellowLeaverZone[2]++;
                            break;
                        case "ZONE C - Near Red Building":
                            numOfYellowLeaverZone[3]++;
                            break;
                        case "ZONE D - Near Purple Building":
                            numOfYellowLeaverZone[4]++;
                            break;
                        case "ZONE D2- Between Purple Building and Civil Mixing Lab":
                            numOfYellowLeaverZone[5]++;
                            break;
                        case "ZONE E1 - Between Purple and Orange Building":
                            numOfYellowLeaverZone[6]++;
                            break;
                        case "ZONE E2 - Near Orange and Engineering Research Building":
                            numOfYellowLeaverZone[7]++;
                            break;
                        case "ZONE N - Near Engineering Research Building":
                            numOfYellowLeaverZone[8]++;
                            break;
                        case "ZONE F3 - Near F3 Building":
                            numOfYellowLeaverZone[9]++;
                            break;
                        case "ZONE F4 - Near F4 Building":
                            numOfYellowLeaverZone[10]++;
                            break;
                    }
                }
            }

            for (int i = 0; i < yellowZoneNum.length; i++)
                yellowZoneNum[i] = numOfYellowLeaverZone[i] + "";

            adapter = new ListViewElementAdapter(getActivity(), redZoneArray, redZoneNum);
            listView.setAdapter(adapter);
        }
    }
}

