package com.example.royalorbitlast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateQueenCourt extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update_queen_hall);
        DatabaseReference myRef = database.getReference("Hotel Reservation");

        final EditText phoneQueen = findViewById(R.id.phoneK);
        final EditText noSeatsQueen = findViewById(R.id.noRoomK);
        final EditText inDateQueen = findViewById(R.id.inDateK);
        final EditText dQueen = findViewById(R.id.dK);
        final Button update = findViewById(R.id.btn_update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edit text to string value
                final String phoneTxtQ = phoneQueen.getText().toString().trim();
                final String seatsTxtQ = noSeatsQueen.getText().toString();
                final String inDateTxtQ = inDateQueen.getText().toString();
                final String dTxtQ= dQueen.getText().toString().trim();

                final Double seats = Double.parseDouble(seatsTxtQ);
                final Double dates= Double.parseDouble(dTxtQ);

                final Double price =  dates * 500000;

                final String  pr = String.valueOf(price);
                final String type = "Queen Court";

                if (phoneTxtQ.isEmpty()) {
                    phoneQueen.setError("phone no. is Required");
                    phoneQueen.requestFocus();
                    return;
                }
                if (seatsTxtQ.isEmpty()) {
                    noSeatsQueen.setError("No of Seats Required");
                    noSeatsQueen.requestFocus();
                    return;
                }
                if (inDateTxtQ.isEmpty()) {
                    inDateQueen.setError("Check In Date Required");
                    inDateQueen.requestFocus();
                    return;
                }
                if(dates < 0 ){
                    dQueen.setError("No of Dates cannot be zero");
                    inDateQueen.requestFocus();
                    return;

                }
                else{
                    myRef.child("queen").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //send data to databse
                            myRef.child("queen").child(phoneTxtQ).child("No Of seats").setValue(seatsTxtQ);
                            myRef.child("queen").child(phoneTxtQ).child("Check in date").setValue(inDateTxtQ);
                            myRef.child("queen").child(phoneTxtQ).child("No of days").setValue(dTxtQ);
                            myRef.child("queen").child(phoneTxtQ).child("price").setValue(price);

                            String DateFromDb = snapshot.child(phoneTxtQ).child("Check in date").getValue(String.class);
                            String SeatsFromDb = snapshot.child(phoneTxtQ).child("No of seats").getValue(String.class);
                            String noDaysFromDB = snapshot.child(phoneTxtQ).child("No of days").getValue(String.class);
                            String date = inDateTxtQ;
                            String Seats = seatsTxtQ;
                            String days = dTxtQ;

                            String priceQ = pr;
                            String typeQ= type;

                            Intent intent = new Intent(getApplicationContext(),ReservedHallDetailsQueen.class);
                            intent.putExtra("No of seats",Seats);
                            intent.putExtra("Check in date",date);
                            intent.putExtra("No of days",days);

                            intent.putExtra("price",priceQ);
                            intent.putExtra("type",typeQ);
                            Toast.makeText(UpdateQueenCourt.this, "Update hall details Succesfully", Toast.LENGTH_LONG).show();

                            startActivity(intent);
                            finish();

                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }
        });
    }
}