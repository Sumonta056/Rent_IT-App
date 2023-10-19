package com.example.rentit.menuScreens;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.rentit.R;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class menuScreen extends AppCompatActivity {

    // Buttons
    MaterialButton sell;
    MaterialButton buy ;
    MaterialButton free;
    MaterialButton account;
    MaterialButton about;
    MaterialButton status;
    // buttons

    // data passing
    String emails;
    // data passing

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

        // login info passing
        Intent intents = getIntent();
        emails = intents.getStringExtra("emails");
        // login info passing

        // sellBaseScreen button
        sell = findViewById(R.id.sell);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuScreen.this ,sellRentScreen.class );
                // pass login data to menuScreen
                intent.putExtra("emails" ,emails);
                // pass login data to menuScreen
                startActivity(intent); // go to sellBaseScreen screen
            }
        });
        // sellBaseScreen button

        // buy button
        buy = findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuScreen.this , buyScreen.class);
                // pass login data to menuScreen
                intent.putExtra("emails" ,emails);
                // pass login data to menuScreen
                startActivity(intent); // go to buy screen
            }
        });
        // buy button

        // free button
        free = findViewById(R.id.freebutton);
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuScreen.this , freeScreen.class);
                // pass login data to menuScreen
                intent.putExtra("emails" ,emails);
                // pass login data to menuScreen
                startActivity(intent); // go to free screen
            }
        });
        // free button

        // account button
        account = findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuScreen.this , acountSccreen.class);
                // pass login data to menuScreen
                intent.putExtra("emails" ,emails);
                // pass login data to menuScreen
                startActivity(intent); // go to account screen
            }
        });
        // account button

        // about button
        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuScreen.this , aboutScreen.class);
                // pass login data to menuScreen
                intent.putExtra("emails" ,emails);
                // pass login data to menuScreen
                startActivity(intent); // go to about screen
            }
        });
        // about button

        // status button
        status = findViewById(R.id.status);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuScreen.this , com.example.rentit.orderScreens.status.class);
                // pass login data to menuScreen
                intent.putExtra("emails" ,emails);
                // pass login data to menuScreen
                startActivity(intent); // go to status screen
            }
        });
        // status button


    }

    @Override
    // what to do back button press
    public void onBackPressed() {
        // Exit app
        // show a dialog box
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(menuScreen.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit app?");
        // show two option yes or no
        //  if user pressed yes , stop the application
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        //  if user pressed no , go back to previous screen
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        alertDialog.show();
        // exit app
    }
}