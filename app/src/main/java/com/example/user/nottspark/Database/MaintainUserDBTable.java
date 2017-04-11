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
import com.example.user.nottspark.Model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaintainUserDBTable {
    private static final String TAG = "MaintainUserDBTable";
    private final Context context;
    private ArrayList<User> userList = new ArrayList();
    private User download1user;

    public MaintainUserDBTable(Context context) {
        this.context = context;
    }

    public void addUser(final User user) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/insert_user.php";
        //Send data
        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "addUser saved. " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in addUser. " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_USER_USERNAME", user.getUserUsername());
                    params.put("KEY_USER_NAME", user.getUserName());
                    params.put("KEY_USER_CONTACTNUM", user.getUserContactNum());
                    params.put("KEY_USER_EMAIL", user.getUserEmail());
                    params.put("KEY_CAR_MAKE", user.getCarMake());
                    params.put("KEY_CAR_MODEL", user.getCarModel());
                    params.put("KEY_CAR_PLATE", user.getCarPlate());
                    params.put("KEY_REGISTERDATE", user.getRegisterDate());
                    params.put("KEY_USER_ACCOUNTTYPE", user.getUserAccountType());
                    params.put("KEY_USER_PASSWORD", user.getUserPassword());
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

    public List<User> getUserList() {
        getAllUser();
        return userList;
    }

    public User getDownload1user(int id) {
        getUserById(id);
        return download1user;
    }

    private User getUserById(int id) {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                download1User(id);
            } else {
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            Log.wtf(TAG, "Error in getUserById(id):" + e.getMessage());
        }
        return null;
    }

    private List<User> getAllUser() {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                downloadAllUser();
            } else {
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            Log.wtf(TAG, "Error in getAllUser():" + e.getMessage());
        }
        return null;
    }

    private void download1User(final int id) {
        download1user = new User();
        String url = "http://nottspark.maytwelve.com/nottspark/select_1_user.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response.length() > 0 && response != null) {
                                JSONObject jsonObject = new JSONObject(response);

                                JSONArray result = jsonObject.getJSONArray("result");
                                JSONObject courseResponse = result.getJSONObject(0);
                                download1user = new User(
                                        Integer.parseInt(courseResponse.getString("KEY_USER_ID")),
                                        courseResponse.getString("KEY_USER_USERNAME"),
                                        courseResponse.getString("KEY_USER_NAME"),
                                        courseResponse.getString("KEY_USER_CONTACTNUM"),
                                        courseResponse.getString("KEY_USER_EMAIL"),
                                        courseResponse.getString("KEY_CAR_MAKE"),
                                        courseResponse.getString("KEY_CAR_MODEL"),
                                        courseResponse.getString("KEY_CAR_PLATE"),
                                        courseResponse.getString("KEY_REGISTERDATE"),
                                        courseResponse.getString("KEY_USER_ACCOUNTTYPE"),
                                        courseResponse.getString("KEY_USER_PASSWORD")
                                );
                                Log.wtf(TAG, "download1User completed" + download1user.toString());
                            } else
                                Log.wtf(TAG, "Error in download1User:" + response);

                        } catch (Exception e) {
                            Log.wtf(TAG, "Error in download1User catch:" + e.getMessage() + " response: " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(TAG, "Error in download1User:" + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("KEY_USER_ID", id + "");
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

    private void downloadAllUser() {
        userList = new ArrayList<>();
        userList.clear();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/select_all_users.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        int ttl = getCount();

                        if (response.length() > 0 && response != null) {
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject courseResponse = (JSONObject) response.get(i);
                                    User user = new User(
                                            Integer.parseInt(courseResponse.getString("KEY_USER_ID")),
                                            courseResponse.getString("KEY_USER_USERNAME"),
                                            courseResponse.getString("KEY_USER_NAME"),
                                            courseResponse.getString("KEY_USER_CONTACTNUM"),
                                            courseResponse.getString("KEY_USER_EMAIL"),
                                            courseResponse.getString("KEY_CAR_MAKE"),
                                            courseResponse.getString("KEY_CAR_MODEL"),
                                            courseResponse.getString("KEY_CAR_PLATE"),
                                            courseResponse.getString("KEY_REGISTERDATE"),
                                            courseResponse.getString("KEY_USER_ACCOUNTTYPE"),
                                            courseResponse.getString("KEY_USER_PASSWORD")
                                    );
                                    userList.add(user);
                                    Log.wtf(TAG, "User Data :" + user.toString());
                                    if (i == ttl - 1)
                                        break;
                                }
                            } catch (Exception e) {
                                Log.wtf(TAG, "Error in downloadAllUser catch:" + e.getMessage());
                            }
                        } else
                            Log.wtf(TAG, "Error in download1User Response is Null :" + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.wtf(TAG, "Error in downloadAllUser:" + volleyError.getMessage());

                    }
                });
        queue.add(jsonObjectRequest);
    }

    public void updateUser(final User user) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/update_user_info.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "User is updated " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in updateUser. " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_USER_ID", user.getUserID() + "");
                    params.put("KEY_USER_USERNAME", user.getUserUsername());
                    params.put("KEY_USER_NAME", user.getUserName());
                    params.put("KEY_USER_CONTACTNUM", user.getUserContactNum());
                    params.put("KEY_USER_EMAIL", user.getUserEmail());
                    params.put("KEY_CAR_MAKE", user.getCarMake());
                    params.put("KEY_CAR_MODEL", user.getCarModel());
                    params.put("KEY_CAR_PLATE", user.getCarPlate());
                    params.put("KEY_REGISTERDATE", user.getRegisterDate());
                    params.put("KEY_USER_ACCOUNTTYPE", user.getUserAccountType());
                    params.put("KEY_USER_PASSWORD", user.getUserPassword());
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

    public void deleteUser(final int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://nottspark.maytwelve.com/nottspark/delete_user.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "User is deleted: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in delete user: " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_USER_ID", id + "");
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
        final int[] totalUser = new int[1];
        String url = "http://nottspark.maytwelve.com/nottspark/get_user_count.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    totalUser[0] = Integer.parseInt(response);
                    Log.wtf(TAG, "Success getCount: " + totalUser[0]);
                } catch (Exception e) {
                    Log.wtf(TAG, "Error in getCount catch:" + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf(TAG, "Error in getCount:" + error.getMessage());
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return totalUser[0];
    }
}

