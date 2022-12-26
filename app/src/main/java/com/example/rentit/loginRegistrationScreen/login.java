package com.example.rentit.loginRegistrationScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rentit.R;
import com.example.rentit.menuScreens.menu;
import com.example.rentit.screens.base;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login extends AppCompatActivity {
    // Button
    private Button move1;
    private MaterialButton move2;
    private MaterialButton move3;
    // Button

    // Database
    FirebaseAuth auth;
    EditText username ,pass ;
    // Database

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

        // back Button
        move1 = findViewById(R.id.but2);
        move1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this , base.class );
                startActivity(intent);
            }
        });
        // back Button

        // login Button starts
        auth = FirebaseAuth.getInstance();
        // search for id's
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        move2 = findViewById(R.id.login);

        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  String user = username.getText().toString();
                  String password = pass.getText().toString();

                  // if pass and user box empty : show a message to fill all box
                  if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password))
                      Toast.makeText(login.this, "Please! Fill Up All Field", Toast.LENGTH_SHORT).show();

                  else
                  {
                      auth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if(task.isSuccessful())
                              {
                                  Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                  Intent intent = new Intent(login.this, menu.class);
                                  startActivity(intent);
                              }
                              else
                              {
                                  Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                              }
                          }
                      });
                  }
            }
        });
        // login Button ends


        // Registration Button Starts
        move3 = findViewById(R.id.registration);
        move3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this , registration.class );
                startActivity(intent); // go to registration screen
            }
        });
        // Registration Button Ends
    }
}