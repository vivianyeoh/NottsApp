package com.example.user.nottspark.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.nottspark.Model.Leaver;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;

public class LeaverListActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private LeaverListAdapter mAdapter;
    private ArrayList<Leaver> mDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaver_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                newString = null;
                mDataSet = null;

            } else {
                newString = extras.getString("TitleOfArray");
                mDataSet = extras.getParcelableArrayList("numOfRedLeaverZone");

            }
        } else {
            newString = null;
            mDataSet = null;

        }
        setTitle(newString);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.scrollToPosition(((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition());
        mAdapter = new LeaverListAdapter(mDataSet, getApplication());
        mRecyclerView.setAdapter(mAdapter);

    }

}
