package com.example.rentit.menuScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.rentit.R;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class acountProfile extends AppCompatActivity {

    // Button for home
    MaterialButton home;

    // account database
    TextView name, email, phone, location, password;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String emails;
    // account database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // hide the title bar

        setContentView(R.layout.activity_account_profile);

        // login info passing
        Intent intent = getIntent();
        emails = intent.getStringExtra("emails");
        // login info passing

        // home button
        home = findViewById(R.id.homeBut);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(acountProfile.this, menu.class);
                // pass login data to menu
                intent.putExtra("emails", emails);
                // pass login data to menu
                startActivity(intent); // go to menu screen
            }
        });
        // home button

        // accessing database with emails
        name = findViewById(R.id.acc_user);
        email = findViewById(R.id.acc_email);
        phone = findViewById(R.id.acc_ph);
        location = findViewById(R.id.acc_location);
        password = findViewById(R.id.acc_pass);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");
        // location of where all user data are store

        // access database reference's
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            // value to access an user from database
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //check all user data and its child one by one
                for (DataSnapshot ds : snapshot.getChildren()) {
                    // stop where the tracked emails matched with database email
                    // then show all email's parent's all child info
                    if (ds.child("email").getValue().equals(emails)) {
                        name.setText(ds.child("name").getValue(String.class));
                        email.setText(ds.child("email").getValue(String.class));
                        phone.setText(ds.child("phone").getValue(String.class));
                        location.setText(ds.child("address").getValue(String.class));
                        password.setText(ds.child("password").getValue(String.class));

                    }
                }

            }
            // accessing database with emails

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    // move to menu class if back key pressed
    public void onBackPressed() {

        Intent intent = new Intent(acountProfile.this, menu.class);
        // pass login data to menu
        intent.putExtra("emails", emails);
        // pass login data to menu
        startActivity(intent); // go to menu screen

        super.onBackPressed();
    }
}