package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReserveRoomDetailsQueen extends AppCompatActivity {


    private Button btn_update;
    private Button btn_delete;
    private Button btn_back;

    TextView inDateLabel,noRoomLabel,noDateLabel,priceLabel,typeLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reserve_room_details_queen);

        btn_update = (Button)findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);
        btn_back = (Button)findViewById(R.id.btn_back);

        inDateLabel = findViewById(R.id.inDateLabel);
        noRoomLabel = findViewById(R.id.noRoomLabel);
        noDateLabel = findViewById(R.id.noDateLabel);
        priceLabel = findViewById(R.id.priceLabel);
        typeLabel = findViewById(R.id.typeLabel);

        showAllUserData();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateKing();
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
                Toast.makeText(ReserveRoomDetailsQueen.this, "Delete Successful", Toast.LENGTH_SHORT).show();
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
    public void openUpdateKing(){
        Intent intent =  new Intent(this, UpdateKingRoom.class);
        startActivity(intent);
    }

    public void openMenu(){
        Intent intent =  new Intent(this, Menu.class);
        startActivity(intent);
    }



}