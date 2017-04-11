package com.example.user.nottspark.View;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.user.nottspark.Model.Leaver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import getresult.example.asus.nottspark.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<Leaver>> listDataChild;
    private String[] redZoneNum;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, ArrayList<Leaver>> listChildData, String[] redZoneNum) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
        this.redZoneNum = redZoneNum;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final Leaver childText = (Leaver) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_in_parker, null);
        }
        TextView txtListChild = (TextView) convertView.findViewById(R.id.tvDesc);
        txtListChild.setText(childText.getLeavingTime());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    public String getTtlSpace(int groupPosition) {
        return this.redZoneNum[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        String numOfParking = getTtlSpace(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView tvZoneString = (TextView) convertView.findViewById(R.id.zoneString);
        tvZoneString.setTypeface(null, Typeface.BOLD);
        tvZoneString.setText(headerTitle);
        TextView tvNumOfParking = (TextView) convertView.findViewById(R.id.numOfParking);
        tvNumOfParking.setTypeface(null, Typeface.BOLD);
        tvNumOfParking.setText(numOfParking);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}