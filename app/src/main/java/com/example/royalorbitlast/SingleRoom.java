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

        final EditText phoneDouble = findViewById(R.id.phoneDouble);
        final EditText noRoomDouble = findViewById(R.id.noRoomDouble);
        final EditText inDateDouble = findViewById(R.id.inDateDouble);
//        final EditText outDateDouble = findViewById(R.id.outDateDouble);
        final EditText dDouble = findViewById(R.id.dDouble);



        final Button confirmDouble = findViewById(R.id.confirmDouble);

        confirmDouble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //get data from edit text to string value
                final String phoneTxtD = phoneDouble.getText().toString();
                final String noRoomTxtD = noRoomDouble.getText().toString();
                final String inDateQueenTxtD = inDateDouble.getText().toString();
//                final String outDateQueenTxtD= outDateDouble.getText().toString();
                final String dQueenTxtD= dDouble.getText().toString();
//
//
                final Double Rooms= Double.parseDouble(noRoomTxtD);
                final Double dates= Double.parseDouble(dQueenTxtD);
                final Double in= Double.parseDouble(inDateQueenTxtD);
//                final Double out= Double.parseDouble(outDateQueenTxtD);

                final Double price = Rooms * dates * 5000;

                if (phoneTxtD.isEmpty()) {
                    phoneDouble.setError("phone no. is Required");
                    phoneDouble.requestFocus();
                    return;
                }

                if (noRoomTxtD.isEmpty()) {
                    noRoomDouble.setError("No of Rooms Required");
                    noRoomDouble.requestFocus();
                    return;
                }

//                if (!Patterns.(00/00/0000).matcher(inDateQueen).matches()) {
//                    inDateQueen.setError("please provide valid email");
//                    inDateQueen.requestFocus();
//                    return;
//                }

                if (inDateQueenTxtD.isEmpty()) {
                    inDateDouble.setError("Check In Date Required");
                    inDateDouble.requestFocus();
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
                    dDouble.setError("No of Dates cannot be zero");
                    inDateDouble.requestFocus();
                    return;
                }

//                if(dates > 10 ){
//                    dQueen.setError("Maximum Number Of Booking Date is 10");
//                    dQueen.requestFocus();
//                    return;
//                }

                if(Rooms > 10 ){
                    noRoomDouble.setError("Maximum Number Of Booking Room is 10");
                    noRoomDouble.requestFocus();
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
                            myRef.child("Single").child(phoneTxtD).child("No Of Rooms").setValue(noRoomTxtD);
                            myRef.child("Single").child(phoneTxtD).child("Check in date").setValue(inDateQueenTxtD);
//                            myRef.child("Double").child(phoneTxtD).child("Check out date").setValue(outDateQueenTxtD);
                            myRef.child("Single").child(phoneTxtD).child("No of days").setValue(dQueenTxtD);
                            myRef.child("Single").child(phoneTxtD).child("price").setValue(price);


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