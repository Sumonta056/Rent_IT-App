package com.example.rentit.orderScreens;

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
import com.example.rentit.productsData.UserProductTrack;
import com.example.rentit.menuScreens.menuScreen;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class status extends AppCompatActivity {

    // recycler View
    RecyclerView recyclerView;
    // recyler  View

    // database
    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter adapter;
    // database

    // data passing
    String emails;
    String type;
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
        type = intents.getStringExtra("type");
        // login info passing

        setContentView(R.layout.activity_status);

        // Initializing variable
        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.showProduct);

        //Query
        // getting the data from database
        Query query = null;
        if(type.equals("buy"))
        {
            query = firebaseFirestore.collection(emails)
                    .whereEqualTo("head", "Bought By You"); // collection name from database
            TextView textView = findViewById(R.id.textView10);
            textView.setText("My Orders");
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_bookmark_added_24, 0, 0, 0);



        }
        else
        {
            query = firebaseFirestore.collection(emails)
                    .whereEqualTo("head", "Rented By You"); // collection name from database
            TextView textView = findViewById(R.id.textView10);
            textView.setText("My Sales");
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_local_grocery_store_24, 0, 0, 0);
        }

        FirestoreRecyclerOptions<UserProductTrack> options = new FirestoreRecyclerOptions.Builder<UserProductTrack>()
                .setQuery(query, UserProductTrack.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<UserProductTrack, status.StatusProductViewHolder>(options) {
            @NonNull
            @Override
            public status.StatusProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list ,parent,false);
                return new status.StatusProductViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull status.StatusProductViewHolder holder, int position, @NonNull UserProductTrack model) {

                holder.name.setText(model.getName());
                holder.description.setText(model.getDescription());
                holder.rating.setText(model.getRating());
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

        Intent intent = new Intent(status.this, menuScreen.class);
        // pass login data to menuScreen
        intent.putExtra("emails", emails);
        // pass login data to menuScreen
        startActivity(intent); // go to sellBaseScreen screen

        super.onBackPressed();
    }
}