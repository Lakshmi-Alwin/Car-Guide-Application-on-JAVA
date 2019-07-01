package com.example.carguide;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import static android.view.View.GONE;

public class VehiclePage extends Fragment {

    private ImageView lockedSelected, unlockedSelected, lockedNotSelected, unlockedNotSelected, startEngineSelected, startEngineNotSelected;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_page, container, false);
        lockedSelected = view.findViewById(R.id.locked_selected);
        unlockedSelected = view.findViewById(R.id.unlocked_selected);
        lockedNotSelected = view.findViewById(R.id.locked_not_selected);
        unlockedNotSelected = view.findViewById(R.id.unlocked_not_selected);
        startEngineNotSelected = view.findViewById(R.id.start_engine_not_selected);
        startEngineSelected = view.findViewById(R.id.start_engine_selected);
        unlockedSelected.setVisibility(GONE);
        unlockedNotSelected.setVisibility(View.VISIBLE);
        lockedNotSelected.setVisibility(GONE);
        lockedSelected.setVisibility(View.VISIBLE);
        startEngineSelected.setVisibility(GONE);

        lockedSelected.setOnClickListener(view1 -> {
            lockedSelected.setVisibility(GONE);
            lockedNotSelected.setVisibility(View.VISIBLE);
            unlockedNotSelected.setVisibility(GONE);
            unlockedSelected.setVisibility(View.VISIBLE);
            startEngineNotSelected.setVisibility(GONE);
            startEngineSelected.setVisibility(View.VISIBLE);
        });

        unlockedSelected.setOnClickListener(view1 -> {
            lockedSelected.setVisibility(View.VISIBLE);
            lockedNotSelected.setVisibility(GONE);
            unlockedNotSelected.setVisibility(View.VISIBLE);
            unlockedSelected.setVisibility(GONE);
            startEngineNotSelected.setVisibility(View.VISIBLE);
            startEngineSelected.setVisibility(GONE);
        });

        startEngineSelected.setOnClickListener(view2 -> Toast.makeText(view.getContext() , "Engine is starting", Toast.LENGTH_SHORT).show());

        startEngineNotSelected.setOnClickListener(view2 -> Toast.makeText(view.getContext() , "Please Lock the vehicle", Toast.LENGTH_SHORT).show());

        return view;
    }
}
