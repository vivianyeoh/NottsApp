package com.example.user.nottspark.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.nottspark.Model.Leaver;

import getresult.example.asus.nottspark.R;


/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.ViewHolder> {
    private static final String TAG = "LeaverListAdapter";

    private Leaver[] mDataSet;

    public HistoryListAdapter(Leaver[] dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_in_history, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        viewHolder.gettvPlaceName().setText(mDataSet[position].getUserID().getCar().getCarPlate());
        viewHolder.gettvPlaceTime().setText(mDataSet[position].getLeavingTime());
        viewHolder.gettvPlaceDate().setText(mDataSet[position].getLeavingDate());
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPlaceName;
        private final TextView tvPlaceTime;
        private final TextView tvPlaceDate;
        private final ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            tvPlaceName = (TextView) v.findViewById(R.id.placeName);
            tvPlaceTime = (TextView) v.findViewById(R.id.placeTime);
            tvPlaceDate = (TextView) v.findViewById(R.id.placeDate);
            imageView = (ImageView) v.findViewById(R.id.placeImage);
        }

        public TextView gettvPlaceName() {
            return tvPlaceName;
        }

        public TextView gettvPlaceTime() {
            return tvPlaceTime;
        }

        public TextView gettvPlaceDate() {
            return tvPlaceDate;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}