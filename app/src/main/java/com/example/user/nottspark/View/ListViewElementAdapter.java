package com.example.user.nottspark.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import getresult.example.asus.nottspark.R;

/**
 * Created by user on 20/3/2017.
 */

public class ListViewElementAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] zones;
    private final String[] numberOfZones;

    public ListViewElementAdapter(Context context, String[] zones, String[] numberOfZones) {
        super(context, R.layout.row_in_parkerfrag, zones);
        this.context = context;
        this.zones = zones;
        this.numberOfZones = numberOfZones;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_in_parkerfrag, parent, false);
        TextView spaceLocation = (TextView) rowView.findViewById(R.id.spaceLocation);
        TextView spaceNum = (TextView) rowView.findViewById(R.id.spaceNum);

        spaceLocation.setText(zones[position]);
        spaceNum.setText(numberOfZones[position]);

        return rowView;
    }
}
