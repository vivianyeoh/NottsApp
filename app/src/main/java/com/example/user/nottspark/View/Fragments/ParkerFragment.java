package com.example.user.nottspark.View.Fragments;

import android.content.Context;
import android.os.Bundle;
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

import getresult.example.asus.nottspark.R;


public class ParkerFragment extends Fragment {
    private static ArrayList<Leaver> leaverArrayList;
    private static User user;
    private static ExpandableListAdapter listAdapter;
    private static ExpandableListView expListView;
    private String TAG = "ParkerFragment";
    private ImageButton refresh;


    public ParkerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_parker, container, false);
        leaverArrayList = getArguments().getParcelableArrayList("allLeaverList");
        user = getArguments().getParcelable("currentSecUser");
        expListView = (ExpandableListView) view.findViewById(R.id.parkingspacenum);
        listAdapter = new ExpandableListAdapter(view.getContext(), leaverArrayList);
        expListView.setAdapter(listAdapter);


        refresh = (ImageButton) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                refresh.setEnabled(false);
                try {
                    Thread mdownloadData = new Thread() {
                        @Override
                        public void run() {
                            LeaverController ml = new LeaverController(getContext());
                            leaverArrayList = (ArrayList<Leaver>) ml.getAllLeaver();

                            try {
                                synchronized (this) {
                                    wait(2000);
                                }
                            } catch (InterruptedException ex) {
                            }
                            if (leaverArrayList.size() > 0) {
                                listAdapter = new ExpandableListAdapter(view.getContext(), leaverArrayList);
                                expListView.setAdapter(listAdapter);
                                Toast.makeText(getContext(), "Number of space is refreshed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    };
                    mdownloadData.start();


                } catch (Exception ex) {
                    Log.wtf(TAG, "Error in ParkerFragment: " + ex.getLocalizedMessage());
                }
                refresh.setEnabled(true);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
}

