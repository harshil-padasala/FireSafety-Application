package com.example.firesafety;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CarousalAdapter extends PagerAdapter {

    // list that will store the id of images...
    List<Integer> list;

    public CarousalAdapter(List<Integer> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // getting whole view inside this object...
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.carousal_layout, container, false);
        // getting an targeted image holder from above view's object...
        ImageView image = view.findViewById(R.id.carousal_image);

        // setting the properties for images...
        image.setImageResource(list.get(position));
        // you can put any events here...
        // this is just for example...
        /*
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Image "+position, Snackbar.LENGTH_LONG).show();
                }
            });
        */

        // adding this updated view to the container...
        container.addView(view);

        // returning the final view...
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
