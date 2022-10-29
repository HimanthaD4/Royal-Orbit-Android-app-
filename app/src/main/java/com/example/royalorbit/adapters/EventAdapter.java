package com.example.royalorbit.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.royalorbit.R;
import com.example.royalorbit.models.Event;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private Context context;
    private List<?> resCardList;
    private Class<?> activity;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public EventAdapter(Context context, List<?> resCardList, Class<?> activity) {
        this.context = context;
        this.resCardList = resCardList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_card_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = (Event) resCardList.get(position);

        DocumentReference documentReference = db.collection("eventReservations").document(event.getId());
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Event data = new Event(
                        documentSnapshot.getString("id"),
                        documentSnapshot.getString("userId"),
                        documentSnapshot.getString("eventType"),
                        documentSnapshot.getBoolean("flower"),
                        documentSnapshot.getBoolean("fabric"),
                        documentSnapshot.getBoolean("stageDesign"),
                        documentSnapshot.getString("contactNum"),
                        documentSnapshot.getDate("startTime"),
                        documentSnapshot.getDate("endTime"),
                        documentSnapshot.getLong("totalAmount")
                );

                switch (data.getEventType()){
                    case "Birthday Party":
                        holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.birthday_party));
                        break;
                    case "Anniversary Party":
                        holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.anniversry_party));
                        break;
                    case "Batch Party":
                        holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.batch_party));
                        break;
                    case "DJ Party":
                        holder.image.setImageDrawable(context.getResources().getDrawable(R.drawable.dj_party));
                }
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                holder.eventType.setText(data.getEventType());
                holder.resDateRange.setText(dateFormat.format(data.getStartTime()) + "\n" + dateFormat.format(data.getEndTime()));
                holder.totalAmount.setText("Rs." + data.getTotalAmount());

                holder.seeMoreBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, activity);
                        intent.putExtra("id", data.getId());
                        intent.putExtra("eventType", data.getEventType());
                        intent.putExtra("isFlower", data.isFlower());
                        intent.putExtra("isFabric", data.isFabric());
                        intent.putExtra("isStageDesign", data.isStageDesign());
                        intent.putExtra("contactNum", data.getContactNum());
                        intent.putExtra("startTime", dateFormat.format(data.getStartTime()));
                        intent.putExtra("endTime", dateFormat.format(data.getEndTime()));
                        intent.putExtra("totalAmount", data.getTotalAmount());
                        context.startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return resCardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventType, resDateRange, totalAmount;
        ImageView image;
        Button seeMoreBtn;


        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.type_image);
            eventType = itemView.findViewById(R.id.type);
            resDateRange = itemView.findViewById(R.id.res_date_range);
            totalAmount = itemView.findViewById(R.id.total_amount);
            seeMoreBtn = itemView.findViewById(R.id.see_more);

        }
    }
}
