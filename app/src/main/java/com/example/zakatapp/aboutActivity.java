package com.example.zakatapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class aboutActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView websiteLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Sumbul Zakat Gold");
        getSupportActionBar().setTitle("About Page");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Add a clickable URL link
        websiteLink = findViewById(R.id.websiteLink);

        String githubLink = "https://github.com"; // Text to display as the link
        SpannableString spannableString = new SpannableString(githubLink);
        spannableString.setSpan(new UnderlineSpan(), 0, githubLink.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), 0, githubLink.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        websiteLink.setText(spannableString);

        websiteLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // Handle the click event for the link
                    String url = "https://github.com";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemShare) {
            // Share functionality
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Please use my application - http://t.co/app");
            startActivity(Intent.createChooser(shareIntent, null));
            return true;
        } else if (item.getItemId() == R.id.itemAbout) {
            // Launch about activity
            Intent aboutIntent = new Intent(this, aboutActivity.class);
            startActivity(aboutIntent);
            return true;
        } else if (item.getItemId() == R.id.itemHome){
            // Launch about activity
            Intent aboutIntent = new Intent(this, homeActivity.class);
            startActivity(aboutIntent);
            return true;
        }

        if (item.getItemId() == android.R.id.home) {
            // Handle back button
            super.onBackPressed();
            return true;
        }
        return false;
    }
}