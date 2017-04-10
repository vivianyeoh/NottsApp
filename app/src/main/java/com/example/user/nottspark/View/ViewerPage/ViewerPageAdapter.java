package com.example.user.nottspark.View.ViewerPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.View.Fragments.LeaverFragment;
import com.example.user.nottspark.View.Fragments.MapDisplay;
import com.example.user.nottspark.View.Fragments.ParkerFragment;
import com.example.user.nottspark.View.Fragments.UserHistoryFragment;
import com.example.user.nottspark.View.Fragments.UserProfileFragment;

import java.util.ArrayList;

public class ViewerPageAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    ArrayList<Leaver> leaverArrayList;

    public ViewerPageAdapter(FragmentManager fm, int NumOfTabs, ArrayList<Leaver> leaverArrayList) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.leaverArrayList = leaverArrayList;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MapDisplay mapDisplay = new MapDisplay();
                return mapDisplay;
            case 1:
                ParkerFragment parkerFragment = new ParkerFragment();
                addFrag(parkerFragment);
                return parkerFragment;
            case 2:
                LeaverFragment leaverFragment = new LeaverFragment();
                return leaverFragment;
            case 3:
                UserHistoryFragment userHistoryFragment = new UserHistoryFragment();
                return userHistoryFragment;
            case 4:
                UserProfileFragment userProfileFragment = new UserProfileFragment();
                return userProfileFragment;
            default:
                return null;
        }
    }

    public void addFrag(Fragment fragment) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("allLeaverList", leaverArrayList);
        Fragment fragLea = fragment;
        fragLea.setArguments(bundle);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}