package com.example.carguide;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private TextView profileName;
    private ImageView car;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_vehicle,container,false);

        profileName = v.findViewById(R.id.vehiclepage_name);
        car=v.findViewById(R.id.car_vehiclepage);
        /*car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddVehicle.class);
                startActivity(intent);
            }
        });*/

        GoogleSignInAccount googleSignInAccount = GoogleSignIn.getLastSignedInAccount(getActivity());
        SharedPreferences preferences = getActivity().getSharedPreferences(MainActivity.SHAREDPREFERENCES, Context.MODE_PRIVATE);
        if(!preferences.contains("clientname"))
            profileName.setText(googleSignInAccount.getDisplayName());
        else
            profileName.setText(preferences.getString("clientname", ""));
        return v;
    }
}
