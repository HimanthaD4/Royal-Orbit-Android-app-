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
public class UpdateKingHall extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update_king_hall);
        DatabaseReference myRef = database.getReference("Hotel Reservation");

        final EditText phoneKing = findViewById(R.id.phoneK);
        final EditText noSeatsKing = findViewById(R.id.noRoomK);
        final EditText inDateKing = findViewById(R.id.inDateK);
        final EditText dKing = findViewById(R.id.dK);

        final Button update = findViewById(R.id.btn_update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edit text to string value
                final String phoneTxtK = phoneKing.getText().toString().trim();
                final String seatsTxtK = noSeatsKing.getText().toString();
                final String inDateTxtK = inDateKing.getText().toString();
                final String dTxtK= dKing.getText().toString().trim();

                final Double seats = Double.parseDouble(seatsTxtK);
                final Double dates= Double.parseDouble(dTxtK);

                final Double price =  dates * 1000000;

                final String  pr = String.valueOf(price);
                final String type = "King Court";


                if (phoneTxtK.isEmpty()) {
                    phoneKing.setError("phone no. is Required");
                    phoneKing.requestFocus();
                    return;
                }
                if (seatsTxtK.isEmpty()) {
                    noSeatsKing.setError("No of Seats Required");
                    noSeatsKing.requestFocus();
                    return;
                }
                if (inDateTxtK.isEmpty()) {
                    inDateKing.setError("Check In Date Required");
                    inDateKing.requestFocus();
                    return;
                }
                if(dates < 0 ){
                    dKing.setError("No of Dates cannot be zero");
                    inDateKing.requestFocus();
                    return;
//
                }
                else{
                    myRef.child("king").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //send data to databse
                            myRef.child("king").child(phoneTxtK).child("No Of seats").setValue(seatsTxtK);
                            myRef.child("king").child(phoneTxtK).child("Check in date").setValue(inDateTxtK);
                            myRef.child("king").child(phoneTxtK).child("No of days").setValue(dTxtK);
                            myRef.child("king").child(phoneTxtK).child("price").setValue(price);

                            String DateFromDb = snapshot.child(phoneTxtK).child("Check in date").getValue(String.class);
                            String SeatsFromDb = snapshot.child(phoneTxtK).child("No of seats").getValue(String.class);
                            String noDaysFromDB = snapshot.child(phoneTxtK).child("No of days").getValue(String.class);

                            String date = inDateTxtK;
                            String Seats = seatsTxtK;
                            String days = dTxtK;




                            String priceK = pr;
                            String typeK = type;

                            Intent intent = new Intent(getApplicationContext(),ReserveHallDetailsKing.class);
//
//                            intent.putExtra("No of seats",SeatsFromDb);
//                            intent.putExtra("Check in date",DateFromDb);
//                            intent.putExtra("No of days",noDaysFromDB);

                            intent.putExtra("No of seats",Seats);
                            intent.putExtra("Check in date",date);
                            intent.putExtra("No of days",days);

                            intent.putExtra("price",priceK);
                            intent.putExtra("type",typeK);
                            Toast.makeText(UpdateKingHall.this, "Update hall details Succesfully", Toast.LENGTH_LONG).show();

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
