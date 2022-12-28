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
import android.widget.ImageView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.example.rentit.order.orderFree;
import com.example.rentit.R;
import com.example.rentit.productsData.Free;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class freeScreen extends AppCompatActivity {

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

         //hide the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        // hide the title bar

        // login info passing
        Intent intents = getIntent();
        emails = intents.getStringExtra("emails");
        // login info passing

        setContentView(R.layout.activity_free_screen);

        // Initializing variable
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.check2);

        //Query
        // getting the data from database
        Query query = firebaseFirestore.collection("Free"); // collection name from database

        FirestoreRecyclerOptions<Free> options = new FirestoreRecyclerOptions.Builder<Free>()
                .setQuery(query, Free.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Free , freeScreen.FreeProductViewHolder>(options) {
            @NonNull
            @Override
            public freeScreen.FreeProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.free_items , parent,false);
                return new freeScreen.FreeProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull freeScreen.FreeProductViewHolder holder, int position, @NonNull Free model) {

                holder.name.setText(model.getName());
                holder.description.setText(model.getDescription());
                holder.rating.setText(model.getRating());
                holder.address.setText(model.getAddress());


                String img;
                img = model.getImage();
                Picasso.get().load(img).into(holder.image);

                // switch to next screen selecting any image
                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext() , orderFree.class);
                        // passing data
                        intent.putExtra("emails" , emails);
                        intent.putExtra("name",model.getName());
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("rating",model.getRating());
                        intent.putExtra("address",model.getAddress());
                        intent.putExtra("image",img);
                        // passing data
                        startActivity(intent);
                    }
                });
                // switch to next screen selecting any image
            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private class FreeProductViewHolder extends RecyclerView.ViewHolder {
        TextView name , description , rating, address;
        ImageView image ;
        public FreeProductViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.free_name);
            description = itemView.findViewById(R.id.free_des);
            rating = itemView.findViewById(R.id.free_rat);
            address = itemView.findViewById(R.id.free_address);
            image = itemView.findViewById(R.id.free_image);
        }
    }
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

    public void onBackPressed() {

        Intent intent = new Intent(freeScreen.this, menu.class);
        // pass login data to menu
        intent.putExtra("emails", emails);
        // pass login data to menu
        startActivity(intent); // go to sell screen

        super.onBackPressed();
    }
}

