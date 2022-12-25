package com.example.rentit.ui.home;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rentit.adapters.Products;
import com.example.rentit.R;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home,container,false);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = recyclerView.findViewById(R.id.free);

        //Query
        Query query = firebaseFirestore.collection("Products") ;

        FirestoreRecyclerOptions<Products> options = new FirestoreRecyclerOptions.Builder<Products>()
                .setQuery(query, Products.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Products, ProductsViewHolder>(options) {
            @NonNull
            @Override
            public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item ,parent ,false);
                return new ProductsViewHolder(view);

            }

            @Override
            protected void onBindViewHolder(@NonNull ProductsViewHolder holder, int position, @NonNull Products model) {
                holder.name.setText(model.getName());
                holder.description.setText(model.getDescription());
                holder.rating.setText(model.getRating());
                holder.price.setText(model.getPrice());

            }
        };
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);



        return root ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private class ProductsViewHolder extends RecyclerView.ViewHolder {

        TextView name , description , rating , price ;
        public ProductsViewHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.pop_name);
            description = itemView.findViewById(R.id.pop_des);
            rating = itemView.findViewById(R.id.pop_rat);
            price = itemView.findViewById(R.id.pop_rent_price);
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
}