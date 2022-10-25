package com.example.fuelapp.stationManagement;

import com.example.fuelapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fuelapp.stationManagement.Models.Station;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StationManagementActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    static StationManagementActivity instance;
    private RequestQueue requestQueue;
    private SwipeRefreshLayout refresh;
    private ArrayList<Station> station = new ArrayList<>();
    private JsonArrayRequest arrayRequest;
    private RecyclerView recyclerView;
    private Dialog dialog;
    private StationAdapter stationAdapter;

    private String url ="http://10.0.2.2:5109/api/Station";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_management);

        refresh = (SwipeRefreshLayout) findViewById(R.id.stationSwipedown);
        recyclerView = (RecyclerView) findViewById(R.id.station);

        dialog = new Dialog(this);

        refresh.setOnRefreshListener(this);
        refresh.post(new Runnable() {
            @Override
            public void run() {
                station.clear();
                getData();
            }
        });
    }
    public static StationManagementActivity getInstance() {
        return instance;
    }

    public void getData() {
        refresh.setRefreshing(true);

        arrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        Station st = new Station();
                        //st.setId(jsonObject.getString("Id"));
                        st.setStationName(jsonObject.getString("stationName"));
                        Log.d("TEST", jsonObject.getString("stationName"));


                        station.add(st);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapterPush(station);
                refresh.setRefreshing(false);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue( StationManagementActivity.this);
        requestQueue.add(arrayRequest);
    }

    private void adapterPush(ArrayList<Station> station) {
        stationAdapter = new StationAdapter(this, station);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(stationAdapter);
    }

    @Override
    public void onRefresh() {
        station.clear();
        getData();
    }
}