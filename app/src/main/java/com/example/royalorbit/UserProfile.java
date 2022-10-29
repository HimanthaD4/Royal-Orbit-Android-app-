package com.example.royalorbit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfile extends AppCompatActivity {


    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }


    @Override
    protected void onStart() {
        super.onStart();
        getAuthUser();
    }

    private void getAuthUser(){
        String userId = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                TextView firstName = (TextView) findViewById(R.id.first_name);
                TextView lastName = (TextView) findViewById(R.id.last_name);
                TextView email = (TextView) findViewById(R.id.email);
                TextView phoneNumber = (TextView) findViewById(R.id.phone_number);
                TextView age = (TextView) findViewById(R.id.age);
                TextView address = (TextView) findViewById(R.id.address);
                TextView nic = (TextView) findViewById(R.id.nic);

                firstName.setText(documentSnapshot.getString("firstName"));
                lastName.setText(documentSnapshot.getString("lastName"));
                address.setText(documentSnapshot.getString("address"));
                email.setText(documentSnapshot.getString("email"));
                age.setText(documentSnapshot.getString("age"));
                phoneNumber.setText(documentSnapshot.getString("phoneNumber"));
                nic.setText(documentSnapshot.getString("nic"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(i);
    }
    public void goToEditProfile(View view) {
        Intent i = new Intent(getApplicationContext(), EditProfile.class);
        startActivity(i);
    }

    public void logOut(View view) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}