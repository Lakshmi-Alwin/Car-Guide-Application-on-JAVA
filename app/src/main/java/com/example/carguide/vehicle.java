package com.example.carguide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.Objects;


public class vehicle extends Fragment {

    View v;

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_vehicle,container,false);

        TextView profileName = v.findViewById(R.id.vehicle_page_name);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        ImageView vehicle = v.findViewById(R.id.car_vehiclepage);
        GestureDetector gdt = new GestureDetector(new GestureListner());
        vehicle.setOnTouchListener((view, motionEvent) -> {
            gdt.onTouchEvent(motionEvent);
            return true;
        });

        if(!PreferencesManager.sharedPreferences.contains(PreferencesManager.CLIENT_NAME))
            profileName.setText(googleSignInAccount.getDisplayName());
        else
            profileName.setText(PreferencesManager.getName());
        return v;
    }

    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private class GestureListner extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Intent intent = new Intent(v.getContext(), AddVehicle.class);
                startActivity(intent);
                Objects.requireNonNull(getActivity()).overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
            }
            return false;
        }
    }
}
