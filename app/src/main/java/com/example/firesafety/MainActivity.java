package com.example.firesafety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    ViewPager carousal;
    CircleIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carousal = findViewById(R.id.viewPager_carousal);
        indicator = findViewById(R.id.Circle_Indicator);

        List<Integer> image_list = new ArrayList<>();
        image_list.add(R.drawable.p1);
        image_list.add(R.drawable.p2);
        image_list.add(R.drawable.p3);
        CarousalAdapter adapter = new CarousalAdapter(image_list);

        carousal.setAdapter(adapter);

        indicator.setViewPager(carousal);

    }
}