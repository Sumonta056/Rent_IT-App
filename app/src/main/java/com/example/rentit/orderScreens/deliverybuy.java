package com.example.rentit.orderScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.rentit.R;
import com.example.rentit.menuScreens.menuScreen;

import java.util.Objects;

public class deliverybuy extends AppCompatActivity {

    String emails;
    Button home;

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

        setContentView(R.layout.activity_delivery_buy);


        home = findViewById(R.id.homeBut2);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deliverybuy.this , menuScreen.class);
                // pass login data to menuScreen
                intent.putExtra("emails" ,emails);
                // pass login data to menuScreen
                startActivity(intent); // go to sellBaseScreen screen
            }
        });


    }

    public void onBackPressed() {

        Intent intent = new Intent(deliverybuy.this, menuScreen.class);
        // pass login data to menuScreen
        intent.putExtra("emails", emails);
        // pass login data to menuScreen
        startActivity(intent); // go to sellBaseScreen screen

        super.onBackPressed();
    }
}