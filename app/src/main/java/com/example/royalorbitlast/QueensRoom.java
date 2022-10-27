package com.example.royalorbitlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QueensRoom extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_queens_room);

        DatabaseReference myRef = database.getReference("Room Reservations");

        final EditText phoneQueen = findViewById(R.id.phoneQueen);
        final EditText noRoomQueen = findViewById(R.id.noRoomQueen);
        final EditText inDateQueen = findViewById(R.id.inDateQueen);
//        final EditText outDateQueen = findViewById(R.id.outDateQueen);
        final EditText dQueen = findViewById(R.id.dQueen);

        final Button confirmQ = findViewById(R.id.confirmQ);

        confirmQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edit text to string value
                final String phoneTxtQ = phoneQueen.getText().toString();
                final String noRoomTxtQ = noRoomQueen.getText().toString();
                final String inDateQueenTxtQ = inDateQueen.getText().toString();
//                final String outDateQueenTxtQ= outDateQueen.getText().toString();
                final String dQueenTxtQ= dQueen.getText().toString();
//
//
                final Double Rooms= Double.parseDouble(noRoomTxtQ);
                final Double dates= Double.parseDouble(dQueenTxtQ);
                final Double in= Double.parseDouble(inDateQueenTxtQ);
//                final Double out= Double.parseDouble(outDateQueenTxtQ);

                final Double price = Rooms * dates * 15000;

                if (phoneTxtQ.isEmpty()) {
                    phoneQueen.setError("phone no. is Required");
                    phoneQueen.requestFocus();
                    return;
                }

               if (noRoomTxtQ.isEmpty()) {
                    noRoomQueen.setError("No of Rooms Required");
                    noRoomQueen.requestFocus();
                    return;
                }

//                if (!Patterns.(00/00/0000).matcher(inDateQueen).matches()) {
//                    inDateQueen.setError("please provide valid email");
//                    inDateQueen.requestFocus();
//                    return;
//                }

              if (inDateQueenTxtQ.isEmpty()) {
                   inDateQueen.setError("Check In Date Required");
                    inDateQueen.requestFocus();
                    return;
                }

//                if (inDateQueenTxtQ.length() < 5) {
//                    inDateQueen.setError("Min password length is 5 characters!");
//                }
//
//                if (outDateQueenTxtQ.isEmpty()) {
//                    outDateQueen.setError("Check Out Date is Required");
//                    outDateQueen.requestFocus();
//                    return;
//
//                }
//                if(in>out){
//                    outDateQueen.setError("Set Valid Dates");
//                    inDateQueen.setError("Set valid Dates");
//                    outDateQueen.requestFocus();
//                    inDateQueen.requestFocus();
//                }

                if(dates < 0 ){
                    dQueen.setError("No of Dates cannot be zero");
                    inDateQueen.requestFocus();
                    return;
                }

//                if(dates > 10 ){
//                    dQueen.setError("Maximum Number Of Booking Date is 10");
//                    dQueen.requestFocus();
//                    return;
//                }

                if(Rooms > 10 ){
                    noRoomQueen.setError("Maximum Number Of Booking Room is 10");
                    noRoomQueen.requestFocus();
                    return;
                }
//                if(out > 2024.1 ){
//                    outDateQueen.setError("You can Only Book for 2023");
//                    outDateQueen.requestFocus();
//                }
//                if(in < 2022.9 ){
//                    inDateQueen.setError("Enter valid date");
//                    inDateQueen.requestFocus();
//                    return;
//                }



else {
                    myRef.child("Queen").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //send data to databse
                            myRef.child("Queen").child(phoneTxtQ).child("No Of Rooms").setValue(noRoomTxtQ);
                            myRef.child("Queen").child(phoneTxtQ).child("Check in date").setValue(inDateQueenTxtQ);
//                            myRef.child("Queen").child(phoneTxtQ).child("Check out date").setValue(outDateQueenTxtQ);
                            myRef.child("Queen").child(phoneTxtQ).child("No of days").setValue(dQueenTxtQ);
                            myRef.child("Queen").child(phoneTxtQ).child("price").setValue(price);


                            Toast.makeText(QueensRoom.this, "Rooms Booked Succesful", Toast.LENGTH_LONG).show();
                            openRoomReservation();
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

    private void openRoomReservation() {
        Intent intent = new Intent(this, RoomReservation.class);
        startActivity(intent);
    }
}