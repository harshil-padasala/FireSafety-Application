package com.example.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class FireAlaram extends AppCompatActivity {

    String[] localities;
    AutoCompleteTextView localitySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_alaram);

        localities = new String[] {
                "Gulabnagar","Patel colony", "Sat rasta", "Digvijay Plot", "Townhall",
                "Lakhota Talav", "Khodiyar colony"
        };
        localitySpinner = findViewById(R.id.area);

        ArrayAdapter<String> localityAdapter = new ArrayAdapter<>(FireAlaram.this, android.R.layout.simple_list_item_1, localities);
        localitySpinner.setAdapter(localityAdapter);
    }
}