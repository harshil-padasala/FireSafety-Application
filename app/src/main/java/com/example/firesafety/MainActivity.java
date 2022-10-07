package com.example.firesafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

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

        FirebaseMessaging.getInstance().subscribeToTopic("Fire-Reported");

        // all objects are initialized here...
        carousal = findViewById(R.id.viewPager_carousal);
        indicator = findViewById(R.id.Circle_Indicator);
        handler = new Handler();
        timer = new Timer();

        // list of images are here...
        List<Integer> image_list = new ArrayList<>();
        image_list.add(R.drawable.image_one);
        image_list.add(R.drawable.image_two);
        image_list.add(R.drawable.image_three);
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
                        if (currentImagePosition == image_list.size() - 1) {
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

    public void goContacts(View view) {
        startActivity(new Intent(MainActivity.this, ContactsActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.options_new1) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
            finish();
            Toast.makeText(this, "Log Out Successfully.", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);

    }

    public void goProfile(View view) {
        startActivity(new Intent(MainActivity.this, Profile.class));
    }

    public void goGuidelines(View view) {
        startActivity(new Intent(MainActivity.this, Guidelines.class));
    }
}