package com.example.user.nottspark.View.ViewerPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.nottspark.Model.Leaver;
import com.example.user.nottspark.Model.User;
import com.example.user.nottspark.View.Fragments.LeaverFragment;
import com.example.user.nottspark.View.Fragments.MapDisplay;
import com.example.user.nottspark.View.Fragments.ParkerFragment;
import com.example.user.nottspark.View.Fragments.UserProfileFragment;

import java.util.ArrayList;

public class ViewerPageAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;
    private ArrayList<Leaver> leaverArrayList;
    private User curUser;

    public ViewerPageAdapter(FragmentManager fm, int NumOfTabs, ArrayList<Leaver> leaverArrayList, User curUser) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.leaverArrayList = leaverArrayList;
        this.curUser = curUser;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MapDisplay mapDisplay = new MapDisplay();
                return mapDisplay;
            case 1:
                ParkerFragment parkerFragment = new ParkerFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("allLeaverList", leaverArrayList);
                bundle.putParcelable("currentSecUser", curUser);
                parkerFragment.setArguments(bundle);
                return parkerFragment;
            case 2:
                LeaverFragment leaverFragment = new LeaverFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelableArrayList("allLeaverList", leaverArrayList);
                bundle2.putParcelable("currentSecUser", curUser);
                leaverFragment.setArguments(bundle2);
                return leaverFragment;
            case 3:
                UserProfileFragment userProfileFragment = new UserProfileFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putParcelableArrayList("allLeaverList", leaverArrayList);
                bundle3.putParcelable("currentSecUser", curUser);
                userProfileFragment.setArguments(bundle3);
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