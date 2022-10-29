package com.example.royalorbit.activities.event;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.royalorbit.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class CreateEventRes extends AppCompatActivity {


    LinearLayout event1;
    LinearLayout event2;
    LinearLayout event3;
    LinearLayout event4;
    String eventType, startDate, startTime, endDate, endTime;
    DatePickerDialog.OnDateSetListener setStartDateListener;
    TimePickerDialog.OnTimeSetListener setStartTimeListener;
    DatePickerDialog.OnDateSetListener setEndDateListener;
    TimePickerDialog.OnTimeSetListener setEndTimeListener;
    TextInputEditText contactNumInput, startTimeInput, endTimeInput;
    CheckBox flowerCheckBox, fabricCheckBox, stageDesignCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event_res);


        event1 = (LinearLayout) findViewById(R.id.event_1);
        event2 = (LinearLayout) findViewById(R.id.event_2);
        event3 = (LinearLayout) findViewById(R.id.event_3);
        event4 = (LinearLayout) findViewById(R.id.event_4);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        contactNumInput = findViewById(R.id.contact_number);
        startTimeInput = findViewById(R.id.start_time);
        endTimeInput = findViewById(R.id.end_time);
        flowerCheckBox = findViewById(R.id.flower);
        fabricCheckBox = findViewById(R.id.fabric);
        stageDesignCheckBox = findViewById(R.id.stage_design);

        startTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEventRes.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setStartDateListener, year, month, day);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEventRes.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setStartTimeListener, hour, minute, true);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
                datePickerDialog.show();
            }
        });

        setStartDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                startDate = dayOfMonth + "/" + month + "/" + year;
                startTimeInput.setText(startDate+" "+startTime);
            }
        };

        setStartTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                startTime = hourOfDay+":"+minute;
                startTimeInput.setText(startDate+" "+startTime);
            }
        };

        endTimeInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEventRes.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setEndDateListener, year, month, day);
                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateEventRes.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, setEndTimeListener, hour, minute, true);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.show();
                datePickerDialog.show();
            }
        });

        setEndDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                endDate = dayOfMonth + "/" + month + "/" + year;
                endTimeInput.setText(endDate+" "+endTime);
            }
        };

        setEndTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                endTime = hourOfDay+":"+minute;
                endTimeInput.setText(endDate+" "+endTime);
            }
        };
    }

    public void selectBirthdayParty(View view) {
        event1.setBackground(getResources().getDrawable(R.drawable.background_color_radius));
        event2.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event3.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event4.setBackground(getResources().getDrawable(R.drawable.background_radius));
        eventType = "Birthday Party";
    }

    public void selectAnniversaryParty(View view) {
        event1.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event2.setBackground(getResources().getDrawable(R.drawable.background_color_radius));
        event3.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event4.setBackground(getResources().getDrawable(R.drawable.background_radius));
        eventType = "Anniversary Party";
    }

    public void selectBatchParty(View view) {
        event1.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event2.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event3.setBackground(getResources().getDrawable(R.drawable.background_color_radius));
        event4.setBackground(getResources().getDrawable(R.drawable.background_radius));
        eventType = "Batch Party";
    }

    public void selectDJParty(View view) {
        event1.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event2.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event3.setBackground(getResources().getDrawable(R.drawable.background_radius));
        event4.setBackground(getResources().getDrawable(R.drawable.background_color_radius));
        eventType = "DJ Party";
    }

    public void reservation(View view) {

        if (TextUtils.isEmpty(eventType)) {
            Toast.makeText(CreateEventRes.this, "Event type cannot be empty", Toast.LENGTH_SHORT).show();
        }  else if (TextUtils.isEmpty(contactNumInput.getText().toString())) {
            Toast.makeText(CreateEventRes.this, "Contact number cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(startDate)) {
            Toast.makeText(CreateEventRes.this, "Start Date cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(startTime)) {
            Toast.makeText(CreateEventRes.this, "Start Time cannot be empty", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(endDate)) {
            Toast.makeText(CreateEventRes.this, "End Date cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(endTime)) {
            Toast.makeText(CreateEventRes.this, "End Time cannot be empty", Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(CreateEventRes.this, EventResDetails.class);
            intent.putExtra("ACTION", "POST");
            intent.putExtra("eventType", eventType);
            intent.putExtra("flower", flowerCheckBox.isChecked());
            intent.putExtra("fabric", fabricCheckBox.isChecked());
            intent.putExtra("stageDesign", stageDesignCheckBox.isChecked());
            intent.putExtra("contactNum", contactNumInput.getText().toString());
            intent.putExtra("startDateTime", startDate + " " + startTime);
            intent.putExtra("endDateTime", endDate + " " + endTime);
            System.out.println("----------------------------------- eventType " + eventType);
            System.out.println("----------------------------------- startDateTime " +startDate + " " + startTime);
            System.out.println("----------------------------------- endDateTime " +endDate + " " + endTime);
            startActivity(intent);
        }
    }
}