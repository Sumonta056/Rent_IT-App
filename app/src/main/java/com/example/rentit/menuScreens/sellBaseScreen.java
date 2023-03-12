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

public class sellBaseScreen extends AppCompatActivity {

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

        // sellBaseScreen free button
        button = findViewById(R.id.sell_free);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sellBaseScreen.this , sellFreeScreen.class);
                // pass login data
                intent.putExtra("emails" ,emails);
                // pass login data
                startActivity(intent); // go to sellBaseScreen free screen

            }
        });
        // sellBaseScreen free button


        // rent item button
        rent = findViewById(R.id.sell_rent);
        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sellBaseScreen.this , sellRentScreen.class);
                // pass login data
                intent.putExtra("emails" ,emails);
                // pass login data
                startActivity(intent); // go to rent item screen

            }
        });
        // rent item button
    }
    public void onBackPressed() {

        Intent intent = new Intent(sellBaseScreen.this, menuScreen.class);
        // pass login data to menuScreen
        intent.putExtra("emails", emails);
        // pass login data to menuScreen
        startActivity(intent); // go to menuScreen screen

        super.onBackPressed();
    }
}