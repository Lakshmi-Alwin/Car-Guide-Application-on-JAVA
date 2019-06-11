package com.example.carguide;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.carguide.account.GOOGLE_ACCOUNT;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new vehicle()).commit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

      @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.car:
                selectedFragment = new vehicle();
                break;
            case R.id.help:
                selectedFragment = new support();
                break;
            case R.id.account:
                selectedFragment = new account();
                Bundle args = new Bundle();
                Log.v(MainActivity.TAG, "acount fragemnt");
                args.putParcelable(GOOGLE_ACCOUNT, getIntent().getParcelableExtra(GOOGLE_ACCOUNT));
                selectedFragment.setArguments(args);
                break;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;

    }

    });
}

    @Override
    public void onBackPressed() {
    }
}
