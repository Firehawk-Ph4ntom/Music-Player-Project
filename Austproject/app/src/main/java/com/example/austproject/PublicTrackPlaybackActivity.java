package com.example.austproject;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PublicTrackPlaybackActivity extends AppCompatActivity {

    TextView Title;
    Button PlayPause, Loop, goBack, Save;
    MediaPlayer mediaPlayer;
    boolean loopFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_track_playback);

        Title = findViewById(R.id.tvTitle);
        PlayPause = findViewById(R.id.btPlayPause);
        Loop = findViewById(R.id.btLoop);
        Save = findViewById(R.id.btSave);
        goBack = findViewById(R.id.btgoBack);

        String trackTitle = getIntent().getStringExtra("TRACK_TITLE");
        int trackResource = getIntent().getIntExtra("TRACK_RESOURCE", -1);

        Title.setText(trackTitle);

        if (trackResource != -1) {
            mediaPlayer = MediaPlayer.create(this, trackResource);
            if (mediaPlayer != null) {
                mediaPlayer.start();
                PlayPause.setText("Pause");
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        PlayPause.setText("Play");
                    }
                });
            }
        }

        PlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.pause();
                        PlayPause.setText("Play");
                    } else {
                        mediaPlayer.start();
                        PlayPause.setText("Pause");
                    }
                }
            }
        });

        Loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    loopFlag = !loopFlag;
                    mediaPlayer.setLooping(loopFlag);
                    Loop.setText(loopFlag ? "Disable Loop" : "Enable Loop");
                    Toast.makeText(PublicTrackPlaybackActivity.this, "Looping " + (loopFlag ? "Enabled" : "Disabled"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PublicTrackPlaybackActivity.this, SelectPlaylistActivity.class);
                i.putExtra("TRACK_TITLE", trackTitle);
                i.putExtra("TRACK_RESOURCE", trackResource);
                startActivity(i);
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}