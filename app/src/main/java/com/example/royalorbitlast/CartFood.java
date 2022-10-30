package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CartFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_cart_food);
    }
}