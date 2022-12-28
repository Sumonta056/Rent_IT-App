package com.example.rentit;

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

import com.example.rentit.menuScreens.freeScreen;
import com.example.rentit.menuScreens.menu;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    // recycler View
    RecyclerView recyclerView;
    // recyler  View

    // database
    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter adapter;
    // database

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

        // login info passing
        Intent intents = getIntent();
        emails = intents.getStringExtra("emails");
        // login info passing

        setContentView(R.layout.activity_main);

        // Initializing variable
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.showProduct);

        //Query
        // getting the data from database
        Query query = firebaseFirestore.collection(emails); // collection name from database

        FirestoreRecyclerOptions<UserProductTrack> options = new FirestoreRecyclerOptions.Builder<UserProductTrack>()
                .setQuery(query, UserProductTrack.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<UserProductTrack, MainActivity.StatusProductViewHolder>(options) {
            @NonNull
            @Override
            public MainActivity.StatusProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list ,parent,false);
                return new MainActivity.StatusProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MainActivity.StatusProductViewHolder holder, int position, @NonNull UserProductTrack model) {

                holder.name.setText(model.getName());
                holder.description.setText(model.getDescription());
                holder.rating.setText(model.getRating());
                holder.head.setText(model.getHead());
                holder.type.setText(model.getType());
                holder.address.setText(model.getAddress());


                String img ;
                img = model.getImage();
                Picasso.get().load(img).into(holder.image);

            }
        };

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private class StatusProductViewHolder extends RecyclerView.ViewHolder {

        TextView head , name , description , rating , address,type;
        ImageView image;
        public StatusProductViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.user_name);
            description = itemView.findViewById(R.id.user_des);
            rating = itemView.findViewById(R.id.user_rating);
            head = itemView.findViewById(R.id.user_head);
            type = itemView.findViewById(R.id.user_type);
            address = itemView.findViewById(R.id.user_price_address);
            image = itemView.findViewById(R.id.user_image);
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

        Intent intent = new Intent(MainActivity.this, menu.class);
        // pass login data to menu
        intent.putExtra("emails", emails);
        // pass login data to menu
        startActivity(intent); // go to sell screen

        super.onBackPressed();
    }
}