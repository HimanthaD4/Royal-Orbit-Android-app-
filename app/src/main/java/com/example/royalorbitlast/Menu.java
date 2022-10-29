package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Menu extends AppCompatActivity {
    TextInputLayout fullname;
    TextView fullNameLabel;
    private ImageButton foods;
    private ImageButton rooms;
    private ImageButton halls;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);

        fullNameLabel = findViewById(R.id.fullNameLable);

//show all data
        showAllUserData();

        foods = (ImageButton) findViewById(R.id.foods);
        rooms = (ImageButton) findViewById(R.id.rooms);
        halls = (ImageButton) findViewById(R.id.halls);

        foods.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openFoods();
            }});

        rooms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRooms();
            } });

        halls.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHalls();
            } });

    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("name");
        fullNameLabel.setText(user_username); }

    private void openHalls() {
        Intent intent = new Intent(this, HallReservation.class);
        startActivity(intent);
    }

    private void openRooms() {
        Intent intent = new Intent(this, RoomReservation.class);
        startActivity(intent);
    }

    private void openFoods() {
        Intent intent = new Intent(this, FoodReservation.class);
        startActivity(intent);
    }
}