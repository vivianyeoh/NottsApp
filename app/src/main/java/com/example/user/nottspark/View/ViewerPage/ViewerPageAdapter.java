package com.example.user.nottspark.View.ViewerPage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.nottspark.View.Fragments.LeaverFragment;
import com.example.user.nottspark.View.Fragments.MapDisplay;
import com.example.user.nottspark.View.Fragments.ParkerFragment;
import com.example.user.nottspark.View.Fragments.UserHistoryFragment;
import com.example.user.nottspark.View.Fragments.UserProfileFragment;

public class ViewerPageAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;

    public ViewerPageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MapDisplay mapDisplay = new MapDisplay();
                return mapDisplay;
            case 1:
                ParkerFragment parkerFragment = new ParkerFragment();
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

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}