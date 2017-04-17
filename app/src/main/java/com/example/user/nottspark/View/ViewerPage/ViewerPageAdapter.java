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
import com.example.user.nottspark.View.UserProfileActivity;

import java.util.ArrayList;

public class ViewerPageAdapter extends FragmentStatePagerAdapter {

    private int mNumOfTabs;
    private ArrayList<Leaver> leaverArrayList;
    private ArrayList<User> userArrayList;
    private User curUser;
    private ParkerFragment parkerFragment;
    private LeaverFragment leaverFragment;
    private UserProfileActivity userProfileFragment;

    public ViewerPageAdapter(FragmentManager fm, int NumOfTabs, ArrayList<Leaver> leaverArrayList, ArrayList<User> userArrayList, User curUser) {
        super(fm);

        this.mNumOfTabs = NumOfTabs;
        this.leaverArrayList = leaverArrayList;
        this.userArrayList = userArrayList;
        this.curUser = curUser;


    }

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof ParkerFragment) {
            ParkerFragment parkerFrag = (ParkerFragment) object;
            if (parkerFrag != null) {
                parkerFrag.update(curUser);
            }
        }
        if (object instanceof LeaverFragment) {
            LeaverFragment leaverFrag = (LeaverFragment) object;
            if (leaverFrag != null) {
                leaverFrag.update(curUser);
            }
        }
        if (object instanceof UserProfileActivity) {
            UserProfileActivity userProfileFrag = (UserProfileActivity) object;
            if (userProfileFrag != null) {
                userProfileFrag.update(curUser);
            }
        }
        return super.getItemPosition(object);
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
        parkerFragment = new ParkerFragment();
        leaverFragment = new LeaverFragment();
        userProfileFragment = new UserProfileActivity();
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
                parkerFragment.setArguments(bundle);
                return parkerFragment;

            case 2:
                leaverFragment.setArguments(bundle);
                return leaverFragment;
            default:
                return null;
        }
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}