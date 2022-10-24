package com.example.royalorbitlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {


    DatabaseReference databaseReference  = FirebaseDatabase.getInstance().getReferenceFromUrl("https://royal-orbit-last-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);



        final Button loginone = findViewById(R.id.loginone);
        final EditText phone = findViewById(R.id.phone);
        final EditText password = findViewById(R.id.password);

        loginone.setOnClickListener(new View.OnClickListener() {

            //create objects in database to acces firebase

            @Override
            public void onClick(View view) {

                final String phoneText = phone.getText().toString();
                final String passwordText = password.getText().toString();


                if(phoneText.isEmpty()){
                    phone.setError("Enter Your phone number");
                    phone.requestFocus();
                    return;
                }
                if(passwordText.isEmpty()){
                    password.setError("Enter Your email");
                    password.requestFocus();
                    return;
                }



                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check mobile number is exist
                            if(snapshot.hasChild(phoneText)){

                                //mobile exist in firebase
                                final String getpassword = snapshot.child(phoneText).child("Password").getValue(String.class);
                                if(getpassword.equals(passwordText)){


                                    //open menu
                                    Toast.makeText(login.this, "Welcome back to the Royal Ordit", Toast.LENGTH_SHORT).show();

                                    openMenu();
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(login.this, "OOPS! TRY AGAIN, WRONG EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
                                }

                            }
                            else{
                                Toast.makeText(login.this, "OOPS! TRY AGAIN, WRONG EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }




            }
        });

    }

    public void openMenu( ){

        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}