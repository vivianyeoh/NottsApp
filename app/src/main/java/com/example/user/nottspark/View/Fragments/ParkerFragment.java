package com.example.user.nottspark.View.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.user.nottspark.View.LeaverListActivity;
import com.example.user.nottspark.View.ListViewElementAdapter;

import java.util.Random;

import getresult.example.asus.nottspark.R;

public class ParkerFragment extends Fragment {

    private ListView listView;

    public ParkerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parker, container, false);
        listView = (ListView) view.findViewById(R.id.parkingspacenum);

        String[] values = new String[23];
        for (int i = 0; i < 23; i++) {
            values[i] = "ZONE " + (i + 1);
        }

        String[] numbers = new String[23];
        for (int i = 0; i < 23; i++) {
            numbers[i] = new Random().nextInt(i + 1) + "";
        }

        ListViewElementAdapter adapter = new ListViewElementAdapter(getActivity(), values, numbers);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), LeaverListActivity.class);
                getActivity().startActivity(intent);
            }
        });
        ImageButton button = (ImageButton) view.findViewById(R.id.refresh);
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }


}

