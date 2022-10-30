package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReservedRoomDetailsDouble extends AppCompatActivity {


    private Button btn_update;
    private Button btn_delete;
    private Button btn_back;

    TextView inDateLabel,noRoomLabel,noDateLabel,priceLabel,typeLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_reserved_room_details_double);

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
                openUpdateDouble();
            }
        });


        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMenu();
                Toast.makeText(ReservedRoomDetailsDouble.this, "Delete Successful", Toast.LENGTH_SHORT).show();
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
        Intent intent1 = getIntent();

        String inDate = intent1.getStringExtra("Check in date");
        String noRoom = intent1.getStringExtra("No of Rooms");
        String noDate = intent1.getStringExtra("No of days");
        String price = intent1.getStringExtra("price");
        String type = intent1.getStringExtra("type");



        inDateLabel.setText(inDate);
        noRoomLabel.setText(noRoom);
        noDateLabel.setText(noDate);
        priceLabel.setText(price);
        typeLabel.setText(type);
    }
    public void openUpdateDouble(){
        Intent intent =  new Intent(this, UpdateDoubleRoom.class);
        startActivity(intent);
    }

    public void openMenu(){
        Intent intent =  new Intent(this, Menu.class);
        startActivity(intent);
    }



}