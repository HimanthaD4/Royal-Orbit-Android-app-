package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ReservedRoomDetalis extends AppCompatActivity {


    TextView inDateLabel,noRoomLabel,noDateLabel,priceLabel,typeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reserved_room_detalis);

        inDateLabel = findViewById(R.id.inDateLabel);
        noRoomLabel = findViewById(R.id.noRoomLabel);
        noDateLabel = findViewById(R.id.noDateLabel);
        priceLabel = findViewById(R.id.priceLabel);
        typeLabel = findViewById(R.id.typeLabel);

        showAllUserData();

    }
    private void showAllUserData() {
        Intent intent = getIntent();

        String inDate = intent.getStringExtra("Check in date");
        String noRoom = intent.getStringExtra("No of Rooms");
        String noDate = intent.getStringExtra("No of days");
        String price = intent.getStringExtra("price");
        String type = intent.getStringExtra("type");



        inDateLabel.setText(inDate);
        noRoomLabel.setText(noRoom);
        noDateLabel.setText(noDate);
        priceLabel.setText(price);
        typeLabel.setText(type);
    }


}