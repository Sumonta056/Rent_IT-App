package com.example.rentit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class login extends AppCompatActivity {
    // Button
    private Button move1;
    private MaterialButton move2;
    private MaterialButton move3;
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

        setContentView(R.layout.activity_login);


        // Button
        move1 = findViewById(R.id.but2);
        move1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this , MainActivity.class );
                startActivity(intent);
            }
        });
        // Button

        // Button
        move2 = findViewById(R.id.login);
        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this , menu.class );
                startActivity(intent);
            }
        });
        // Button


        // Button
        move3 = findViewById(R.id.registration);
        move3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this , registration.class );
                startActivity(intent);
            }
        });
        // Button
    }
}