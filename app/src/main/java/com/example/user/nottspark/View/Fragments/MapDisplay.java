package com.example.user.nottspark.View.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;

import java.util.ArrayList;

import getresult.example.asus.nottspark.R;
import uk.co.senab.photoview.PhotoViewAttacher;

public class MapDisplay extends Fragment {
    private static ArrayList<Leaver> leaverArrayList;
    private static User user;
    private PhotoViewAttacher mAttacher;

    public MapDisplay() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_display, container, false);
        ImageView mImageView = (ImageView) view.findViewById(R.id.imageView);

        user = getArguments().getParcelable("currentSecUser");
        Drawable bitmap = null;
        if (user.getUserAccountType().equals("Red Parking Permit")) {

            bitmap = ContextCompat.getDrawable(getActivity(), R.drawable.fullmap_redpermit);

        } else {
            bitmap = ContextCompat.getDrawable(getActivity(), R.drawable.fullmap_yellowpermit);

        }
        mImageView.setImageDrawable(bitmap);
        mAttacher = new PhotoViewAttacher(mImageView);
        return view;
    }

}
