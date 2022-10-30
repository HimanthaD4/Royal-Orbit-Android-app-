package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Foodmainmenu extends AppCompatActivity {

    private ImageButton imageButton4;
    private ImageButton imageButton5;
    private ImageButton imageButton6;
    private ImageButton imageButton;
    private ImageButton imageButton7;
    private ImageButton imageButton10;
    private ImageButton imageButton11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_foodmainmenu);

        imageButton11 = (ImageButton) findViewById(R.id.imageButton11);
        imageButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartFood();
            }
        });

        imageButton10 = (ImageButton) findViewById(R.id.imageButton10);
        imageButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMenu();
            }
        });

        imageButton7 = (ImageButton) findViewById(R.id.imageButton7);
        imageButton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSnakesMenu();
            }
        });

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSandwichMenu();
            }
        });

        imageButton6 = (ImageButton) findViewById(R.id.imageButton6);
        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNoodlesMenu();
            }
        });

        imageButton5 = (ImageButton)  findViewById(R.id.imageButton5);
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPizzaMenu();
            }
        });

        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBurgerMenu();
            }
        });
    }
    public void openCartFood(){
        Intent intent = new Intent(this,CartFood.class);
        startActivity(intent);
    }
    public void openMenu(){
        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);
    }
    public void openSnakesMenu(){
        Intent intent = new Intent(this,SnakesMenu.class);
        startActivity(intent);
    }
    public void openSandwichMenu(){
        Intent intent = new Intent(this,SandwichMenu.class);
        startActivity(intent);
    }
    public void openNoodlesMenu(){
        Intent intent = new Intent(this,NoodlesMenu.class);
        startActivity(intent);
    }
    public void openPizzaMenu(){
        Intent intent = new Intent(this,PizzaMenu.class);
        startActivity(intent);
    }
    public void openBurgerMenu(){
        Intent intent = new Intent(this,BurgerMenu.class);
        startActivity(intent);
        }
}