package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class GreenCourt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_green_court);
    }
}