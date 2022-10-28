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

public class SingleRoom extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_single_room);

        DatabaseReference myRef = database.getReference("Room Reservations");

        final EditText phoneS = findViewById(R.id.phoneS);
        final EditText noRoomS = findViewById(R.id.noRoomS);
        final EditText inDateS = findViewById(R.id.inDateS);
//        final EditText outDateS = findViewById(R.id.outDateDouble);
        final EditText dS = findViewById(R.id.dS);



        final Button confirmDouble = findViewById(R.id.confirmS);

        confirmDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //get data from edit text to string value
                final String phoneTxtS = phoneS.getText().toString();
                final String noRoomTxtS = noRoomS.getText().toString();
                final String inDateQueenTxtS = inDateS.getText().toString();
//                final String outDateQueenTxtD= outDateDouble.getText().toString();
                final String dQueenTxtS= dS.getText().toString();
//
//
                final Double Rooms= Double.parseDouble(noRoomTxtS);
                final Double dates= Double.parseDouble(dQueenTxtS);
                final Double in= Double.parseDouble(inDateQueenTxtS);
//                final Double out= Double.parseDouble(outDateQueenTxtD);

                final Double price = Rooms * dates * 5000;

                if (phoneTxtS.isEmpty()) {
                    phoneS.setError("phone no. is Required");
                    phoneS.requestFocus();
                    return;
                }

                if (noRoomTxtS.isEmpty()) {
                    noRoomS.setError("No of Rooms Required");
                    noRoomS.requestFocus();
                    return;
                }

//                if (!Patterns.(00/00/0000).matcher(inDateQueen).matches()) {
//                    inDateQueen.setError("please provide valid email");
//                    inDateQueen.requestFocus();
//                    return;
//                }

                if (inDateQueenTxtS.isEmpty()) {
                    inDateS.setError("Check In Date Required");
                    inDateS.requestFocus();
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
                    dS.setError("No of Dates cannot be zero");
                    inDateS.requestFocus();
                    return;
                }

//                if(dates > 10 ){
//                    dQueen.setError("Maximum Number Of Booking Date is 10");
//                    dQueen.requestFocus();
//                    return;
//                }

                if(Rooms > 10 ){
                    noRoomS.setError("Maximum Number Of Booking Room is 10");
                    noRoomS.requestFocus();
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


                else{


                    myRef.child("Single").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {



                            //send data to databse
                            myRef.child("Single").child(phoneTxtS).child("No Of Rooms").setValue(noRoomTxtS);
                            myRef.child("Single").child(phoneTxtS).child("Check in date").setValue(inDateQueenTxtS);
//                            myRef.child("Double").child(phoneTxtD).child("Check out date").setValue(outDateQueenTxtD);
                            myRef.child("Single").child(phoneTxtS).child("No of days").setValue(dQueenTxtS);
                            myRef.child("Single").child(phoneTxtS).child("price").setValue(price);


                            Toast.makeText(SingleRoom.this, "Rooms Booked Succesful", Toast.LENGTH_LONG).show();
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