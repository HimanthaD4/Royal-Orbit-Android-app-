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

public class DoubleRoom extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_double_room2);

DatabaseReference myRef = database.getReference("Room Reservations");

        final EditText phoneD = findViewById(R.id.phoneD);
        final EditText noRoomD = findViewById(R.id.noRoomD);
        final EditText inDateD = findViewById(R.id.inDateD);
//        final EditText outDateDouble = findViewById(R.id.outDateDouble);
        final EditText dD = findViewById(R.id.dD);



        final Button confirmDouble = findViewById(R.id.confirmD);

        confirmDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //get data from edit text to string value
                final String phoneTxtD = phoneD.getText().toString();
                final String noRoomTxtD = noRoomD.getText().toString();
                final String inDateQueenTxtD = inDateD.getText().toString();
//                final String outDateQueenTxtD= outDateDouble.getText().toString();
                final String dQueenTxtD= dD.getText().toString();
//
//
                final Double Rooms= Double.parseDouble(noRoomTxtD);
                final Double dates= Double.parseDouble(dQueenTxtD);
                final Double in= Double.parseDouble(inDateQueenTxtD);
//                final Double out= Double.parseDouble(outDateQueenTxtD);

                final Double price = Rooms * dates * 10000;

                if (phoneTxtD.isEmpty()) {
                    phoneD.setError("phone no. is Required");
                    phoneD.requestFocus();
                    return;
                }

                if (noRoomTxtD.isEmpty()) {
                    noRoomD.setError("No of Rooms Required");
                    noRoomD.requestFocus();
                    return;
                }

//                if (!Patterns.(00/00/0000).matcher(inDateQueen).matches()) {
//                    inDateQueen.setError("please provide valid email");
//                    inDateQueen.requestFocus();
//                    return;
//                }

                if (inDateQueenTxtD.isEmpty()) {
                    inDateD.setError("Check In Date Required");
                    inDateD.requestFocus();
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
                    dD.setError("No of Dates cannot be zero");
                    inDateD.requestFocus();
                    return;
                }

//                if(dates > 10 ){
//                    dQueen.setError("Maximum Number Of Booking Date is 10");
//                    dQueen.requestFocus();
//                    return;
//                }

                if(Rooms > 10 ){
                    noRoomD.setError("Maximum Number Of Booking Room is 10");
                    noRoomD.requestFocus();
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

                    myRef.child("Double").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //send data to databse
                            myRef.child("Double").child(phoneTxtD).child("No Of Rooms").setValue(noRoomTxtD);
                            myRef.child("Double").child(phoneTxtD).child("Check in date").setValue(inDateQueenTxtD);
//                            myRef.child("Double").child(phoneTxtD).child("Check out date").setValue(outDateQueenTxtD);
                            myRef.child("Double").child(phoneTxtD).child("No of days").setValue(dQueenTxtD);
                            myRef.child("Double").child(phoneTxtD).child("price").setValue(price);


                            Toast.makeText(DoubleRoom.this, "Rooms Booked Succesful", Toast.LENGTH_LONG).show();
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