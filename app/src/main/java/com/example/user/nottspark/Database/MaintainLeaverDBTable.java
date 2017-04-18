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
import com.example.user.nottspark.Model.Leaver;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaintainLeaverDBTable {
    private static final String TAG = "MaintainLeaverDBTable";
    private final Context context;
    private ArrayList<Leaver> leaverList = new ArrayList();
    private Leaver download1leaver;


    public MaintainLeaverDBTable(Context context) {
        this.context = context;
    }

    public void addLeaver(final Leaver leaver) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/insert_leaver.php";
        //Send data
        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "addLeaver saved. " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in addLeaver. " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_L_ID", "");
                    params.put("KEY_L_USER_ID", leaver.getUserID() + "");
                    params.put("KEY_L_LOCATION", leaver.getLocation());
                    params.put("KEY_L_DESC", leaver.getLeaverDesc());
                    params.put("KEY_L_PARINGSTATUS", leaver.isPairingStatus() + "");
                    params.put("KEY_L_DATETIME", leaver.getDate());
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

    public List<Leaver> getLeaverList() {
        getAllLeaver();
        return leaverList;
    }

    public Leaver getDownload1leaver(int id) {
        getLeaverById(id);
        return download1leaver;
    }

    private Leaver getLeaverById(int id) {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                download1Leaver(id);
            } else {
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            Log.wtf(TAG, "Error in getLeaverById(id):" + e.getMessage());
        }
        return null;
    }

    private List<Leaver> getAllLeaver() {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                downloadAllLeaver();
            } else {
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            Log.wtf(TAG, "Error in getAllLeaver():" + e.getMessage());
        }
        return null;
    }

    private void download1Leaver(final int id) {
        String url = "http://nottspark.maytwelve.com/nottspark/select_1_leaver.php";
        download1leaver = new Leaver();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson = new Gson();
                            MaintainUserDBTable mu = new MaintainUserDBTable(context);
                            if (response.length() > 0 && response != null) {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray result = jsonObject.getJSONArray("result");
                                JSONObject courseResponse = result.getJSONObject(0);
                                download1leaver = new Leaver(
                                        Integer.parseInt(courseResponse.getString("KEY_L_ID")),
                                        Integer.parseInt(courseResponse.getString("KEY_L_USER_ID")),
                                        courseResponse.getString("KEY_L_LOCATION"),
                                        courseResponse.getString("KEY_L_DESC"),
                                        Integer.parseInt(courseResponse.getString("KEY_L_PARINGSTATUS")),
                                        courseResponse.getString("KEY_L_DATETIME")
                                );
                                Log.wtf(TAG, "download1Leaver completed: " + download1leaver.toString());
                            } else
                                Log.wtf(TAG, "Error in download1Leaver:" + response);

                        } catch (Exception e) {
                            Log.wtf(TAG, "Error in download1Leaver catch:" + e.getMessage() + " response: " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(TAG, "Error in download1Leaver:" + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("KEY_L_ID", id + "");
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

    private void downloadAllLeaver() {
        leaverList = new ArrayList<>();
        leaverList.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/select_all_leavers.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        int ttl = getCount();
                        if (response.length() > 0 && response != null) {
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject courseResponse = (JSONObject) response.get(i);
                                    Leaver leaver = new Leaver(
                                            Integer.parseInt(courseResponse.getString("KEY_L_ID")),
                                            Integer.parseInt(courseResponse.getString("KEY_L_USER_ID")),
                                            courseResponse.getString("KEY_L_LOCATION"),
                                            courseResponse.getString("KEY_L_DESC"),
                                            Integer.parseInt(courseResponse.getString("KEY_L_PARINGSTATUS")),
                                            courseResponse.getString("KEY_L_DATETIME")
                                    );
                                    leaverList.add(leaver);
                                    Log.wtf(TAG, "Leaver Data :" + leaver.toString());
                                    if (i == getCount() - 1)
                                        break;
                                }

                            } catch (Exception e) {
                                Log.wtf(TAG, "Error in downloadAllLeaver catch:" + e.getMessage());
                            }
                        } else
                            Log.wtf(TAG, "Error in download1Leaver Response is Null :" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.wtf(TAG, "Error in downloadAllLeaver:" + volleyError.getMessage());

                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void updateLeaver(final Leaver leaver) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/update_leaver_info.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "Leaver is updated " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in updateLeaver. " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_L_ID", leaver.getLeaverID() + "");
                    params.put("KEY_L_USER_ID", leaver.getUserID() + "");
                    params.put("KEY_L_LOCATION", leaver.getLocation());
                    params.put("KEY_L_DESC", leaver.getLeaverDesc());
                    params.put("KEY_L_PARINGSTATUS", leaver.isPairingStatus() + "");
                    params.put("KEY_L_DATETIME", leaver.getDate());
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

    public void updateLeaverStatus(final int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/update_leaver_status.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "Leaver status is updated " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in updateLeaverStatus. " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_L_ID", id + "");
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

    public void deleteLeaver(final int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/delete_leaver.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "Leaver is deleted: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in delete leaver: " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_L_ID", id + "");
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
        final int[] totalLeaver = new int[1];
        String url = "http://nottspark.maytwelve.com/nottspark/get_leaver_count.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            totalLeaver[0] = Integer.parseInt(response);
                            Log.wtf(TAG, "Success getCount: " + totalLeaver[0]);
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
        return totalLeaver[0];
    }

}