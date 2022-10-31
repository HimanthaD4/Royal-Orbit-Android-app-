package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class ReserveHallDetailsKing extends AppCompatActivity {

    private Button btn_update;
    private Button btn_delete;
    private Button btn_back;

    TextView inDateLabel,noSeatsLabel,noDateLabel,priceLabel,typeLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reserve_hall_details_king);

        btn_update = (Button)findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_back = (Button)findViewById(R.id.btn_back);

        inDateLabel = findViewById(R.id.inDateLabel);
        noSeatsLabel = findViewById(R.id.noSeatsLabel);
        noDateLabel = findViewById(R.id.noDateLabel);
        priceLabel = findViewById(R.id.priceLabel);
        typeLabel = findViewById(R.id.typeLabel);

        showAllUserData();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateKingHall();
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
                Toast.makeText(ReserveHallDetailsKing.this, "Delete Successful", Toast.LENGTH_SHORT).show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
            }
        });



    }
    private void showAllUserData() {
        Intent intent = getIntent();

        String inDate = intent.getStringExtra("Check in date");
        String noSeats = intent.getStringExtra("No of seats");
        String noDate = intent.getStringExtra("No of days");
        String price = intent.getStringExtra("price");
        String type = intent.getStringExtra("type");


        inDateLabel.setText(inDate);
        noSeatsLabel.setText(noSeats);
        noDateLabel.setText(noDate);
        priceLabel.setText(price);
        typeLabel.setText(type);
    }
    public void openUpdateKingHall(){
        Intent intent =  new Intent(this, UpdateKingHall.class);
        startActivity(intent);
    }

    public void openMenu(){
        Intent intent =  new Intent(this, Menu.class);
        startActivity(intent);
    }



}