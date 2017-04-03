package com.example.user.nottspark.View;

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
public class LeaverListAdapter extends RecyclerView.Adapter<LeaverListAdapter.ViewHolder> {
    private static final String TAG = "LeaverListAdapter";

    private Leaver[] mDataSet;

    public LeaverListAdapter(Leaver[] dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_in_parker, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        viewHolder.gettvPlaceName().setText("Leaver: " + mDataSet[position].getLocation());
        viewHolder.gettvLeaverName().setText("Location: " + mDataSet[position].getUserID());
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvPlaceName;
        private final ImageView imageView;
        private final TextView tvLeaverName;

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
            tvLeaverName = (TextView) v.findViewById(R.id.leaverName);
            imageView = (ImageView) v.findViewById(R.id.placeImage);
        }

        public TextView gettvPlaceName() {
            return tvPlaceName;
        }

        public TextView gettvLeaverName() {
            return tvLeaverName;
        }

        public ImageView getImageView() {
            return imageView;
        }
    }
}