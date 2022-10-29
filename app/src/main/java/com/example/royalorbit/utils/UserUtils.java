package com.example.royalorbit.utils;

import com.example.royalorbit.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserUtils {
    public FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    private User currentUser;

    public void getAuthUser(){
        String userId= mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = new User();
                user.setId(userId);
                user.setFirstName(documentSnapshot.getString("firstName"));
                user.setLastName(documentSnapshot.getString("lastName"));
                user.setPhoneNumber(documentSnapshot.getString("phoneNumber"));
                user.setEmail(documentSnapshot.getString("email"));
                setCurrentUser(user);
            }
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
