package com.example.rentit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.rentit.menuScreens.menu;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class acountProfile extends AppCompatActivity {

    MaterialButton home;

    TextView name, email, phone, location, password;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
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

        setContentView(R.layout.activity_account_profile);

        Intent intent = getIntent();
        emails = intent.getStringExtra("emails");

        // home button
        home = findViewById(R.id.homeBut);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(acountProfile.this, menu.class);
                // pass login data to menu
                intent.putExtra("emails", emails);
                // pass login data to menu
                startActivity(intent); // go to sell screen
            }
        });
        // home button

        name = findViewById(R.id.acc_user);
        email = findViewById(R.id.acc_email);
        phone = findViewById(R.id.acc_ph);
        location = findViewById(R.id.acc_location);
        password = findViewById(R.id.acc_pass);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    if (ds.child("email").getValue().equals(emails)) {
                        name.setText(ds.child("name").getValue(String.class));
                        email.setText(ds.child("email").getValue(String.class));
                        phone.setText(ds.child("phone").getValue(String.class));
                        location.setText(ds.child("address").getValue(String.class));
                        password.setText(ds.child("password").getValue(String.class));

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(acountProfile.this, menu.class);
        // pass login data to menu
        intent.putExtra("emails", emails);
        // pass login data to menu
        startActivity(intent); // go to sell screen

        super.onBackPressed();
    }
}