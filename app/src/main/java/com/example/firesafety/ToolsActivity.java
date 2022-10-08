package com.example.firesafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class ToolsActivity extends AppCompatActivity {

    private int[] index_map = new int[] {
            R.id.fireExtinguisher,
            R.id.firehose,
            R.id.fireSmoke,
            R.id.fireSplinker,
            R.id.fireSandBucket
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
    }

    public void goDescription(View view) {

    }
}