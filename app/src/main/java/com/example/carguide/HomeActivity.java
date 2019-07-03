package com.example.carguide;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        PreferencesManager.sharedAccountID = GoogleSignIn.getLastSignedInAccount(this).getId();
        PreferencesManager.sharedPreferences = getSharedPreferences(PreferencesManager.sharedAccountID, MODE_PRIVATE);
        PreferencesManager.sharedPreferencesEditor = PreferencesManager.sharedPreferences.edit();
        PreferencesManager.saveEmail(Objects.requireNonNull(GoogleSignIn.getLastSignedInAccount(this)).getEmail());

        final String[] vin = {PreferencesManager.getVIN()};
        if(vin[0].equals(""))
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new vehicle()).commit();
        else getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VehiclePage()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        final ImageView vehicle_tab_underline = findViewById(R.id.vehicle_tab_underline);
        final ImageView support_tab_underline = findViewById(R.id.support_tab_underline);
        final ImageView account_tab_underline = findViewById(R.id.account_tab_underline);
        support_tab_underline.setVisibility(View.GONE);
        account_tab_underline.setVisibility(View.GONE);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.car:
                    vehicle_tab_underline.setVisibility(View.VISIBLE);
                    support_tab_underline.setVisibility(View.GONE);
                    account_tab_underline.setVisibility(View.GONE);
                    vin[0] = PreferencesManager.getVIN();
                    selectedFragment = vin[0].equals("") ? new vehicle(): new VehiclePage();
                    break;
                case R.id.help:
                    vehicle_tab_underline.setVisibility(View.GONE);
                    support_tab_underline.setVisibility(View.VISIBLE);
                    account_tab_underline.setVisibility(View.GONE);
                    selectedFragment = new support();
                    break;
                case R.id.account:
                    vehicle_tab_underline.setVisibility(View.GONE);
                    support_tab_underline.setVisibility(View.GONE);
                    account_tab_underline.setVisibility(View.VISIBLE);
                    selectedFragment = new account();
                    Log.v(MainActivity.TAG, "account fragment");
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment, "profile_fragment_tag").commit();
            return true;
        });
    }
}
