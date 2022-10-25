package com.example.fuelapp.stationManagement;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.fuelapp.R;
import com.example.fuelapp.stationManagement.Models.Station;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Station> station;
    private String url = "http://10.0.2.2:5109/api/Station/";
    public StationAdapter(Context context, ArrayList<Station> station) {
        this.context = context;
        this.station = station;
    }

    @NonNull
    @Override
    public StationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.station_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationAdapter.MyViewHolder holder, final int position) {
        holder.stationName.setText(station.get(position).getStationName());
        holder.id.setText("#" + String.valueOf(position+1));

    }

    @Override
    public int getItemCount() {
        return station.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView stationName, id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.id);
            stationName = (TextView) itemView.findViewById(R.id.stationName);
        }
    }
}
