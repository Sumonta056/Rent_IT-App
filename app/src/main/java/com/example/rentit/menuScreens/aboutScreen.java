package com.example.rentit.menuScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.rentit.R;

import java.util.Objects;

public class aboutScreen extends AppCompatActivity {

    // login info tracking
    String emails;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // hide the title bar

        setContentView(R.layout.activity_about_us);

        // login info passing
        Intent intents = getIntent();
        emails = intents.getStringExtra("emails");
        type = intents.getStringExtra("type");

        // login info passing
    }

    @Override

    // move to menuScreen screen if back key pressed
    public void onBackPressed() {

        Intent intent = new Intent(aboutScreen.this, menuScreen.class);
        // pass login data to menuScreen
        intent.putExtra("emails", emails);
        // pass login data to menuScreen
        startActivity(intent); // go to meny screen

        super.onBackPressed();
    }
}

