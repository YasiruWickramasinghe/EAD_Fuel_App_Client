package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StationDetailsActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private JsonArrayRequest arrayRequest;

    String url = "http://10.0.2.2:5109/api/Queue/";
    String url2 = "http://10.0.2.2:5109/api/station-status/";
    String station = "Tangalla";
    String st_no="4";
    String p_start_time, p_end_time,d_date, p_date,f_type, d_start_time, d_end_time;

    TextView station_name, station_No, car_no, bike_no, tweel_no,tot_veh1, start_time1, end_time1, date1;
    TextView station_name2, station_No2, van_no, lorry_no,tot_veh2, start_time2, end_time2, date2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_details);

        station_name = findViewById(R.id.textStationName);
        station_No = findViewById(R.id.textStationNo);
        car_no = findViewById(R.id.txtCar);
        bike_no = findViewById(R.id.txtBike);
        tweel_no = findViewById(R.id.txtThreewheel);
        tot_veh1 = findViewById(R.id.txtTotalVehicle1);
        date1 = findViewById(R.id.txtDate);
        start_time1 = findViewById(R.id.txtStartTime1);
        end_time1 = findViewById(R.id.txtEndTime1);

        station_name2 = findViewById(R.id.textStationName2);
        station_No2 = findViewById(R.id.textStationNo2);
        van_no = findViewById(R.id.txtVan);
        lorry_no = findViewById(R.id.txtLorry);
        tot_veh2 = findViewById(R.id.txtTotalVehicle2);
        date2 = findViewById(R.id.txtDate2);
        start_time2 = findViewById(R.id.txtStartTime2);
        end_time2 = findViewById(R.id.txtEndTime2);

        getVehicleDetails();
        getStationDetails();

        //Assign values to text views
        station_name2.setText(station);
        station_No2.setText(st_no);

    }



    public void getVehicleDetails() {
        arrayRequest = new JsonArrayRequest(url + "station/" + station, new Response.Listener<JSONArray>() {
            int c_count = 0, b_count = 0, l_count = 0,  v_count = 0, t_count = 0;
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
                        else if (type.equals("Van")) v_count++;
                        else if (type.equals("Bike")) b_count++;
                        else if (type.equals("Three-Wheel")) t_count++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                car_no.setText(String.valueOf(c_count));
                bike_no.setText(String.valueOf(b_count));
                tweel_no.setText(String.valueOf(t_count));
                tot_veh1.setText(String.valueOf(c_count+b_count+t_count));

                van_no.setText(String.valueOf(v_count));
                lorry_no.setText(String.valueOf(l_count));
                tot_veh2.setText(String.valueOf(l_count+v_count));
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
                            d_date = jsonObject.getString("date");
                        }
                        else if (type.equals("Petrol")){
                            p_start_time = jsonObject.getString("startTime");
                            p_end_time = jsonObject.getString("endTime");
                            p_date = jsonObject.getString("date");
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                date1.setText(p_date);
                start_time1.setText(p_start_time);
                end_time1.setText(p_end_time);

                date2.setText(d_date);
                start_time2.setText(d_start_time);
                end_time2.setText(d_end_time);

                Log.d("TESTM", String.valueOf(p_start_time));
                Log.d("TESTM", String.valueOf(p_end_time));
                Log.d("TESTM", String.valueOf(p_date));
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