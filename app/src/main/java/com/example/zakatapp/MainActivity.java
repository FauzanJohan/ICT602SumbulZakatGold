package com.example.zakatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.RadioButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etGram;
    RadioButton keepType;
    RadioButton wearType;
    EditText etPrice;
    Button btnCalculate;
    Button btnReset;

    Toolbar toolbar;

    boolean isKeepSelected = true; // Default to keepType selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGram = findViewById(R.id.inputWeight);
        keepType = findViewById(R.id.radioKeep);
        wearType = findViewById(R.id.radioWear);
        etPrice = findViewById(R.id.inputPrice);
        btnCalculate = findViewById(R.id.btnConvert);
        btnCalculate.setOnClickListener(this);

        btnReset = findViewById(R.id.buttonReset); // Initialize btnReset here
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset functionality
                etGram.getText().clear();
                etPrice.getText().clear();
                keepType.setChecked(true);
                wearType.setChecked(false);

                // Clear result TextViews
                TextView totalValue = findViewById(R.id.totalValue);
                TextView totalPayable = findViewById(R.id.totalPayable);
                TextView totalZakat = findViewById(R.id.totalZakat);

                totalValue.setText("Total Value of Gold:");
                totalPayable.setText("Total Gold Value That is Zakat Payable:");
                totalZakat.setText("Total Zakat:");
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar); // Find the Toolbar from layout
        setSupportActionBar(toolbar); // Set the Toolbar as the ActionBar
        getSupportActionBar().setTitle("Calculator Page"); // Set the title for the Toolbar

        // Inflate the menu into the Toolbar
        getMenuInflater().inflate(R.menu.menu, toolbar.getMenu());

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        keepType.setOnClickListener(this);
        wearType.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.radioKeep) {
            // Handle Keep radio button selection
            keepType.setChecked(true);
            wearType.setChecked(false);
        } else if (v.getId() == R.id.radioWear) {
            // Handle Wear radio button selection
            keepType.setChecked(false);
            wearType.setChecked(true);
        } else if (v.getId() == R.id.btnConvert) {
            calculateZakat();
        }
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

    private void calculateZakat() {

        String gramText = etGram.getText().toString().trim();
        String priceText = etPrice.getText().toString().trim();

        if (gramText.isEmpty() || priceText.isEmpty()) {
            // Show an error message or handle empty fields
            return;
        }

        try {
            double goldWeight = Double.parseDouble(etGram.getText().toString());
            double goldValue = Double.parseDouble(etPrice.getText().toString());

            double X = keepType.isSelected() ? 85 : 200; // Ternary operator to determine X

            double totalValueGold = goldWeight * goldValue;
            double payableWeight = goldWeight - X;
            payableWeight = Math.max(payableWeight, 0); // Set payableWeight to 0 if it's less than 0

            double totalPayableGold = payableWeight * goldValue;
            double totalZakatGold = 0.025 * totalPayableGold; // Zakat calculation

            displayZakat(totalValueGold, totalPayableGold, totalZakatGold);

        } catch (NumberFormatException e) {
            // Handle parsing errors or empty fields
            e.printStackTrace();
            // Display an error message to the user or perform appropriate actions
        }
    }

    private void displayZakat(double totalValueGold, double totalPayableGold, double totalZakatGold) {
        // Display the results in their respective TextViews
        TextView totalValue = findViewById(R.id.totalValue);
        totalValue.setText("Total Value of Gold: " + totalValueGold);

        TextView totalPayable = findViewById(R.id.totalPayable);
        totalPayable.setText("Total Zakat Payable: " + totalPayableGold);

        TextView totalZakat = findViewById(R.id.totalZakat);
        totalZakat.setText("Total Zakat: " + totalZakatGold);
    }


}