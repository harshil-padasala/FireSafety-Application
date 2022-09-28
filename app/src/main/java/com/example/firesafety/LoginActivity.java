package com.example.firesafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText UserName, Password;

    MaterialButton login;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserName = findViewById(R.id.editUsername);
        Password = findViewById(R.id.editPassword);

        login = findViewById(R.id.login_login);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(view -> {
            String username = Objects.requireNonNull(UserName.getText()).toString();
            String password = Objects.requireNonNull(Password.getText()).toString();

            registerUser(username, password);
        });


    }

    private void registerUser(String username, String password) {

        firebaseAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(LoginActivity.this, "Successfully Signed up", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}