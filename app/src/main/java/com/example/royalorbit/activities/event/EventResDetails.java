package com.example.royalorbit.activities.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.royalorbit.R;
import com.example.royalorbit.models.Event;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventResDetails extends AppCompatActivity {

    TextView eventTypeTxt, startTimeTxt, endTimeTxt, contactNumTxt, perHourTxt, decorationsTxt, totalAmountTxt;
    String  eventType, startDateTime, endDateTime, contactNum, ACTION;
    Date orderTimeDate, deliveryTimeDate;
    Button confirmRes;
    boolean flower, fabric, stageDesign;
    Date startEvent, endEvent;
    long totalHours, perHour;
    Integer  totalAmount;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_res_details);

        eventTypeTxt = findViewById(R.id.event_type);
        startTimeTxt = findViewById(R.id.start_time);
        endTimeTxt = findViewById(R.id.end_time);
        contactNumTxt = findViewById(R.id.contact_number);
        perHourTxt = findViewById(R.id.per_hour);
        decorationsTxt = findViewById(R.id.decorations);
        totalAmountTxt = findViewById(R.id.total_amount);
        confirmRes = findViewById(R.id.confirm_res);

        //get data in previous intent
        ACTION = getIntent().getStringExtra("ACTION");
        eventType = getIntent().getStringExtra("eventType");
        flower = getIntent().getBooleanExtra("flower", false);
        fabric = getIntent().getBooleanExtra("fabric", false);
        stageDesign = getIntent().getBooleanExtra("stageDesign", false);
        contactNum = getIntent().getStringExtra("contactNum");
        startDateTime = getIntent().getStringExtra("startDateTime");
        endDateTime = getIntent().getStringExtra("endDateTime");

        System.out.println("----------------------------------- eventType " +eventType);
        System.out.println("----------------------------------- startDateTime " +startDateTime);
        System.out.println("----------------------------------- endDateTime " +  endDateTime);

        //date calculation part
        try {
            startEvent = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(startDateTime);
            endEvent = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(endDateTime);

            long different =(int) (endEvent.getTime() - startEvent.getTime());
            long secondsInMilli = 1000;
            long minutesInMilli = secondsInMilli * 60;
            long hoursInMilli = minutesInMilli * 60;
            long daysInMilli = hoursInMilli * 24;


            long elapsedDays = different / daysInMilli;
            different = different % daysInMilli;

            long elapsedHours = different / hoursInMilli;
            different = different % hoursInMilli;

            totalHours = (int) (elapsedHours + (elapsedDays*24));

            System.out.println("---------------------------- Hours : " + (elapsedHours + (elapsedDays*24)));



        } catch (ParseException e) {
            e.printStackTrace();
        }

        //bill calculation part
        String collectDecorations = " ";
        System.out.println("---------------------------- collectDecorations : " + collectDecorations);
        Integer decoration = 0;
        if (flower){
            decoration += 5000;
            collectDecorations += "Flower = Rs.5000\n";
        } if (fabric){
            decoration += 7500;
            collectDecorations += "Fabric = Rs.7500\n ";
        } if (stageDesign){
            decoration += 10000;
            collectDecorations += "Stage Design = Rs.10000\n ";
        } if (!flower && !fabric && !stageDesign){
            collectDecorations += "Not Selected";
        }
        switch (eventType) {
            case "Birthday Party":
                perHour = 5000;
                totalAmount = (int) ((totalHours *perHour) + decoration);
                break;
            case "Anniversary Party":
                perHour = 6000;
                totalAmount = (int) ((totalHours *perHour) + decoration);
                break;
            case "Batch Party":
                perHour = 4000;
                totalAmount = (int) ((totalHours *perHour) + decoration);
                break;
            case "DJ Party":
                perHour = 7000;
                totalAmount = (int) ((totalHours *perHour) + decoration);
                break;
        }

        System.out.println("---------------------------- collectDecorations : " + collectDecorations);
        System.out.println("---------------------------- decoration : " + decoration);
        System.out.println("---------------------------- elapsedHours : " + totalHours);
        System.out.println("---------------------------- totalAmount : " + totalAmount);

        //values set in TextViews
        eventTypeTxt.setText(eventType);
        startTimeTxt.setText(String.valueOf(startDateTime));
        endTimeTxt.setText(String.valueOf(endDateTime));
        contactNumTxt.setText(String.valueOf(contactNum));
        perHourTxt.setText(String.valueOf("Rs."+perHour));
        decorationsTxt.setText(collectDecorations);
        totalAmountTxt.setText(String.valueOf("Rs."+totalAmount));


        Event event = new Event();
        //store firebase
        if (ACTION.equals("POST")){
            event.setId(UUID.randomUUID().toString());
        }else if (ACTION.equals("PUT")){
            event.setId(getIntent().getStringExtra("id"));
        }
        event.setUserId(mAuth.getCurrentUser().getUid());
        event.setEventType(eventType);
        event.setFlower(flower);
        event.setFabric(fabric);
        event.setStageDesign(stageDesign);
        event.setContactNum(contactNum);
        event.setStartTime(startEvent);
        event.setEndTime(endEvent);
        event.setTotalAmount(totalAmount);

        confirmRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ACTION.equals("POST")){
                    saveRoomReservation(event);
                }else if (ACTION.equals("PUT")){
                    try {
                        updateRoomReservation(event);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void updateRoomReservation(Event event) throws ParseException {

        Map<String, Object> data = new HashMap<>();
        data.put("eventType", event.getEventType());
        data.put("flower", event.isFlower());
        data.put("fabric", event.isFabric());
        data.put("stageDesign", event.isStageDesign());
        data.put("contactNum", event.getContactNum());
        data.put("startDateTime", event.getStartTime());
        data.put("endDateTime", event.getEndTime());
        data.put("totalAmount", event.getTotalAmount());

        DocumentReference documentReference = db.collection("eventReservations").document(event.getId());
        documentReference.update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EventResDetails.this, "Reservation Update Successfully!",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EventResDetails.this, EventReservation.class));
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EventResDetails.this, "Reservation Update Failed!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveRoomReservation(Event event) {

        DocumentReference documentReference = db.collection("eventReservations").document(event.getId());
        documentReference.set(event)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EventResDetails.this, "Reservation Saved Successfully!",
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EventResDetails.this, EventReservation.class));
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EventResDetails.this, "Reservation Saved Failed!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}