package com.example.user.nottspark.Controller;

import android.content.Context;

import com.example.user.nottspark.Database.MaintainLeaverDBTable;
import com.example.user.nottspark.Model.Leaver;

import java.util.List;

/**
 * Created by user on 16/3/2017.
 */

public class LeaverController {

    MaintainLeaverDBTable npd;

    public LeaverController(Context app_context) {
        npd = new MaintainLeaverDBTable(app_context);
    }

    public void addLeaver(Leaver leaver) {
        npd.addLeaver(leaver);
    }

    public void updateLeaver(Leaver leaver) {
        npd.updateLeaver(leaver);
    }

    public Leaver getLeaverByID(int id) {
        return npd.getDownload1leaver(id);
    }

    public List<Leaver> getAllLeaver() {
        return npd.getLeaverList();
    }

    public void updateLeaverStatus(final int id) {
        npd.updateLeaverStatus(id);
    }
    public int getCount() {
        return npd.getCount();
    }

    public void deleteLeaver(int id) {
        npd.deleteLeaver(id);
    }
}
