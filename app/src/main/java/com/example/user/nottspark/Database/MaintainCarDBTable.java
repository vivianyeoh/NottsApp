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
import com.example.user.nottspark.Model.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaintainCarDBTable {
    private static final String TAG = "MaintainCarDBTable";
    private final Context context;
    Boolean checkOtherError;

    public MaintainCarDBTable(Context context) {
        this.context = context;
    }

    public void addCar(final Car car) {
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://notts.esy.es/insert_car.php";

        //Send data
        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "addCar saved. " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in addCar. " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("KEY_CAR_ID", car.getCarID() + "");
                    params.put("KEY_CAR_MAKE", car.getCarMake());
                    params.put("KEY_CAR_MODEL", car.getCarModel());
                    params.put("KEY_CAR_PLATE", car.getCarPlate());
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
    }//insert car end

    public List<Car> getAllCar() {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                return downloadAllCar();
            } else {
                checkOtherError = true;
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            checkOtherError = true;
            Log.wtf(TAG, "Error in getCar:" + e.getMessage());
        }
        return null;
    }

    public Car getCarById(int id) {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                return download1Car(id);
            } else {
                checkOtherError = true;
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            checkOtherError = true;
            Log.wtf(TAG, "Error in getCarById:" + e.getMessage());
        }
        return null;
    }

    public Car download1Car(final int id) {

        final Car[] car = {null};
        String url = "http://notts.esy.es/select_1_car.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray result = jsonObject.getJSONArray("result");
                            JSONObject courseResponse = result.getJSONObject(0);
                            int carId = Integer.parseInt(courseResponse.getString("KEY_CAR_ID"));
                            String carMake = courseResponse.getString("KEY_CAR_MAKE");
                            String carModel = courseResponse.getString("KEY_CAR_MODEL");
                            String carPlate = courseResponse.getString("KEY_CAR_PLATE");
                            car[0] = new Car(carId, carMake, carModel, carPlate);
                            Log.wtf(TAG, "Success download1Car:" + car[0].getCarID());
                        } catch (JSONException e) {
                            Log.wtf(TAG, "Error in download1Car catch:" + e.getMessage() + " response: " + response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf(TAG, "Error in download1Car:" + error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("KEY_CAR_ID", id + "");
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

        return car[0];
    }

    public int getCount() {
        final int[] totalCar = new int[1];
        String url = "http://notts.esy.es/get_car_count.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            totalCar[0] = Integer.parseInt(response);
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

        return totalCar[0];
    }

    public List<Car> downloadAllCar() {
        final List<Car> carList = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://notts.esy.es/select_all_cars.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    public void onResponse(JSONArray response) {
                        try {
                            carList.clear();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject courseResponse = (JSONObject) response.get(i);
                                int carId = Integer.parseInt(courseResponse.getString("KEY_CAR_ID"));
                                String carMake = courseResponse.getString("KEY_CAR_MAKE");
                                String carModel = courseResponse.getString("KEY_CAR_MODEL");
                                String carPlate = courseResponse.getString("KEY_CAR_PLATE");
                                Car car = new Car(carId, carMake, carModel, carPlate);
                                carList.add(car);
                            }

                        } catch (Exception e) {
                            checkOtherError = true;
                            Log.wtf(TAG, "Error in downloadAllCar catch:" + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        checkOtherError = true;
                        Log.wtf(TAG, "Error in downloadAllCar:" + volleyError.getMessage());

                    }
                });
        queue.add(jsonObjectRequest);
        return carList;
    }


}