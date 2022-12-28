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

public class menu extends AppCompatActivity {

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

        // sell button
        sell = findViewById(R.id.sell);
        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this , sell.class );
                // pass login data to menu
                intent.putExtra("emails" ,emails);
                // pass login data to menu
                startActivity(intent); // go to sell screen
            }
        });
        // sell button

        // buy button
        buy = findViewById(R.id.buy);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this , buyScreen.class);
                // pass login data to menu
                intent.putExtra("emails" ,emails);
                // pass login data to menu
                startActivity(intent); // go to buy screen
            }
        });
        // buy button

        // free button
        free = findViewById(R.id.freebutton);
        free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this , freeScreen.class);
                // pass login data to menu
                intent.putExtra("emails" ,emails);
                // pass login data to menu
                startActivity(intent); // go to free screen
            }
        });
        // free button

        // account button
        account = findViewById(R.id.account);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this , acountProfile.class);
                // pass login data to menu
                intent.putExtra("emails" ,emails);
                // pass login data to menu
                startActivity(intent); // go to account screen
            }
        });
        // account button

        // about button
        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this , aboutUs.class);
                // pass login data to menu
                intent.putExtra("emails" ,emails);
                // pass login data to menu
                startActivity(intent); // go to about screen
            }
        });
        // about button

        // status button
        status = findViewById(R.id.status);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menu.this , com.example.rentit.order.status.class);
                // pass login data to menu
                intent.putExtra("emails" ,emails);
                // pass login data to menu
                startActivity(intent); // go to about screen
            }
        });
        // status button


    }

    @Override
    public void onBackPressed() {
        // Exit app
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(menu.this);
        alertDialog.setTitle("Exit App");
        alertDialog.setMessage("Do you want to exit app?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

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