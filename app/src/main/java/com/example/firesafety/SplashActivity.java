package com.example.firesafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    boolean admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if(user!=null) {
                Query query = FirebaseDatabase.getInstance().getReference("users");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            for (DataSnapshot ss : snapshot.getChildren()) {
                                UserInfo user = ss.getValue(UserInfo.class);
                                if (user.getEmail().compareTo(user.email)==0) {
                                    if (user.isAdmin) {
                                        admin = true;
                                    }
                                } else {
                                    System.out.println("Not an intended user...");
                                }
                            }
                            if (admin) {
                                startActivity(new Intent(SplashActivity.this, FireAlaram.class));
                                finish();
                            } else {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }, 2000);
    }
}