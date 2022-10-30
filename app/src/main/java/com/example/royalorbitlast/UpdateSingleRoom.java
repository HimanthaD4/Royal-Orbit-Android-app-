package com.example.royalorbitlast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateSingleRoom extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update_single_room);

        DatabaseReference myRef = database.getReference("Room Reservations");


        final EditText phoneSingle = findViewById(R.id.phoneSingle);
        final EditText noRoomSingle = findViewById(R.id.noOfSingle);
        final EditText inDateSingle = findViewById(R.id.inDateSingle);
        final EditText dSingle = findViewById(R.id.dSingle);

        final Button confirmSingle = findViewById(R.id.btn_update);

        confirmSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edit text to string value
                final String phoneTxtS = phoneSingle.getText().toString().trim();
                final String noRoomTxtS = noRoomSingle.getText().toString();
                final String inDateTxtS = inDateSingle.getText().toString();
                final String dTxtS= dSingle.getText().toString().trim();

                final Double Rooms= Double.parseDouble(noRoomTxtS);
                final Double dates= Double.parseDouble(dTxtS);

                final Double price = Rooms * dates * 5000;
                final String  pr = String.valueOf(price);
                final String type = "Single Room";


                if (phoneTxtS.isEmpty()) {
                    phoneSingle.setError("phone no. is Required");
                    phoneSingle.requestFocus();
                    return;
                }
                if (noRoomTxtS.isEmpty()) {
                    noRoomSingle.setError("No of Rooms Required");
                    noRoomSingle.requestFocus();
                    return;
                }
                if (inDateTxtS.isEmpty()) {
                    inDateSingle.setError("Check In Date Required");
                    inDateSingle.requestFocus();
                    return;
                }
                if(dates < 0 ){
                    dSingle.setError("No of Dates cannot be zero");
                    inDateSingle.requestFocus();
                    return;
                }
                if(Rooms > 10 ){
                    noRoomSingle.setError("Maximum Number Of Booking Room is 10");
                    noRoomSingle.requestFocus();
                    return;
                }
                else{
                    myRef.child("single").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //send data to databse
                            myRef.child("single").child(phoneTxtS).child("No Of Rooms").setValue(noRoomTxtS);
                            myRef.child("single").child(phoneTxtS).child("Check in date").setValue(inDateTxtS);
                            myRef.child("single").child(phoneTxtS).child("No of days").setValue(dTxtS);
                            myRef.child("single").child(phoneTxtS).child("price").setValue(price);

                            String DateFromDb = snapshot.child(phoneTxtS).child("Check in date").getValue(String.class);
                            String RoomFromDb = snapshot.child(phoneTxtS).child("No Of Rooms").getValue(String.class);
                            String noDaysFromDB = snapshot.child(phoneTxtS).child("No of days").getValue(String.class);
                            String priceK = pr;
                            String types = type;

                            Intent intent = new Intent(getApplicationContext(),ReservedRoomDetailsSingle.class);

                            intent.putExtra("No of Rooms",RoomFromDb);
                            intent.putExtra("Check in date",DateFromDb);
                            intent.putExtra("No of days",noDaysFromDB);
                            intent.putExtra("price",priceK);
                            intent.putExtra("type",types);

                            startActivity(intent);

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