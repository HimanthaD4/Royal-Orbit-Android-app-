package com.example.royalorbit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.royalorbit.activities.event.EventReservation;

import com.example.royalorbit.utils.UserUtils;

public class MainMenu extends AppCompatActivity {
    UserUtils userUtils = new UserUtils();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }


    @Override
    protected void onStart() {
        super.onStart();
        userUtils.getAuthUser();
//        getAuthUser();
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

//    public void roomRes(View view) {
//        Intent i = new Intent(getApplicationContext(), RoomReservation.class);
//        startActivity(i);
//    }

//    public void hallRes(View view) {
//        Intent i = new Intent(getApplicationContext(), HallReservation.class);
//        startActivity(i);
//    }

//    public void foodRes(View view) {
//        Intent i = new Intent(getApplicationContext(), FoodReservation.class);
//        startActivity(i);
//    }

    public void eventRes(View view) {
        Intent i = new Intent(getApplicationContext(), EventReservation.class);
        startActivity(i);
    }

    public void goToProfile(View view) {
        Intent i = new Intent(getApplicationContext(), UserProfile.class);
        startActivity(i);
    }
}