package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HallReservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hall_reservation);
    }
}