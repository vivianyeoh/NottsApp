package com.example.user.nottspark.Database;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.nottspark.Model.Transaction;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaintainTransactionDBTable {
    private static final String TAG = "MaintainTransactionDBTable";
    private final Context context;
    private ArrayList<Transaction> transactionList = new ArrayList();
    private Transaction download1transaction;


    public MaintainTransactionDBTable(Context context) {
        this.context = context;
    }

    public void addTransaction(final Transaction transaction) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/insert_transaction.php";
        //Send data
        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "addTransaction saved. " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in addTransaction. " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_TRANSID", transaction.getTransID() + "");
                    params.put("KEY_PARKERID", transaction.getParkerID() + "");
                    params.put("KEY_LEAVERID", transaction.getLeaverID() + "");
                    params.put("KEY_EXCHANGESTATUS", transaction.getExchangeStatus());
                    params.put("KEY_EXCHANGETIME", transaction.getExchangeTime());
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getTransactionList() {
        getAllTransaction();
        return transactionList;
    }

    public Transaction getDownload1transaction(int id) {
        getTransactionById(id);
        return download1transaction;
    }

    public Transaction getTransactionById(int id) {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                download1Transaction(id);
            } else {
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            Log.wtf(TAG, "Error in getTransactionById(id):" + e.getMessage());
        }
        return null;
    }

    public List<Transaction> getAllTransaction() {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                downloadAllTransaction();
            } else {
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            Log.wtf(TAG, "Error in getAllTransaction():" + e.getMessage());
        }
        return null;
    }

    public void download1Transaction(final int id) {
        String url = "http://nottspark.maytwelve.com/nottspark/select_1_transaction.php";
        download1transaction = new Transaction();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson = new Gson();
                            MaintainUserDBTable mu = new MaintainUserDBTable(context);
                            MaintainLeaverDBTable ml = new MaintainLeaverDBTable(context);
                            if (response.length() > 0 && response != null) {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray result = jsonObject.getJSONArray("result");
                                JSONObject courseResponse = result.getJSONObject(0);
                                download1transaction = new Transaction(
                                        Integer.parseInt(courseResponse.getString("KEY_TRANSID")),
                                        Integer.parseInt(courseResponse.getString("KEY_PARKERID")),
                                        Integer.parseInt(courseResponse.getString("KEY_LEAVERID")),
                                        courseResponse.getString("KEY_EXCHANGESTATUS"),
                                        courseResponse.getString("KEY_EXCHANGETIME")
                                );
                                Log.wtf(TAG, "download1Transaction completed:" + download1transaction.toString());
                            } else
                                Log.wtf(TAG, "Error in download1Transaction:" + response);

                        } catch (Exception e) {
                            Log.wtf(TAG, "Error in download1Transaction catch:" + e.getMessage() + " response: " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(TAG, "Error in download1Transaction:" + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("KEY_TRANSID", id + "");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    public void downloadAllTransaction() {
        transactionList = new ArrayList<>();
        transactionList.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/select_all_transactions.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0 && response != null) {
                            try {
                                Gson gson = new Gson();
                                MaintainUserDBTable mu = new MaintainUserDBTable(context);
                                MaintainLeaverDBTable ml = new MaintainLeaverDBTable(context);
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject courseResponse = (JSONObject) response.get(i);
                                    Transaction transaction = new Transaction(
                                            Integer.parseInt(courseResponse.getString("KEY_TRANSID")),
                                            Integer.parseInt(courseResponse.getString("KEY_PARKERID")),
                                            Integer.parseInt(courseResponse.getString("KEY_LEAVERID")),
                                            courseResponse.getString("KEY_EXCHANGESTATUS"),
                                            courseResponse.getString("KEY_EXCHANGETIME")
                                    );
                                    transactionList.add(transaction);
                                    Log.wtf(TAG, "Transaction Data :" + transaction.toString());
                                }

                            } catch (Exception e) {
                                Log.wtf(TAG, "Error in downloadAllTransaction catch:" + e.getMessage());
                            }
                        } else
                            Log.wtf(TAG, "Error in download1Transaction Response is Null :" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.wtf(TAG, "Error in downloadAllTransaction:" + volleyError.getMessage());

                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void updateTransaction(final Transaction transaction) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/update_transaction_info.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "Transaction is updated " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in updateTransaction. " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_TRANSID", transaction.getTransID() + "");
                    params.put("KEY_PARKERID", transaction.getParkerID() + "");
                    params.put("KEY_LEAVERID", transaction.getLeaverID() + "");
                    params.put("KEY_EXCHANGESTATUS", transaction.getExchangeStatus());
                    params.put("KEY_EXCHANGETIME", transaction.getExchangeTime());
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteTransaction(final int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/delete_transaction.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "Transaction is deleted: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in delete transaction: " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_TRANSID", id + "");
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        final int[] totalTransaction = new int[1];
        String url = "http://nottspark.maytwelve.com/nottspark/get_transaction_count.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            totalTransaction[0] = Integer.parseInt(response);
                            Log.wtf(TAG, "Success getCount: " + response);
                        } catch (Exception e) {
                            Log.wtf(TAG, "Error in getCount catch:" + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(TAG, "Error in getCount:" + error.getMessage());
                    }
                }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return totalTransaction[0];
    }

}