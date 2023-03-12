package com.example.rentit.menuScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.rentit.R;

import java.util.Objects;

public class aboutScreen extends AppCompatActivity {

    // login info tracking
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

        setContentView(R.layout.activity_about_us);

        // login info passing
        Intent intents = getIntent();
        emails = intents.getStringExtra("emails");
        // login info passing
    }

    @Override

    // move to menuScreen screen if back key pressed
    public void onBackPressed() {

        Intent intent = new Intent(aboutScreen.this, menuScreen.class);
        // pass login data to menuScreen
        intent.putExtra("emails", emails);
        // pass login data to menuScreen
        startActivity(intent); // go to meny screen

        super.onBackPressed();
    }
}







/*
package com.example.rentit.menuScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentit.R;
import com.example.rentit.productsData.Products;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class buyScreen extends AppCompatActivity {

    // recycler View
    RecyclerView recyclerView;
    // recyler  View

    // database
    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter adapter;
    // database

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

        // login info passing
        Intent intents = getIntent();
        emails = intents.getStringExtra("emails");
        // login info passing

        // accessing the buy screen layout
        setContentView(R.layout.activity_buy_screen);

        // Initializing variable
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.check);

        //Query
        // getting the data from database
        Query query = firebaseFirestore.collection("Products"); // collection name from database

        // database fetching starts
        FirestoreRecyclerOptions<Products> options = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(query, Products.class)
                .build();
        // firebase adapter for accessing data
        adapter = new FirestoreRecyclerAdapter<Products, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // view base on layout popular item xml
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item ,parent ,false);
                return new ProductsViewHolder(view);

            }
            @Override
            // what to get from database
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull Products model) {

                // setting and getting information from database
                holder.name.setText(model.getName());
                holder.description.setText(model.getDescription());
                holder.rating.setText(model.getRating());
                holder.price.setText(model.getPrice());
                // setting and getting information from database

                // loading image from database
                String imageUrl ;
                imageUrl = model.getImage();
                Picasso.get().load(imageUrl).into(holder.image);
                // loading image from database

            }
        };
        // database fetching done

        // recycler View setting after accessing data
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        // recycler View setting after accessing data
    }

    // getting the id's of recycle view items
    private class ProductsViewHolder extends RecyclerView.ViewHolder {
        TextView name , description , rating , price ;
        ImageView image;
        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            rating = itemView.findViewById(R.id.pop_rat);
            price = itemView.findViewById(R.id.pop_rent_price);
            image = itemView.findViewById(R.id.image);
        }
    }

    // faster execution
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    // faster execution

    public void onBackPressed() {

        Intent intent = new Intent(buyScreen.this, menuScreen.class);
        // pass login data to menuScreen
        intent.putExtra("emails", emails);
        // pass login data to menuScreen
        startActivity(intent); // go to sellBaseScreen screen

        super.onBackPressed();
    }
}
 */