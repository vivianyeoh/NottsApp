package com.example.user.nottspark.Controller;

import android.content.Context;

import com.example.user.nottspark.Database.MaintainTransactionDBTable;
import com.example.user.nottspark.Model.Transaction;

import java.util.List;

/**
 * Created by user on 16/3/2017.
 */

public class TransactionController {

    MaintainTransactionDBTable npd;

    public TransactionController(Context app_context) {
        npd = new MaintainTransactionDBTable(app_context);
    }

    public void addTransaction(Transaction transaction) {
        npd.addTransaction(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        npd.updateTransaction(transaction);
    }

    public Transaction getTransactionByID(int id) {
        return npd.getDownload1transaction(id);
    }

    public List<Transaction> getAllTransaction() {
        return npd.getTransactionList();
    }

    public int getCount() {
        return npd.getCount();
    }

    public void deleteTransaction(int id) {
        npd.deleteTransaction(id);
    }
}
