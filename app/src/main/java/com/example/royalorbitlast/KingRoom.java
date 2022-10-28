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

public class KingRoom extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_king_room);


        DatabaseReference myRef = database.getReference("Room Reservations");

        final EditText phoneK = findViewById(R.id.phoneK);
        final EditText noRoomK = findViewById(R.id.noRoomK);
        final EditText inDateK = findViewById(R.id.inDateK);
//        final EditText outDateK = findViewById(R.id.outDateDouble);
        final EditText dK = findViewById(R.id.dK);



        final Button confirmDouble = findViewById(R.id.confirmK);

        confirmDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                //get data from edit text to string value
                final String phoneTxtK = phoneK.getText().toString();
                final String noRoomTxtK = noRoomK.getText().toString();
                final String inDateQueenTxtK = inDateK.getText().toString();
//                final String outDateQueenTxtK= outDateDouble.getText().toString();
                final String dQueenTxtK= dK.getText().toString();
//
//
                final Double Rooms= Double.parseDouble(noRoomTxtK);
                final Double dates= Double.parseDouble(dQueenTxtK);
                final Double in= Double.parseDouble(inDateQueenTxtK);
//                final Double out= Double.parseDouble(outDateQueenTxtD);

                final Double price = Rooms * dates * 20000;

                if (phoneTxtK.isEmpty()) {
                    phoneK.setError("phone no. is Required");
                    phoneK.requestFocus();
                    return;
                }

                if (noRoomTxtK.isEmpty()) {
                    noRoomK.setError("No of Rooms Required");
                    noRoomK.requestFocus();
                    return;
                }

//                if (!Patterns.(00/00/0000).matcher(inDateQueen).matches()) {
//                    inDateQueen.setError("please provide valid email");
//                    inDateQueen.requestFocus();
//                    return;
//                }

                if (inDateQueenTxtK.isEmpty()) {
                    inDateK.setError("Check In Date Required");
                    inDateK.requestFocus();
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
                    dK.setError("No of Dates cannot be zero");
                    inDateK.requestFocus();
                    return;
                }

//                if(dates > 10 ){
//                    dQueen.setError("Maximum Number Of Booking Date is 10");
//                    dQueen.requestFocus();
//                    return;
//                }

                if(Rooms > 10 ){
                    noRoomK.setError("Maximum Number Of Booking Room is 10");
                    noRoomK.requestFocus();
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


    myRef.child("king").addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            //send data to databse
            myRef.child("king").child(phoneTxtK).child("No Of Rooms").setValue(noRoomTxtK);
            myRef.child("king").child(phoneTxtK).child("Check in date").setValue(inDateQueenTxtK);
//                            myRef.child("Double").child(phoneTxtD).child("Check out date").setValue(outDateQueenTxtD);
            myRef.child("king").child(phoneTxtK).child("No of days").setValue(dQueenTxtK);
            myRef.child("king").child(phoneTxtK).child("price").setValue(price);


            Toast.makeText(KingRoom.this, "Rooms Booked Succesful", Toast.LENGTH_LONG).show();
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