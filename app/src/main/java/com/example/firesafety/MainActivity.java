package com.example.firesafety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends AppCompatActivity {

    // required objects...
    ViewPager carousal;
    CircleIndicator indicator;

    Timer timer;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // all objects are initialized here...
        carousal = findViewById(R.id.viewPager_carousal);
        indicator = findViewById(R.id.Circle_Indicator);
        handler = new Handler();
        timer = new Timer();

        // list of images are here...
        List<Integer> image_list = new ArrayList<>();
        image_list.add(R.drawable.p1);
        image_list.add(R.drawable.p2);
        image_list.add(R.drawable.p3);
        // setting up new carousel adapter...
        CarousalAdapter adapter = new CarousalAdapter(image_list);

        // attaching the carousel adapter to the view pager...
        carousal.setAdapter(adapter);

        // attaching the view pager to the circle indicator...
        indicator.setViewPager(carousal);

        // scheduling the timer to change the image automatically...
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // assigning the task that will be done by the handler...
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        // it gets the current image position...
                        int currentImagePosition = carousal.getCurrentItem();

                        // setting up next image to be shown...
                        if(currentImagePosition == image_list.size()-1) {
                            currentImagePosition = 0;
                        } else {
                            currentImagePosition++;
                        }

                        // showing up the next image...
                        carousal.setCurrentItem(currentImagePosition, true);
                    }
                });
            }
        }, 4000, 4000);

    }
}