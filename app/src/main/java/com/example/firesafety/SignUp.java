package com.example.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class SignUp extends AppCompatActivity {

    String[] localities;
    AutoCompleteTextView localitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        localities = new String[] {
          "Gulabnagar","Patel colony", "Sat rasta", "Digvijay Plot", "Townhall",
          "Lakhota Talav", "Khodiyar colony"
        };
        localitySpinner = findViewById(R.id.area);

       ArrayAdapter<String> localityAdapter = new ArrayAdapter<>(SignUp.this, android.R.layout.simple_list_item_1, localities);
       localitySpinner.setAdapter(localityAdapter);

    }

    public void logIn(View view) {
        startActivity(new Intent(SignUp.this, LogIn.class));
        finish();
    }
}