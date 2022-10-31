package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomReservation extends AppCompatActivity {

    private Button btn_king;
    private Button btn_Queen;
    private Button btn_Double;
    private Button btn_single;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_room_reservation);

        btn_king = (Button) findViewById(R.id.btn_king);
        btn_Queen = (Button) findViewById(R.id.btn_crown);
        btn_Double = (Button) findViewById(R.id.btn_green);
        btn_single = (Button) findViewById(R.id.btn_queen);

        btn_king.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openKing();
            }
        });

        btn_Queen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  openQueen();
            }
        });
        btn_Double.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDouble();
            }
        });
        btn_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSingle();
            }
        });
    }

    private void openSingle() {
        Intent intent = new Intent(this, SingleRoom.class);
        startActivity(intent);
    }

    private void openDouble() {
        Intent intent = new Intent(this, DoubleRoom.class);
        startActivity(intent);
    }

    private void openQueen() {
        Intent intent = new Intent(this, QueensRoom.class);
        startActivity(intent);
    }

//    private void openKing() {
//        Intent intent = new Intent(this, KingRoom.class);
//        startActivity(intent);
//    }
}