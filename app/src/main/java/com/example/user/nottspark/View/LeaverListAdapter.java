package com.example.user.nottspark.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.nottspark.Controller.LeaverController;
import com.example.user.nottspark.Model.Leaver;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;


/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class LeaverListAdapter extends RecyclerView.Adapter<LeaverListAdapter.ViewHolder> {
    private static final String TAG = "LeaverListAdapter";
    private static LeaverController lc;
    private ArrayList<Leaver> mDataSet;
    private Context c;

    public LeaverListAdapter(ArrayList<Leaver> mDataSet, Context c) {
        this.c = c;
        lc = new LeaverController(c);
        this.mDataSet = mDataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_in_parker, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.getTvDesc().setText("" + mDataSet.get(position).getLeaverDesc());
        viewHolder.getTvTime().setText("" + mDataSet.get(position).getLeavingTime());
        viewHolder.getTvStatus().setText("" + mDataSet.get(position).getPairingStatus());
        Button btn = viewHolder.getBtnEditStatus();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lc.updateLeaverStatus(mDataSet.get(position).getLeaverID());
                Toast.makeText(c, "Space at " + mDataSet.get(position).getLeavingTime() + " is marked taken!", Toast.LENGTH_SHORT).show();
                removeItem(position);
            }
        });
    }

    // Remove an Announcement from our Dataset
    public void removeItem(int position) {
        mDataSet.remove(position);
        notifyItemRemoved(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    // Returns number of elements in the mDataSet List
    public int getItemCount() {
        if (mDataSet == null) {
            return -1;
        } else {
            return mDataSet.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvDesc;
        private final TextView tvTime;
        private final TextView tvStatus;
        private final Button btnEditStatus;


        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            tvDesc = (TextView) v.findViewById(R.id.tvDesc);
            tvTime = (TextView) v.findViewById(R.id.tvTime);
            tvStatus = (TextView) v.findViewById(R.id.tvStatus);
            btnEditStatus = (Button) v.findViewById(R.id.btnEditStatus);
        }

        public TextView getTvDesc() {
            return tvDesc;
        }

        public TextView getTvTime() {
            return tvTime;
        }

        public TextView getTvStatus() {
            return tvStatus;
        }

        public Button getBtnEditStatus() {
            return btnEditStatus;
        }
    }
}