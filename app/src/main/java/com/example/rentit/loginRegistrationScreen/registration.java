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
import com.example.rentit.regisrationData.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class registration extends AppCompatActivity {

    // Button
    private Button back;
    private MaterialButton move2;
    private MaterialButton move3;
    // Button

    // database
    FirebaseAuth auth;
    FirebaseDatabase database;
    EditText username;
    EditText pass;
    EditText email;
    EditText address;
    EditText repass;
    EditText ph;
    // database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // hide the title bar

        setContentView(R.layout.activity_registration);

        // back Button start
        back = findViewById(R.id.back2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registration.this, login.class);
                startActivity(intent);
            }
        });
        // back Button end


        // pushing user information into database
        // Registration Button starts
        auth = FirebaseAuth.getInstance(); // get the desire database location
        database = FirebaseDatabase.getInstance();
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        repass = findViewById(R.id.passRe);
        ph = findViewById(R.id.phone);
        move2 = findViewById(R.id.reg);

        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String emails = email.getText().toString();
                String adr = address.getText().toString();
                String pasRe = repass.getText().toString();
                String phone = ph.getText().toString();

                // check all info given or not
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(password) || TextUtils.isEmpty(pasRe) || TextUtils.isEmpty(emails) || TextUtils.isEmpty(adr) | TextUtils.isEmpty(phone))
                    Toast.makeText(registration.this, "Please! Fill Up All Information", Toast.LENGTH_SHORT).show();

                else {
                    // check both pass and repass same or not
                    if (password.equals(pasRe)) {

                        // creating a account in authentication  with email and password just for login
                        auth.createUserWithEmailAndPassword(emails, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // creating another account with all information of registration
                                    UserModel userModel = new UserModel(user, emails, adr, password, phone);
                                    // generating a user id for registration
                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(userModel);
                                    // adding info database
                                    // pushing account id with info into user collection of database
                                    // creating another account with all information of registration


                                    // check registration successful or not
                                    Toast.makeText(registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(registration.this, login.class);
                                    // move to registration to login class
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(registration.this, "Registration Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    } else {
                        // if password ana re password not match reset the registration screen
                        Toast.makeText(registration.this, "Password not matched", Toast.LENGTH_SHORT).show(); // mis match pass
                        Intent intent = new Intent(getApplicationContext(), registration.class);
                        startActivity(intent); // restart screen
                    }
                }


            }


        });
        // Registration Button ends
        // pushing user information into database

    }
}