package com.example.user.nottspark.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.ViewerPage.MainActivity;

import getresult.example.asus.nottspark.R;

public class LeaverListActivity extends AppCompatActivity {
    private static final int DATASET_COUNT = 6;
    private Leaver[] mDataset;
    private RecyclerView mRecyclerView;
    private LeaverListAdapter mAdapter;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = MainActivity.getUserinfo();
        initDataset();

        setContentView(R.layout.activity_leaver_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.scrollToPosition(((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition());
        mAdapter = new LeaverListAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initDataset() {
        mDataset = new Leaver[DATASET_COUNT];
        mDataset[0] = new Leaver(10001, user, "sl", "coming", true, true);
        mDataset[1] = new Leaver(10001, user, "", "coming", true, true);
        mDataset[2] = new Leaver(10001, user, "", "coming", true, true);
        mDataset[3] = new Leaver(10001, user, "", "coming", true, true);
        mDataset[4] = new Leaver(10001, user, "", "coming", true, true);
        mDataset[5] = new Leaver(10001, user, "", "coming", true, true);
    }
}
