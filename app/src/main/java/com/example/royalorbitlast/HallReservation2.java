package com.example.royalorbitlast;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class HallReservation2 extends AppCompatActivity {

    private Button btn_king;
    private Button btn_queen;
    private Button btn_crown;
    private Button btn_green;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_hall_reservation3);

        btn_king = (Button) findViewById(R.id.btn_king);
        btn_queen = (Button) findViewById(R.id.btn_queen);
        btn_crown = (Button) findViewById(R.id.btn_crown);
        btn_green = (Button) findViewById(R.id.btn_green);

        btn_king.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openKing();
            }
        });

        btn_queen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQueen();
            }
        });
        btn_crown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCrown();
            }
        });
        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGreen();
            }
        });
    }


    private void openQueen() {
        Intent intent = new Intent(this, QueenCourt.class);
        startActivity(intent);
    }
    private void openCrown() {
        Intent intent = new Intent(this, CrownCourt.class);
        startActivity(intent);
    }
    private void openGreen() {
        Intent intent = new Intent(this, GreenCourt.class);
        startActivity(intent);
    }

    private void openKing() {
        Intent intent = new Intent(this, KingCourt.class);
        startActivity(intent);
    }
}