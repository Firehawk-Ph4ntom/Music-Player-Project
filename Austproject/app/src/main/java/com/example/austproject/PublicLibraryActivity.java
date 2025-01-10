package com.example.austproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class PublicLibraryActivity extends AppCompatActivity {

    ListView music;
    Button Logout, privateLibrary;
    String[] tracks = {
            "Candlelight Red - Broken Glass",
            "Crossfade - Cold",
            "Evans Blue - Erase My Scars",
            "Chad Krueger - Hero",
            "Egypt Central - Home",
            "Eminem - Lose Yourself",
            "Eminem - Mockingbird",
            "Fall Of Envy - Solace",
            "Linkin Park - In the End",
            "Linkin Park - Numb",
            "Linkin Park - Somewhere I Belong",
            "Memory Of A Melody - Reach"
    };
    int[] trackResources = {
            R.raw.broken_glass,
            R.raw.cold,
            R.raw.erase_my_scars,
            R.raw.hero,
            R.raw.home,
            R.raw.lose_yourself,
            R.raw.mockingbird,
            R.raw.solace,
            R.raw.in_the_end,
            R.raw.numb,
            R.raw.somewhere_i_belong,
            R.raw.reach
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_library);

        music = findViewById(R.id.lvMusic);
        privateLibrary = findViewById(R.id.btPrivateLibrary);
        Logout = findViewById(R.id.btLogout);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(PublicLibraryActivity.this, android.R.layout.simple_list_item_1, tracks);
        music.setAdapter(adapter);

        music.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int currentTrack, long id) {
                String selectedTrack = tracks[currentTrack];
                int trackResourceId = trackResources[currentTrack];

                Intent i = new Intent(PublicLibraryActivity.this, PublicTrackPlaybackActivity.class);
                i.putExtra("TRACK_TITLE", selectedTrack);
                i.putExtra("TRACK_RESOURCE", trackResourceId);

                startActivity(i);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PublicLibraryActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        privateLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PublicLibraryActivity.this, PrivateLibraryActivity.class);
                startActivity(i);
            }
        });
    }
}