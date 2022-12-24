package com.example.rentit.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.rentit.R;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class menu extends AppCompatActivity {

    MaterialButton sell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // hide the title bar

        setContentView(R.layout.activity_menu);

        // sell button
        sell = findViewById(R.id.sell);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this , com.example.rentit.menu.sell.class );
                startActivity(intent); // go to sell screen
            }
        });
        // sell button
    }
}