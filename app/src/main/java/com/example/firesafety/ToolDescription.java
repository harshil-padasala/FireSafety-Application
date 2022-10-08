package com.example.firesafety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.Random;

public class ToolDescription extends AppCompatActivity {

    YouTubePlayerView video;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_description);

        Intent i = getIntent();
        int id = i.getIntExtra("id",0);

        video = findViewById(R.id.tools_desc_youtube_video);
        description = findViewById(R.id.tools_desc_description);
        getLifecycle().addObserver(video);

        video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                int length = ToolsInfo.videos[id].length;
                Random rand = new Random();
                int subId = rand.nextInt(length);

                String videoId = ToolsInfo.videos[id][subId];
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        description.setText(ToolsInfo.description[id]);

    }
}