package com.example.rentit.menuScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.rentit.R;

import java.util.Objects;

public class sell extends AppCompatActivity {

    // buttons
    Button button;
    Button rent;

    // login track
    String emails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // hide the title bar

        // login info passing
        Intent intents = getIntent();
        emails = intents.getStringExtra("emails");
        // login info passing

        setContentView(R.layout.activity_sell);

        // sell free button
        button = findViewById(R.id.sell_free);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sell.this , sellFreeScreen.class);
                // pass login data
                intent.putExtra("emails" ,emails);
                // pass login data
                startActivity(intent); // go to sell free screen

            }
        });
        // sell free button


        // rent item button
        rent = findViewById(R.id.sell_rent);
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sell.this , sellRentScreen.class);
                // pass login data
                intent.putExtra("emails" ,emails);
                // pass login data
                startActivity(intent); // go to rent item screen

            }
        });
        // rent item button
    }
    public void onBackPressed() {

        Intent intent = new Intent(sell.this, menu.class);
        // pass login data to menu
        intent.putExtra("emails", emails);
        // pass login data to menu
        startActivity(intent); // go to menu screen

        super.onBackPressed();
    }
}