package com.example.royalorbitlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class Register extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://royal-orbit-last-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().hide();

        setContentView(R.layout.activity_register);


        final EditText name = findViewById(R.id.name);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText phone = findViewById(R.id.phone);

//        final Button registerone = findViewById(R.id.registerone);
        final Button regi = findViewById(R.id.regi);

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edit text to string value
                final String nameTxt = name.getText().toString();
                final String emailTxt = email.getText().toString();
                final String passwordTxt = password.getText().toString();
                final String phoneTxt = phone.getText().toString();


                if (nameTxt.isEmpty()) {
                    name.setError("Name is required");
                    name.requestFocus();
                    return;
                }

                if (emailTxt.isEmpty()) {
                    email.setError("email is required");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(emailTxt).matches()) {
                    email.setError("please provide valid email");
                    email.requestFocus();
                    return;
                }

                if (passwordTxt.isEmpty()) {
                    password.setError("password is required");
                    password.requestFocus();
                    return;
                }

                if (password.length() < 5) {
                    password.setError("Min password length is 5 characters!");
                }

                if (phoneTxt.isEmpty()) {
                    phone.setError("phone no.  is required");
                    phone.requestFocus();
                    return;
                }



//                //check user enter all data
//                if(nameTxt.isEmpty() || emailTxt.isEmpty() || passwordTxt.isEmpty() || phoneTxt.isEmpty() ){
//                    Toast.makeText(Register.this, "please fill all the fields", Toast.LENGTH_SHORT).show();
//                }

//                password match check part
//                else if(!passwordTxt.equals(conpasswordTxt)){
//
//                    Toast.makeText(Register.this, "pasword not matching", Toast.LENGTH_SHORT).show();
//                }

                else{

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check mail is not entered before
                            if(snapshot.hasChild(phoneTxt)){
                                Toast.makeText(Register.this, "phone number already entered", Toast.LENGTH_SHORT).show();
                            }

                            else {
                                //send data to databse
                                databaseReference.child("users").child(phoneTxt).child("Name").setValue(nameTxt);
                                databaseReference.child("users").child(phoneTxt).child("Email").setValue(emailTxt);
                                databaseReference.child("users").child(phoneTxt).child("Password").setValue(passwordTxt);


                                Toast.makeText(Register.this, "User registered succesfully", Toast.LENGTH_SHORT).show();
                                openlogin();
                                finish();
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
    private void openlogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}






