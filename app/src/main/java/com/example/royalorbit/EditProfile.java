package com.example.royalorbit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.royalorbit.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditProfile extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    User authUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getAuthUser();
    }

    private void getAuthUser() {
        String userId = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("users").document(userId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = new User();
                user.setId(userId);
                user.setFirstName(documentSnapshot.getString("firstName"));
                user.setLastName(documentSnapshot.getString("lastName"));
                user.setAddress(documentSnapshot.getString("address"));
                user.setPhoneNumber(documentSnapshot.getString("phoneNumber"));
                user.setNic(documentSnapshot.getString("nic"));
                user.setAge(documentSnapshot.getString("age"));

                authUser = user;

                TextInputEditText firstNameTextField = (TextInputEditText) findViewById(R.id.first_name);
                firstNameTextField.setText(authUser.getFirstName());

                TextInputEditText lastNameTextField = (TextInputEditText) findViewById(R.id.last_name);
                lastNameTextField.setText(authUser.getLastName());

                TextInputEditText phoneNumberTextField = (TextInputEditText) findViewById(R.id.mobile);
                phoneNumberTextField.setText(authUser.getPhoneNumber());

                TextInputEditText addressTextField = (TextInputEditText) findViewById(R.id.address);
                addressTextField.setText(authUser.getAddress());

                TextInputEditText nic = (TextInputEditText) findViewById(R.id.nic);
                nic.setText(authUser.getNic());

                TextInputEditText age = (TextInputEditText) findViewById(R.id.age);
                age.setText(authUser.getAge());

            }
        });
    }

    public void updateProfile(View view) {
        TextInputEditText firstNameEditText = (TextInputEditText) findViewById(R.id.first_name);
        TextInputEditText lastNameEditText = (TextInputEditText) findViewById(R.id.last_name);
        TextInputEditText mobileEditText = (TextInputEditText) findViewById(R.id.mobile);
        TextInputEditText address = (TextInputEditText) findViewById(R.id.address);
        TextInputEditText age = (TextInputEditText) findViewById(R.id.age);
        TextInputEditText nic = (TextInputEditText) findViewById(R.id.nic);

        if (TextUtils.isEmpty(firstNameEditText.getText().toString())) {
            Toast.makeText(EditProfile.this, "First name cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(lastNameEditText.getText().toString())) {
            Toast.makeText(EditProfile.this, "Last name cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(address.getText().toString())) {
            Toast.makeText(EditProfile.this, "Address cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(age.getText().toString())) {
            Toast.makeText(EditProfile.this, "Age cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(nic.getText().toString())) {
            Toast.makeText(EditProfile.this, "NIC cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(mobileEditText.getText().toString())) {
            Toast.makeText(EditProfile.this, "Mobile number cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            update();
        }

    }

    private void update() {
        TextInputEditText firstNameEditText = (TextInputEditText) findViewById(R.id.first_name);
        TextInputEditText lastNameEditText = (TextInputEditText) findViewById(R.id.last_name);
        TextInputEditText mobileEditText = (TextInputEditText) findViewById(R.id.mobile);
        TextInputEditText address = (TextInputEditText) findViewById(R.id.address);
        TextInputEditText age = (TextInputEditText) findViewById(R.id.age);
        TextInputEditText nic = (TextInputEditText) findViewById(R.id.nic);

        Map<String, Object> data = new HashMap<>();
        data.put("firstName", firstNameEditText.getText().toString());
        data.put("lastName", lastNameEditText.getText().toString());
        data.put("phoneNumber", mobileEditText.getText().toString());
        data.put("address", address.getText().toString());
        data.put("age", age.getText().toString());
        data.put("nic", nic.getText().toString());

        String userId = mAuth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("users").document(userId);
        documentReference.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(EditProfile.this, "Profile Details Updated Successfully.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), UserProfile.class));
            }
        });
    }
}