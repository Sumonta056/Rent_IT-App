package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

// hide the title bar
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
// hide the title bar

// Button
import android.widget.ImageButton;
// Button

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // Button
    private ImageButton move;
    // Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // hide the title bar

        setContentView(R.layout.activity_main);

        // next Button
        move = findViewById(R.id.button2);
        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , login.class );
                startActivity(intent);
            }
        });

        // next Button




    }
}