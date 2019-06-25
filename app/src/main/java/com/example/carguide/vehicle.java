package com.example.carguide;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


public class vehicle extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_vehicle,container,false);

        TextView profileName = v.findViewById(R.id.vehicle_page_name);
        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        ImageView vehicle = v.findViewById(R.id.car_vehiclepage);
        vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddVehicle.class);
                startActivity(intent);
            }
        });
        if(!PreferencesManager.sharedPreferences.contains(PreferencesManager.CLIENT_NAME))
            profileName.setText(googleSignInAccount.getDisplayName());
        else
            profileName.setText(PreferencesManager.getName());
        return v;
    }
}
