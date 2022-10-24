package com.example.fuelapp.stationManager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StatusUpdateActivity extends AppCompatActivity {
    String s_time, e_time, t_date,f_type;
    String url = "http://10.0.2.2:5109/api/station-status";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_update);

        // Submit button
        Button submit = (Button) findViewById(R.id.btnSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s_time == null || e_time == null || t_date == null || f_type == null) {
                    Toast.makeText(getApplicationContext(), "Input the all fields before submit", Toast.LENGTH_SHORT).show();
                }
                else{
                    Submit();
                }
            }
        });

        //set the Current date
        String date_n = new SimpleDateFormat("yyyy MMM dd ", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.txtDate);
        date.setText(date_n);
        t_date = date_n;


        //Get Times Inputs
        EditText startTime = (EditText) findViewById(R.id.editStartTime);
        startTime.setInputType(InputType.TYPE_NULL);
        EditText endTime = (EditText) findViewById(R.id.editEndTime);
        endTime.setInputType(InputType.TYPE_NULL);

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStartTimeDialog(startTime);
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEndTimeDialog(endTime);
            }
        });


        //Add Dropdown menu handle
        Spinner spinner = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                f_type = (String) parent.getItemAtPosition(position);
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

    }

    private void showEndTimeDialog(EditText endTime) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, min);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:aa");
                endTime.setText(simpleDateFormat.format(calendar.getTime()));
                e_time = simpleDateFormat.format(calendar.getTime());

            }
        };

        new TimePickerDialog(StatusUpdateActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }



    private void showStartTimeDialog(EditText startTime) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, min);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:aa");
                startTime.setText(simpleDateFormat.format(calendar.getTime()));
                s_time = simpleDateFormat.format(calendar.getTime());
            }
        };

        new TimePickerDialog(StatusUpdateActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }





    //    Insert Function
    private void Submit() {

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("stationName", "Tangalla");
            jsonBody.put("stationNo", "2");
            jsonBody.put("stationOwnerName", "Yasiru");
            jsonBody.put("startTime", s_time);
            jsonBody.put("endTime", e_time);
            jsonBody.put("date", t_date);
            jsonBody.put("fuelType", f_type);
            jsonBody.put("availability", "Yes");

            final String mRequestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.v("TEST", response);
                    if(response.equals("201")){
                        Toast.makeText(StatusUpdateActivity.this, "Status for today is already added", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(StatusUpdateActivity.this, "Data was submitted", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_RESPONSE", error.toString());
                    Toast.makeText(StatusUpdateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            requestQueue.add(stringRequest);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }


}