package com.example.user.nottspark.View.Fragments;

import android.content.Context;
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
import com.example.user.nottspark.Controller.UserController;
import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.ExpandableListAdapter;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;


public class ParkerFragment extends Fragment {
    private static ArrayList<User> userArrayList;
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
        userArrayList = getArguments().getParcelableArrayList("allUserList");
        user = getArguments().getParcelable("currentSecUser");
        expListView = (ExpandableListView) view.findViewById(R.id.parkingspacenum);

        update(user, userArrayList, leaverArrayList);
        refresh = (ImageButton) view.findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                refresh.setEnabled(false);
                try {
                    update(user);
                    Toast.makeText(getContext(), "Number of space is refreshed", Toast.LENGTH_SHORT).show();
                    refresh.setEnabled(true);

                } catch (Exception ex) {
                    Log.wtf(TAG, "Error in ParkerFragment: " + ex.getLocalizedMessage());
                }

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

    public void update(User user, final ArrayList<User> userArrayList, final ArrayList<Leaver> leaverArrayList) {
        listAdapter = new ExpandableListAdapter(getContext(), leaverArrayList, user.getUserAccountType(), userArrayList);
        expListView.setAdapter(listAdapter);
    }

    public void update(final User user1) {
        Thread mdownloadData = new Thread() {
            @Override
            public void run() {
                LeaverController ml = new LeaverController(getContext());
                leaverArrayList = (ArrayList<Leaver>) ml.getAllLeaver();
                UserController ul = new UserController(getContext());
                userArrayList = (ArrayList<User>) ul.getAllUser();

                try {
                    synchronized (this) {
                        wait(2000);
                    }
                } catch (InterruptedException ex) {
                }
                if (leaverArrayList.size() > 0) {
                    listAdapter.setLeaverUserList(leaverArrayList, userArrayList);

                    MainActivity.setAllLeaverList(leaverArrayList);
                    MainActivity.setAllUserList(userArrayList);

                    //Only the original thread that created a view hierarchy can touch its views.
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            listAdapter.notifyDataSetChanged();
                            expListView.setAdapter(listAdapter);
                        }
                    }, 500);
                }
            }
        };
        mdownloadData.start();
    }
}

