package com.example.royalorbit;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.royalorbit.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.login_btn);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                TextInputEditText userEmail = (TextInputEditText) findViewById(R.id.emailTextField);
                TextInputEditText userPassword = (TextInputEditText) findViewById(R.id.password_text_field);
                loginUser(userEmail.getText().toString(), userPassword.getText().toString());
            }
        });
    }

    private void loginUser(String email, String password){
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(Login.this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(Login.this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainMenu.class));
                    }else{
                        Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void forRegister(View view) {
        Intent i = new Intent(getApplicationContext(), RegisterPage.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}