package com.example.rentit;

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

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class registration extends AppCompatActivity {

    // Button
    private Button back;
    private MaterialButton move2;
    private MaterialButton move3;
    // Button

    // database
    DBHelper DB;
    EditText username ;
    EditText pass;
    EditText email;
    EditText address;
    EditText repass;
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
                Intent intent = new Intent(registration.this , login.class );
                startActivity(intent);
            }
        });
        // back Button end

        // registration Button starts
        DB = new DBHelper(this);
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        repass = findViewById(R.id.passRe);
        move2 = findViewById(R.id.reg);

        move2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String password = pass.getText().toString();
                String emails = email.getText().toString();
                String adr = address.getText().toString();
                String pasRe = repass.getText().toString();

                // check all info given or not
                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(pasRe)|| TextUtils.isEmpty(emails)||TextUtils.isEmpty(adr))
                    Toast.makeText(registration.this, "Please! Fill Up All Information", Toast.LENGTH_SHORT).show();

                else
                {
                    // check both pass and repass same or not
                    if(password.equals(pasRe)) {
                        Boolean checkuser = DB.checkusername(user);
                        // check duplicate user name
                        if (!checkuser) {
                            // insert all data to database
                            Boolean insert = DB.insertData(user, password, pasRe, emails, adr);
                            if (insert) {
                                Toast.makeText(registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), login.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(registration.this, "Registration Failed", Toast.LENGTH_SHORT).show(); // registration failed
                            }
                        } else {
                            Toast.makeText(registration.this, "Already Have an account ! Try login", Toast.LENGTH_SHORT).show(); // Same user found
                            Intent intent = new Intent(getApplicationContext(), login.class);
                            startActivity(intent); // go to login

                        }
                    }
                    else
                    {
                        Toast.makeText(registration.this, "Password not matched", Toast.LENGTH_SHORT).show(); // mis match pass
                        Intent intent = new Intent(getApplicationContext(), registration.class);
                        startActivity(intent); // restart screen
                    }
                }


            }
        });
        // Registration Button ends


    }
}