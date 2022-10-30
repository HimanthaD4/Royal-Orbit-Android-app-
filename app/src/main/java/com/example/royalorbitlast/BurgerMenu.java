package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BurgerMenu extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private ImageButton imageButton25;
    private ImageButton imageButton24;
    EditText editTextTextPersonName2,editTextTextPersonName6,editTextTextPersonName8,editTextTextPersonName9;
    private ImageButton imageButton19;
    private ImageButton imageButton21;
    private ImageButton imageButton22;
    private ImageButton imageButton23;

    private boolean validateNumber(String input){
        Pattern p = Pattern.compile("1-5");
        Matcher m = p.matcher(input);
        return m.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_burger_menu);

        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);
        editTextTextPersonName8 = findViewById(R.id.editTextTextPersonName8);
        editTextTextPersonName9 = findViewById(R.id.editTextTextPersonName9);


        imageButton23 = (ImageButton) findViewById(R.id.imageButton23);
        imageButton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextTextPersonName9.getText().toString())){
                    editTextTextPersonName9.setError("Quantity is Compulsory");
                    return;
                }
                else if(editTextTextPersonName2.length()==1-5){
                    editTextTextPersonName2.requestFocus();
                    editTextTextPersonName2.setError("Maximum Quantity is 10");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton22 = (ImageButton) findViewById(R.id.imageButton22);
        imageButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextTextPersonName8.getText().toString())){
                    editTextTextPersonName8.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton21 = (ImageButton)findViewById(R.id.imageButton21);
        imageButton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextTextPersonName6.getText().toString())){
                    editTextTextPersonName6.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton19 = (ImageButton) findViewById(R.id.imageButton19);
        imageButton19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(editTextTextPersonName2.getText().toString())){
                    editTextTextPersonName2.setError("Quantity is Compulsory");
                    return;
                }
                else{
//                    openCartFood();
                }
            }
        });

        imageButton24 = (ImageButton) findViewById(R.id.imageButton24);
        imageButton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFoodmainmenu();
            }
        });

        imageButton25 = (ImageButton) findViewById(R.id.imageButton25);
        imageButton25.setOnClickListener(new View.OnClickListener() {
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