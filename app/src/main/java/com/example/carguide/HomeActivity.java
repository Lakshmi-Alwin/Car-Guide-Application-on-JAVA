package com.example.carguide;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        MainActivity.SHAREDPREFERENCES = "MyPREFERENCES" + GoogleSignIn.getLastSignedInAccount(this).getId();
        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHAREDPREFERENCES, MODE_PRIVATE);
        final String vin = sharedPreferences.getString("VIN", "");
        if(vin.length() == 0)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new vehicle()).commit();
        else getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new VehiclePage()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        final ImageView vehicle_tab_underline = findViewById(R.id.vehicle_tab_underline);
        final ImageView support_tab_underline = findViewById(R.id.support_tab_underline);
        final ImageView account_tab_underline = findViewById(R.id.account_tab_underline);
        support_tab_underline.setVisibility(View.GONE);
        account_tab_underline.setVisibility(View.GONE);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.car:
                        vehicle_tab_underline.setVisibility(View.VISIBLE);
                        support_tab_underline.setVisibility(View.GONE);
                        account_tab_underline.setVisibility(View.GONE);
                        selectedFragment = vin.length() == 0 ? new vehicle(): new VehiclePage();
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
                        Log.v(MainActivity.TAG, "acount fragemnt");
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                return true;

            }

        });
    }
}
