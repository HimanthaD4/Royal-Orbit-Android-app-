package com.example.royalorbit.activities.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.royalorbit.MainMenu;
import com.example.royalorbit.R;
import com.example.royalorbit.adapters.EventAdapter;
import com.example.royalorbit.models.Event;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class EventReservation extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_reservation);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainMenu.class);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getEventReservation(mAuth.getCurrentUser().getUid());
    }

    private void getEventReservation(String currentUser) {
        db.collection("eventReservations")
                .whereEqualTo("userId", currentUser)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        List<Event> resCardList = new ArrayList<>();
                        for (QueryDocumentSnapshot eventRes : value) {
                            Event eventObj = new Event(
                                    eventRes.getString("id"),
                                    eventRes.getString("userId"),
                                    eventRes.getString("eventType"),
                                    eventRes.getBoolean("flower"),
                                    eventRes.getBoolean("fabric"),
                                    eventRes.getBoolean("stageDesign"),
                                    eventRes.getString("contactNum"),
                                    eventRes.getDate("startTime"),
                                    eventRes.getDate("endTime"),
                                    eventRes.getLong("totalAmount")

                            );
                            resCardList.add(eventObj);
                        }
                        EventAdapter adapter = new EventAdapter(EventReservation.this, resCardList, EditEventRes.class);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }


    public void createEventRes(View view) {
        Intent intent = new Intent(EventReservation.this, CreateEventRes.class);
        startActivity(intent);
    }
}