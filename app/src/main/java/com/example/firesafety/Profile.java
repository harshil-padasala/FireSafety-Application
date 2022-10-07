package com.example.firesafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView username, email, phone, area, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = findViewById(R.id.profile_username);
        email = findViewById(R.id.profile_email);
        phone = findViewById(R.id.profile_phone);
        area = findViewById(R.id.profile_area);
        address = findViewById(R.id.profile_address);

        FirebaseUser CurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(CurrentUser!=null) {
            Query query = FirebaseDatabase.getInstance().getReference("users");
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot ss : snapshot.getChildren()) {
                            UserInfo user = ss.getValue(UserInfo.class);
                            if (user.getEmail().compareTo(CurrentUser.getEmail()) == 0) {
                                username.setText(ss.getKey());
                                email.setText(user.getEmail());
                                phone.setText(user.getPhone());
                                area.setText(user.getArea());
                                address.setText(user.getAddress());
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}