package com.example.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity {

    MaterialButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogin = findViewById(R.id.login);

        btnLogin.setOnClickListener(view -> {
            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
        });
    }
}