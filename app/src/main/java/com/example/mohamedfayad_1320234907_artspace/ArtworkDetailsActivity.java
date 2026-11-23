package com.example.mohamedfayad_1320234907_artspace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    TextView txtArtworkDetails;
    Button btnBack;
    Button btnVisitWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        txtArtworkDetails = findViewById(R.id.txtArtworkDetails);
        btnBack = findViewById(R.id.btnBack);
        btnVisitWeb = findViewById(R.id.btnVisitWeb);
        String title = getIntent().getStringExtra("title");
        String artist = getIntent().getStringExtra("artist");
        String year = getIntent().getStringExtra("year");

        txtArtworkDetails.setText(
                "Title: " + title +"\n\nArtist: " + artist +"\n\nCreation Year: " + year
        );

        btnBack.setOnClickListener(v -> finish());


        btnVisitWeb.setOnClickListener(v -> {

                String website = getIntent().getStringExtra("website");

                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(website)
                );

                startActivity(intent);
            });


    }
}