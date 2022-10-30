package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SandwichMenu extends AppCompatActivity {

    private ImageButton imageButton8;
    private ImageButton imageButton2;
    EditText editTextNumber,editTextNumber3,editTextNumber2,editTextNumber4;
    private ImageButton imageButton9;
    private ImageButton imageButton14;
    private ImageButton imageButton13;
    private ImageButton imageButton15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sandwich_menu);

        editTextNumber = findViewById(R.id.editTextNumber);
        editTextNumber3 = findViewById(R.id.editTextNumber3);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        editTextNumber4 = findViewById(R.id.editTextNumber4);

        imageButton15 = (ImageButton) findViewById(R.id.imageButton15);
        imageButton15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber4.getText().toString())){
                    editTextNumber4.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton13 = (ImageButton) findViewById(R.id.imageButton13);
        imageButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber2.getText().toString())){
                    editTextNumber2.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton14 = (ImageButton) findViewById(R.id.imageButton14);
        imageButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber3.getText().toString())){
                    editTextNumber3.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton9 = (ImageButton) findViewById(R.id.imageButton9);
        imageButton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber.getText().toString())){
                    editTextNumber.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoodmainmenu();
            }
        });

        imageButton8 = (ImageButton) findViewById(R.id.imageButton8);
        imageButton8.setOnClickListener(new View.OnClickListener() {
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