package com.example.rentit.ui.Category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.rentit.R;


public class CategoryFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categorye,container,false);
        return root ;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}