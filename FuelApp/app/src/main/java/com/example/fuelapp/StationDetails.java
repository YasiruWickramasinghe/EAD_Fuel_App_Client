package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StationDetails extends AppCompatActivity {
    private RequestQueue requestQueue;
    private JsonArrayRequest arrayRequest;

    String url = "http://10.0.2.2:5109/api/Queue/";
    String url2 = "http://10.0.2.2:5109/api/station-status/";
    String station = "Tangalla";
    String p_start_time, p_end_time, date,f_type, d_start_time, d_end_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

        getVehicleDetails();
        getStationDetails();
    }



    public void getVehicleDetails() {
        arrayRequest = new JsonArrayRequest(url + "station/" + station, new Response.Listener<JSONArray>() {
            int c_count = 0, b_count = 0, l_count = 0;
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                Log.d("TESTM", String.valueOf(response.length()));
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String type = jsonObject.getString("vehicleType");

                        if(type.equals("Car")) c_count++;
                        else if (type.equals("Lorry")) l_count++;
                        else if (type.equals("Bike")) b_count++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("TESTM", String.valueOf(c_count));
                Log.d("TESTM", String.valueOf(l_count));
                Log.d("TESTM", String.valueOf(b_count));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(arrayRequest);
    }




    public void getStationDetails() {
        arrayRequest = new JsonArrayRequest(url2 + "station/" + station, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        String type = jsonObject.getString("fuelType");

                        if(type.equals("Diesel")) {
                            d_start_time = jsonObject.getString("startTime");
                            d_end_time = jsonObject.getString("endTime");
                            date = jsonObject.getString("date");
                        }
                        else if (type.equals("Petrol")){
                            p_start_time = jsonObject.getString("startTime");
                            p_end_time = jsonObject.getString("endTime");
                            date = jsonObject.getString("date");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.d("TESTM", String.valueOf(p_start_time));
                Log.d("TESTM", String.valueOf(p_end_time));
                Log.d("TESTM", String.valueOf(date));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(arrayRequest);
    }
}