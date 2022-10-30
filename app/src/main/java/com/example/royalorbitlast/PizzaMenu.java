package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class PizzaMenu extends AppCompatActivity {

    private ImageButton imageButton30;
    private ImageButton imageButton29;
    EditText editTextNumber5,editTextNumber6,editTextNumber7,editTextNumber8;
    private ImageButton imageButton20;
    private ImageButton imageButton17;
    private ImageButton imageButton27;
    private ImageButton imageButton28;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pizza_menu);

        editTextNumber5 = findViewById(R.id.editTextNumber5);
        editTextNumber6 = findViewById(R.id.editTextNumber6);
        editTextNumber7 = findViewById(R.id.editTextNumber7);
        editTextNumber8 = findViewById(R.id.editTextNumber8);

        imageButton28 = (ImageButton) findViewById(R.id.imageButton28);
        imageButton28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber8.getText().toString())){
                    editTextNumber8.setError("Quantity is Compulsory");
                    return;
                }
                else{
                    openCartFood();
                }
            }
        });

        imageButton27 = (ImageButton) findViewById(R.id.imageButton27);
        imageButton27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber7.getText().toString())){
                    editTextNumber7.setError("Quantity is Compulsory");
                    return;
                }
                else{
                    openCartFood();
                }
            }
        });

        imageButton17 = (ImageButton) findViewById(R.id.imageButton17);
        imageButton17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber6.getText().toString())){
                    editTextNumber6.setError("Quantity is Compulsory");
                    return;
                }
                else{
                    openCartFood();
                }
            }
        });

        imageButton20 = (ImageButton) findViewById(R.id.imageButton20);
        imageButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextNumber5.getText().toString())){
                    editTextNumber5.setError("Quantity is Compulsory");
                    return;
                }
                else{
                    openCartFood();
                }
            }
        });

        imageButton29 = (ImageButton) findViewById(R.id.imageButton29);
        imageButton29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoodmainmenu();
            }
        });

        imageButton30 = (ImageButton) findViewById(R.id.imageButton30);
        imageButton30.setOnClickListener(new View.OnClickListener() {
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