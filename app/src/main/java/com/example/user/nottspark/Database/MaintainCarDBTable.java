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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaintainCarDBTable {
    private static final String TAG = "MaintainCarDBTable";
    private final Context context;
    Boolean checkOtherError;
    private List<Car> carList;
    private Car download1car;


    public MaintainCarDBTable(Context context) {
        this.context = context;
        getAllCar();
        getCarById(1);
        try {
            Log.wtf(TAG, "getAllCar(1) is " + carList.size());
            Log.wtf(TAG, "getAllCar(1) is " + download1car.getCarID());
        } catch (Exception e) {
            Log.wtf(TAG, "getAllCar(1) err " + e.getLocalizedMessage());

        }
    }

    public void addCar(final Car car) {
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
    }

    public List<Car> getCarList() {
        getAllCar();
        return carList;
    }

    public Car getDownload1car(int id) {
        getCarById(id);
        return download1car;
    }

    public Car getCarById(int id) {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                download1Car(id);
            } else {
                checkOtherError = true;
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            checkOtherError = true;
            Log.wtf(TAG, "Error in getCarById(id):" + e.getMessage());
        }
        return null;
    }

    public List<Car> getAllCar() {
        try {
            // Check availability of network connection.
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            Boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (isConnected) {
                downloadAllCar();
            } else {
                checkOtherError = true;
                Log.wtf(TAG, "Network is NOT available");
            }
        } catch (Exception e) {
            checkOtherError = true;
            Log.wtf(TAG, "Error in getAllCar():" + e.getMessage());
        }
        return null;
    }

    public void download1Car(final int id) {
        download1car = null;
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
                            Log.wtf(TAG, "courseResponse.getString(\"KEY_CAR_ID\"): " + courseResponse.getString("KEY_CAR_ID"));
                            String carMake = courseResponse.getString("KEY_CAR_MAKE");
                            String carModel = courseResponse.getString("KEY_CAR_MODEL");
                            String carPlate = courseResponse.getString("KEY_CAR_PLATE");
                            download1car = new Car(carId, carMake, carModel, carPlate);
                            Log.wtf(TAG, "Download completed: " + response);
                            Log.wtf(TAG, "Download completed test: " + download1car.getCarModel());

                        } catch (Exception e) {
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
        Log.wtf(TAG, "Download completed test3: " + download1car.getCarModel());
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Log.wtf(TAG, "Download completed test2: " + download1car.getCarModel());
    }

    public void downloadAllCar() {
        carList = new ArrayList<>();
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
                                Log.wtf(TAG, "1 Car added to carList:" + carList.get(carList.size() - 1).getCarID());

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
    }

    public void updateCar(final Car car) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://notts.esy.es/update_car_info.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "maintaincar123 car is updated " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in updateCar. " + error.toString());
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
    }

    public void deleteCar(final int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://notts.esy.es/deletecar.php";

        try {
            StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.wtf(TAG, "Car is deleted: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.wtf(TAG, "Error in delete car: " + error.toString());
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
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
            queue.add(postRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
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

}