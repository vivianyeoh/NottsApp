package com.example.user.nottspark.Controller;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.example.user.nottspark.Database.NottsParkDatabase;
import com.example.user.nottspark.Model.Transaction;

import java.util.List;

/**
 * Created by user on 16/3/2017.
 */

public class TransactionController {

    NottsParkDatabase npd;

    public TransactionController(Context app_context) {
        npd = new NottsParkDatabase(app_context);
    }

    public TransactionController(FragmentActivity fragmentActivity, Context app_context) {
        npd = new NottsParkDatabase(fragmentActivity, app_context);
    }

    public int addTransaction(Transaction transaction) {
        if(getTransaction(transaction.getTransID())==null) {
            npd.addTransaction(transaction);
            return 1;
        }else
            return -1;
    }

    public Transaction getTransaction(int id) {
        return npd.getTransaction(id);
    }

    public List<Transaction> getAllTransaction() {
        return npd.getAllTransaction();
    }

    public List<Transaction> getAllTransactionsByID(int id) {
        return npd.getAllTransactionsByID(id);
    }

    public int getCount() {
        return npd.getCount("Transaction");
    }

    public int updateTransaction(Transaction transaction) {
        if (transaction != null) {
            return npd.updateTransaction(transaction);
        }else{
            return -1;
        }
    }

    public int deleteTransaction(int id) {
        if (getTransaction(id) != null) {
            deleteTransaction(id);
            return 1;
        } else
            return -1;
    }
}
