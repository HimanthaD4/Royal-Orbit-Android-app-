package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class NoodlesMenu extends AppCompatActivity {

    private ImageButton imageButton36;
    private ImageButton imageButton35;
    EditText editTextNumber9,editTextNumber10,editTextNumber11,editTextNumber12;
    private ImageButton imageButton16;
    private ImageButton imageButton18;
    private ImageButton imageButton32;
    private ImageButton imageButton33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_noodles_menu);

        editTextNumber9 = findViewById(R.id.editTextNumber9);
        editTextNumber10 = findViewById(R.id.editTextNumber10);
        editTextNumber11 = findViewById(R.id.editTextNumber11);
        editTextNumber12 = findViewById(R.id.editTextNumber12);

        imageButton33 = (ImageButton) findViewById(R.id.imageButton33);
        imageButton33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber12.getText().toString())){
                    editTextNumber12.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton32 = (ImageButton) findViewById(R.id.imageButton32);
        imageButton32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber11.getText().toString())){
                    editTextNumber11.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton18 = (ImageButton) findViewById(R.id.imageButton18);
        imageButton18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber10.getText().toString())){
                    editTextNumber10.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton16 = (ImageButton) findViewById(R.id.imageButton16);
        imageButton16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber9.getText().toString())){
                    editTextNumber9.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton35 = (ImageButton) findViewById(R.id.imageButton35);
        imageButton35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoodmainmenu();
            }
        });

        imageButton36 = (ImageButton) findViewById(R.id.imageButton36);
        imageButton36.setOnClickListener(new View.OnClickListener() {
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