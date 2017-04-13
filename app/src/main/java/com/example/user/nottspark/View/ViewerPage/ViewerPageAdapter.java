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
    private ArrayList<User> userArrayList;
    private User curUser;

    public ViewerPageAdapter(FragmentManager fm, int NumOfTabs, ArrayList<Leaver> leaverArrayList, ArrayList<User> userArrayList, User curUser) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.leaverArrayList = leaverArrayList;
        this.userArrayList = userArrayList;
        this.curUser = curUser;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public void setLeaverArrayList(ArrayList<Leaver> leaverArrayList) {
        this.leaverArrayList = leaverArrayList;
    }

    public void setCurUser(User curUser) {
        this.curUser = curUser;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("currentSecUser", curUser);
        bundle.putParcelableArrayList("allLeaverList", leaverArrayList);
        bundle.putParcelableArrayList("allUserList", userArrayList);
        switch (position) {
            case 0:
                MapDisplay mapDisplay = new MapDisplay();
                mapDisplay.setArguments(bundle);
                return mapDisplay;
            case 1:
                ParkerFragment parkerFragment = new ParkerFragment();
                parkerFragment.setArguments(bundle);
                return parkerFragment;
            case 2:
                LeaverFragment leaverFragment = new LeaverFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("currentSecUser", curUser);
                leaverFragment.setArguments(bundle);
                return leaverFragment;
            case 3:
                UserProfileFragment userProfileFragment = new UserProfileFragment();
                userProfileFragment.setArguments(bundle);
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