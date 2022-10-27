package com.example.royalorbitlast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn_register;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        btn_register = (Button)findViewById(R.id.btn_register);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                openregister();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                openlogin();
            }
        });
    }

    private void openlogin() {

        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }


    public void openregister(){
        Intent intent =  new Intent(this, Register.class);
        startActivity(intent);
    }
}