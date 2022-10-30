package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SnakesMenu extends AppCompatActivity {

    private ImageButton imageButton42;
    private ImageButton imageButton41;
    EditText editTextNumber13,editTextNumber14,editTextNumber15,editTextNumber16;
    private ImageButton imageButton34;
    private ImageButton imageButton38;
    private ImageButton imageButton39;
    private ImageButton imageButton40;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_snakes_menu);

        editTextNumber13 = findViewById(R.id.editTextNumber13);
        editTextNumber14 = findViewById(R.id.editTextNumber14);
        editTextNumber15 = findViewById(R.id.editTextNumber15);
        editTextNumber16 = findViewById(R.id.editTextNumber16);

        imageButton40 = (ImageButton) findViewById(R.id.imageButton40);
        imageButton40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber16.getText().toString())){
                    editTextNumber16.setError("Quantity is Compulsory");
                    return;
                }
                else{
                    openCartFood();
                }
            }
        });

        imageButton39 = (ImageButton) findViewById(R.id.imageButton39);
        imageButton39.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber15.getText().toString())){
                    editTextNumber15.setError("Quantity is Compulsory");
                    return;
                }
                else{
                    openCartFood();
                }
            }
        });

        imageButton38 = (ImageButton) findViewById(R.id.imageButton38);
        imageButton38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber14.getText().toString())){
                    editTextNumber14.setError("Quantity is Compulsory");
                    return;
                }
                else{
                    openCartFood();
                }
            }
        });

        imageButton34 = (ImageButton) findViewById(R.id.imageButton34);
        imageButton34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber13.getText().toString())){
                    editTextNumber13.setError("Quantity is Compulsory");
                    return;
                }
                else{
                    openCartFood();
                }
            }
        });

        imageButton41 = (ImageButton) findViewById(R.id.imageButton41);
        imageButton41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoodmainmenu();
            }
        });

        imageButton42 = (ImageButton) findViewById(R.id.imageButton42);
        imageButton42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartFood();
            }
        });
    }
    public void openFoodmainmenu(){
        Intent intent = new Intent(this,Foodmainmenu.class);
        startActivity(intent);
    }
    public void openCartFood(){
        Intent intent = new Intent(this,CartFood.class);
        startActivity(intent);
    }
}