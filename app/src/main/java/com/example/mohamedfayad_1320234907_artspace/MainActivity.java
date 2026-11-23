package com.example.mohamedfayad_1320234907_artspace;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtPosition, txtTitle, txtArtist;
    ImageView imgArtwork;
    Button btnPrevious, btnNext;

    int currentArtwork = 0;

    int[] artworkImages = {R.drawable.artspace1, R.drawable.artspace2, R.drawable.artspace3, R.drawable.artspace4, R.drawable.artspace5, R.drawable.artspace6,};

    String[] artworkTitles = { "Watercolour", "herd of sheep", "bright yellow moon","The Cannon Sho",  "Michael Angelo's" ,"colorful shapes"};

    String[] artistNames = {"William Morris","Robert Hills", " SIMON LEE"," Rijksmuseum", "John James", "New York Public Libraryt"};

    String[] creationYears = { "2019", "2020", "2022", "1680", "1910", "1913" };
    String[] artworkWebsites = {
            "https://unsplash.com/photos/floral-pattern-with-white-blossoms-RXCMQFUo9Hk",
            "https://unsplash.com/photos/herd-of-sheep-on-road-painting-8wcoY3wcbL0",
            "https://unsplash.com/photos/yellow-moon-over-dark-blue-ocean-MnnDZX4gAw4",
            "https://unsplash.com/photos/brown-and-white-sail-ship-on-sea-painting-TjegK_z-0j8",
            "https://unsplash.com/photos/a-painting-on-the-ceiling-of-a-building-1rBg5YSi00c",
            "https://unsplash.com/photos/abstract-colorful-shapes-with-organic-forms-CM0iGXGU6Pc"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            currentArtwork = savedInstanceState.getInt("currentArtwork", 0);
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });

        txtPosition = findViewById(R.id.txtPosition);
        imgArtwork = findViewById(R.id.imgArtwork);
        txtTitle = findViewById(R.id.txtTitle);
        txtArtist = findViewById(R.id.txtArtist);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        updateArtwork();
        btnNext.setOnClickListener(v -> {
            if (currentArtwork < artworkImages.length - 1) {
                currentArtwork++;
                updateArtwork();
            }
        });
        btnPrevious.setOnClickListener(v -> {
            if (currentArtwork > 0) {
                currentArtwork--;
                updateArtwork();
            }
        });
        imgArtwork.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra("title", artworkTitles[currentArtwork]);
            intent.putExtra("artist", artistNames[currentArtwork]);
            intent.putExtra("year", creationYears[currentArtwork]);
            intent.putExtra("website", artworkWebsites[currentArtwork]);
            startActivity(intent);
        });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentArtwork", currentArtwork);
    }
    private void updateArtwork() {

      imgArtwork.setImageResource(artworkImages[currentArtwork]);

        txtPosition.setText( (currentArtwork + 1) + " / " + artworkImages.length );

        txtTitle.setText(artworkTitles[currentArtwork]);

        txtArtist.setText( artistNames[currentArtwork] + " (" + creationYears[currentArtwork] + ")" );

        btnPrevious.setEnabled(currentArtwork != 0);
        btnNext.setEnabled(currentArtwork != artworkImages.length - 1);

        btnPrevious.setBackgroundResource(
                currentArtwork == 0 ? R.drawable.btn_diseable: R.drawable.btn_enable);

        btnNext.setBackgroundResource(
                currentArtwork == artworkImages.length - 1 ? R.drawable.btn_diseable : R.drawable.btn_enable);
    }
}