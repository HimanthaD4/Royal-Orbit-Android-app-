package com.example.royalorbit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.royalorbit.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterPage extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText userEmail = (TextInputEditText) findViewById(R.id.emailTextField);
                TextInputEditText userPassword = (TextInputEditText) findViewById(R.id.password_text_field);
                TextInputEditText firstNameEditText = (TextInputEditText) findViewById(R.id.first_name);
                TextInputEditText lastNameEditText = (TextInputEditText) findViewById(R.id.last_name);
                TextInputEditText mobileEditText = (TextInputEditText) findViewById(R.id.mobile);
                TextInputEditText address = (TextInputEditText) findViewById(R.id.address);
                TextInputEditText age = (TextInputEditText) findViewById(R.id.age);
                TextInputEditText nic = (TextInputEditText) findViewById(R.id.nic);

                if (TextUtils.isEmpty(firstNameEditText.getText().toString())) {
                    Toast.makeText(RegisterPage.this, "First name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(lastNameEditText.getText().toString())) {
                    Toast.makeText(RegisterPage.this, "Last name cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userEmail.getText().toString())) {
                    Toast.makeText(RegisterPage.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(address.getText().toString())) {
                    Toast.makeText(RegisterPage.this, "Address cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(age.getText().toString())) {
                    Toast.makeText(RegisterPage.this, "Age cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(nic.getText().toString())) {
                    Toast.makeText(RegisterPage.this, "NIC cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(mobileEditText.getText().toString())) {
                    Toast.makeText(RegisterPage.this, "Mobile number cannot be empty", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(userPassword.getText().toString())) {
                    Toast.makeText(RegisterPage.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    createUser(userEmail.getText().toString(), userPassword.getText().toString());
                }
            }
        });




    }

    public void createUser(String email, String password) {

        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegisterPage.this, "Authentication Success.",
                                    Toast.LENGTH_SHORT).show();
                            saveUserDetails(email, mAuth.getCurrentUser().getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterPage.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    public void saveUserDetails(String email, String userId) {
        TextInputEditText firstNameEditText = (TextInputEditText) findViewById(R.id.first_name);
        TextInputEditText lastNameEditText = (TextInputEditText) findViewById(R.id.last_name);
        TextInputEditText mobileEditText = (TextInputEditText) findViewById(R.id.mobile);
        TextInputEditText address = (TextInputEditText) findViewById(R.id.address);
        TextInputEditText age = (TextInputEditText) findViewById(R.id.age);
        TextInputEditText nic = (TextInputEditText) findViewById(R.id.nic);

        User user = new User();
        user.setId(userId);
        user.setFirstName(firstNameEditText.getText().toString());
        user.setLastName(lastNameEditText.getText().toString());
        user.setPhoneNumber(mobileEditText.getText().toString());
        user.setAddress(address.getText().toString());
        user.setAge(age.getText().toString());
        user.setNic(nic.getText().toString());
        user.setEmail(email.trim());

        DocumentReference documentReference = db.collection("users").document(userId);

        documentReference.set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(getApplicationContext(), MainMenu.class));
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterPage.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void forLogin(View view) {
        Intent i = new Intent(getApplicationContext(), Login.class);
        startActivity(i);
    }
}