package com.example.rentit.ui.Profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.rentit.R;

public class ProfileFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profilee,container,false);
        return root ;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}