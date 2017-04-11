package com.example.user.nottspark.View;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.nottspark.Controller.LeaverController;
import com.example.user.nottspark.Model.Leaver;

import java.util.ArrayList;
import java.util.HashMap;

import getresult.example.asus.nottspark.R;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private static final String TAG = "ExpandableListAdapter";
    private static Context context;
    private final String[] RED_ZONE_ARRAY = new String[]{
            "ZONE B - Near Blue Building",
            "ZONE P - Near Civil Mixing Lab",
            "ZONE N1 - Next to Engineering Research Building",
            "ZONE N2 - Next to Engineering Research Building",
            "ZONE J1 - Behind Purple Building",
            "ZONE J2 - Around Nexus/Rawa area",
            "ZONE C - Outside SA Circle",
            "ZONE H - Near Yellow Building/SA Bus Stop",
            "ZONE S - Near Sport Complex",
            "ZONE T - Between Tioman and Langkawi Hall",
            "ZONE L - Near Pangkor Hall",
            "ZONE A - Between Pangkor and Kapas Hall",
            "ZONE K - Behind Kapas Hall",
            "ZONE R1 - Next to Redang Hall",
            "ZONE R2 - Behind Redang Hall",
            "ZONE M - Near Islamic Centre"};
    private final String[] YELLOW_ZONE_ARRAY = new String[]{
            "ZONE B1 - Between Trent and Blue Building",
            "ZONE B2 - Behind Blue Building",
            "ZONE B3 - Between Blue Building",
            "ZONE C - Near Red Building",
            "ZONE D - Near Purple Building",
            "ZONE D2- Between Purple Building and Civil Mixing Lab",
            "ZONE E1 - Between Purple and Orange Building",
            "ZONE E2 - Near Orange and Engineering Research Building",
            "ZONE N - Near Engineering Research Building",
            "ZONE F3 - Near F3 Building",
            "ZONE F4 - Near F4 Building"};
    private int[] numOfRedLeaverZone;//ttl number of spaces per zone
    private ArrayList<Leaver>[] redleaverArrayListByZone;//arraylist of leavers per zone
    private ArrayList<Leaver> leaverList;//from database
    private HashMap<String, ArrayList<Leaver>> listDataChild = new HashMap<String, ArrayList<Leaver>>();//hash map to put in listview

    public ExpandableListAdapter(Context context, ArrayList<Leaver> leaverList) {
        ExpandableListAdapter.context = context;
        prepareListData(leaverList);

    }

    private void prepareListData(ArrayList<Leaver> leaverArrayList) {
        numOfRedLeaverZone = new int[RED_ZONE_ARRAY.length];
        redleaverArrayListByZone = new ArrayList[RED_ZONE_ARRAY.length];

        if (leaverArrayList.size() > 0) {
            for (int i = 0; i < numOfRedLeaverZone.length; i++) {
                redleaverArrayListByZone[i] = new ArrayList<Leaver>();
            }


            for (int i = 0; i < leaverArrayList.size(); i++) {
                if (leaverArrayList.get(i).getPairingStatus() == 0) {
                    switch (leaverArrayList.get(i).getLocation()) {
                        case "ZONE B - Near Blue Building":
                            numOfRedLeaverZone[0]++;
                            redleaverArrayListByZone[0].add(leaverArrayList.get(i));
                            break;
                        case "ZONE P - Near Civil Mixing Lab":
                            numOfRedLeaverZone[1]++;
                            redleaverArrayListByZone[1].add(leaverArrayList.get(i));
                            break;
                        case "ZONE N1 - Next to Engineering Research Building":
                            numOfRedLeaverZone[2]++;
                            redleaverArrayListByZone[2].add(leaverArrayList.get(i));
                            break;
                        case "ZONE N2 - Next to Engineering Research Building":
                            numOfRedLeaverZone[3]++;
                            redleaverArrayListByZone[3].add(leaverArrayList.get(i));
                            break;
                        case "ZONE J1 - Behind Purple Building":
                            numOfRedLeaverZone[4]++;
                            redleaverArrayListByZone[4].add(leaverArrayList.get(i));
                            break;
                        case "ZONE J2 - Around Nexus/Rawa area":
                            numOfRedLeaverZone[5]++;
                            redleaverArrayListByZone[5].add(leaverArrayList.get(i));
                            break;
                        case "ZONE C - Outside SA Circle":
                            numOfRedLeaverZone[6]++;
                            redleaverArrayListByZone[6].add(leaverArrayList.get(i));
                            break;
                        case "ZONE H - Near Yellow Building/SA Bus Stop":
                            numOfRedLeaverZone[7]++;
                            redleaverArrayListByZone[7].add(leaverArrayList.get(i));
                            break;
                        case "ZONE S - Near Sport Complex":
                            numOfRedLeaverZone[8]++;
                            redleaverArrayListByZone[8].add(leaverArrayList.get(i));

                            break;
                        case "ZONE T - Between Tioman and Langkawi Hall":
                            numOfRedLeaverZone[9]++;
                            redleaverArrayListByZone[9].add(leaverArrayList.get(i));
                            break;
                        case "ZONE L - Near Pangkor Hall":
                            numOfRedLeaverZone[10]++;
                            redleaverArrayListByZone[10].add(leaverArrayList.get(i));
                            break;
                        case "ZONE A - Between Pangkor and Kapas Hall":
                            numOfRedLeaverZone[11]++;
                            redleaverArrayListByZone[11].add(leaverArrayList.get(i));
                            break;
                        case "ZONE K - Behind Kapas Hall":
                            numOfRedLeaverZone[12]++;
                            redleaverArrayListByZone[12].add(leaverArrayList.get(i));
                            break;
                        case "ZONE R1 - Next to Redang Hall":
                            numOfRedLeaverZone[13]++;
                            redleaverArrayListByZone[13].add(leaverArrayList.get(i));
                            break;
                        case "ZONE R2 - Behind Redang Hall":
                            numOfRedLeaverZone[14]++;
                            redleaverArrayListByZone[14].add(leaverArrayList.get(i));
                            break;
                        case "ZONE M - Near Islamic Centre":
                            numOfRedLeaverZone[15]++;
                            redleaverArrayListByZone[15].add(leaverArrayList.get(i));
                            break;
                    }
                }
            }
            for (int i = 0; i < RED_ZONE_ARRAY.length; i++)
                listDataChild.put(RED_ZONE_ARRAY[i], redleaverArrayListByZone[i]);

        }


    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.RED_ZONE_ARRAY[groupPosition]).get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        final Leaver childText = (Leaver) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_in_parker, null);
        }
        final TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
        final TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
        final TextView tvStatus = (TextView) convertView.findViewById(R.id.tvStatus);
        Button btnEditStatus = (Button) convertView.findViewById(R.id.btnEditStatus);
        tvDesc.setText(childText.getLeaverDesc());
        tvTime.setText(childText.getLeavingTime());
        tvStatus.setText(childText.getPairingStatus() + "");
        btnEditStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                tvStatus.setText("1");
                childText.setPairingStatus(1);
                Thread mdownloadData = new Thread() {
                    @Override
                    public void run() {
                        try {
                            LeaverController lc = new LeaverController(context);
                            lc.updateLeaverStatus(childText.getLeaverID());

                        } catch (Exception e) {
                            Log.wtf(TAG, e.getLocalizedMessage());
                        }
                    }
                };
                mdownloadData.start();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listDataChild.get(this.RED_ZONE_ARRAY[groupPosition]).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.RED_ZONE_ARRAY[groupPosition];
    }

    public String getTtlSpace(int groupPosition) {
        return this.numOfRedLeaverZone[groupPosition] + "";
    }

    @Override
    public int getGroupCount() {
        return this.RED_ZONE_ARRAY.length;
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
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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