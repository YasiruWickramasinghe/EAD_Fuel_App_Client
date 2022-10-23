package com.example.fuelapp.stationManager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.fuelapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StatusUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_update);

        //set the Current date
        String date_n = new SimpleDateFormat("yyyy MMM dd ", Locale.getDefault()).format(new Date());
        TextView date  = (TextView) findViewById(R.id.txtDate);
        date.setText(date_n);



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
            }
        };

        new TimePickerDialog(StatusUpdateActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
    }



}