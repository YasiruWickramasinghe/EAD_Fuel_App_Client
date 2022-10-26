package com.example.fuelapp.queueManagement;

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
import com.example.fuelapp.stationManager.StatusUpdateActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class QueueManagementActivity extends AppCompatActivity {

    String arrival_time, departure_time, currentDate;
    String url = "http://10.0.2.2:5109/api/Queue";
    String vehicleNo = "ABC4512";
    String stationName = "Matara";
    String fuelType = "Petrol";

    TextView  vehicle_no, station_name, fuel_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_management);

        vehicle_no = findViewById(R.id.vehicleNoValue);
        station_name = findViewById(R.id.stationNameValue);
        fuel_type = findViewById(R.id.fuelTypeValue);

        vehicle_no.setText(vehicleNo);
        station_name.setText(stationName);
        fuel_type.setText(fuelType);

        //Submit button
        Button arrivalSubmit = (Button) findViewById(R.id.arrivalSubmit);
        Button departureBeforeSubmit = (Button) findViewById(R.id.departureBeforeSubmit);
        Button departureAfterSubmit = (Button) findViewById(R.id.departureAfterSubmit);

        arrivalSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrivalSubmit();

//                if(arrivalTime == null && departureTime == null || arrivalTime == null) {
//                    Toast.makeText(getApplicationContext(), "ArrivalTime field is Required", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Submit();
//                }
            }
        });

        departureBeforeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // DepartureBeforeSubmit();
            }
        });

        departureAfterSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // DepartureAfterSubmit();
            }
        });

        //Current date
        String todayDate = new SimpleDateFormat("yyyy MMM dd ", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.currentDate);
        date.setText("Date: "+todayDate);
        currentDate = todayDate;


        //Get Times Inputs
        EditText arrivalTime = (EditText) findViewById(R.id.arrivalField);
        arrivalTime.setInputType(InputType.TYPE_NULL);
        EditText departureTime = (EditText) findViewById(R.id.departureField);
        departureTime.setInputType(InputType.TYPE_NULL);

        arrivalTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showArrivalTimeDialog(arrivalTime);
            }
        });

        departureTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDepartureTimeDialog(departureTime);
            }
        });

    }

    private void showArrivalTimeDialog(EditText arrivalTime) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, min);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:aa");
                arrivalTime.setText(simpleDateFormat.format(calendar.getTime()));
                arrival_time = simpleDateFormat.format(calendar.getTime());

            }
        };

        new TimePickerDialog(QueueManagementActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }



    private void showDepartureTimeDialog(EditText departureTime) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int min) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, min);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:aa");
                departureTime.setText(simpleDateFormat.format(calendar.getTime()));
                departure_time = simpleDateFormat.format(calendar.getTime());
            }
        };

        new TimePickerDialog(QueueManagementActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }

    //Arrival Submit
    private void ArrivalSubmit() {

//        try {
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            JSONObject jsonBody = new JSONObject();
//            jsonBody.put("stationName", stationName);
//            jsonBody.put("stationNo", stationNo);
//            jsonBody.put("stationOwnerName", stationOwnerName);
//            jsonBody.put("startTime", s_time);
//            jsonBody.put("endTime", e_time);
//            jsonBody.put("date", t_date);
//            jsonBody.put("fuelType", f_type);
//            jsonBody.put("availability", "Yes");
//
//            final String mRequestBody = jsonBody.toString();
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.v("TEST", response);
//                    if(response.equals("201")){
//                        Toast.makeText(StatusUpdateActivity.this, "Status for today is already added", Toast.LENGTH_SHORT).show();
//                    }
//                    else Toast.makeText(StatusUpdateActivity.this, "Data was submitted", Toast.LENGTH_SHORT).show();
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e("LOG_RESPONSE", error.toString());
//                    Toast.makeText(StatusUpdateActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            }) {
//                @Override
//                public String getBodyContentType() {
//                    return "application/json; charset=utf-8";
//                }
//
//                @Override
//                public byte[] getBody() throws AuthFailureError {
//                    try {
//                        return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
//                    } catch (UnsupportedEncodingException uee) {
//                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
//                        return null;
//                    }
//                }
//
//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String responseString = "";
//                    if (response != null) {
//                        responseString = String.valueOf(response.statusCode);
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                }
//            };
//
//            requestQueue.add(stringRequest);
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
    }

    private void DepartureBeforeSubmit() {

    }

    private void DepartureAfterSubmit() {

    }
}