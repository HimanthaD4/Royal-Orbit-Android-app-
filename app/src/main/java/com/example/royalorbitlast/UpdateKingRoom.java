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

public class UpdateKingRoom extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_update_king_room);

        DatabaseReference myRef = database.getReference("Room Reservations");

        final EditText phoneKing = findViewById(R.id.phoneDouble);
        final EditText noRoomKing = findViewById(R.id.noRoomDouble);
        final EditText inDateKing = findViewById(R.id.inDateDouble);
        final EditText dKing = findViewById(R.id.dDouble);

        final Button confirmKing = findViewById(R.id.btn_update);

        confirmKing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from edit text to string value
                final String phoneTxtK = phoneKing.getText().toString().trim();
                final String noRoomTxtK = noRoomKing.getText().toString();
                final String inDateTxtK = inDateKing.getText().toString();
                final String dTxtK= dKing.getText().toString().trim();

                final Double Rooms= Double.parseDouble(noRoomTxtK);
                final Double dates= Double.parseDouble(dTxtK);

                final Double price = Rooms * dates * 20000;
                final String  pr = String.valueOf(price);
                final String type = "King's Room";


                if (phoneTxtK.isEmpty()) {
                    phoneKing.setError("phone no. is Required");
                    phoneKing.requestFocus();
                    return;
                }
                if (noRoomTxtK.isEmpty()) {
                    noRoomKing.setError("No of Rooms Required");
                    noRoomKing.requestFocus();
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
                }
                if(Rooms > 10 ){
                    noRoomKing.setError("Maximum Number Of Booking Room is 10");
                    noRoomKing.requestFocus();
                    return;
                }
                else{
                    myRef.child("king").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //send data to databse
                            myRef.child("king").child(phoneTxtK).child("No Of Rooms").setValue(noRoomTxtK);
                            myRef.child("king").child(phoneTxtK).child("Check in date").setValue(inDateTxtK);
                            myRef.child("king").child(phoneTxtK).child("No of days").setValue(dTxtK);
                            myRef.child("king").child(phoneTxtK).child("price").setValue(price);

                            String DateFromDb = snapshot.child(phoneTxtK).child("Check in date").getValue(String.class);
                            String RoomFromDb = snapshot.child(phoneTxtK).child("No Of Rooms").getValue(String.class);
                            String noDaysFromDB = snapshot.child(phoneTxtK).child("No of days").getValue(String.class);
                            String priceK = pr;
                            String typeK = type;

                            Intent intent = new Intent(getApplicationContext(),ReservedRoomDetalis.class);

                            intent.putExtra("No of Rooms",RoomFromDb);
                            intent.putExtra("Check in date",DateFromDb);
                            intent.putExtra("No of days",noDaysFromDB);
                            intent.putExtra("price",priceK);
                            intent.putExtra("type",typeK);

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