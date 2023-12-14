package com.example.zakatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class homeActivity extends AppCompatActivity {

    Button btnCalculatePage;
    Button btnAboutPage;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Replace with your actual home activity layout

        toolbar = findViewById(R.id.toolbar); // Find the Toolbar from layout
        setSupportActionBar(toolbar); // Set the Toolbar as the ActionBar
        getSupportActionBar().setTitle("Home Page"); // Set the title for the Toolbar
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));


        // Inflate the menu into the Toolbar
        getMenuInflater().inflate(R.menu.menu, toolbar.getMenu());

        btnCalculatePage = findViewById(R.id.buttonCalculatePage);
        btnCalculatePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain(); // Call openMain method when buttonCalculate is clicked
            }
        });

        btnAboutPage = findViewById(R.id.buttonAboutPage);
        btnAboutPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAbout(); // Call openAbout method when buttonAboutPage is clicked
            }
        });

    }

    private void openMain() {
        // Logic to open the MainActivity or perform other actions
        Intent intent = new Intent(homeActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void openAbout() {
        // Logic to open the AboutActivity or perform other actions
        Intent intent = new Intent(homeActivity.this, aboutActivity.class);
        startActivity(intent);
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
        return false;
    }
}
