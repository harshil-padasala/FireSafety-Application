package com.example.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ToolsActivity extends AppCompatActivity {

    private  static ArrayList<Integer> index_map = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        index_map.add(R.id.fireExtinguisher);
        index_map.add(R.id.firehose);
        index_map.add(R.id.fireSmoke);
        index_map.add(R.id.fireSplinker);
        index_map.add(R.id.fireSandBucket);

    }

    public void goDescription(View view) {
        MaterialCardView card = (MaterialCardView) findViewById(view.getId());
        int id = index_map.indexOf(card.getId());

        Intent showDesc = new Intent(ToolsActivity.this, ToolDescription.class);
        showDesc.putExtra("id",id);
        startActivity(showDesc);
    }
}