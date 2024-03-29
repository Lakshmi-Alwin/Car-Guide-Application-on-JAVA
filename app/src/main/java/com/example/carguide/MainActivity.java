package com.example.carguide;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity {

    public static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;

    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    int currentPage = 0;
    Timer timer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG,"before pager");
        setContentView(R.layout.activity_main);
        mPager = findViewById(R.id.pager);
        ScreenSlidePagerAdapter pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());


        mainActFragment mainFragment_1 = new mainActFragment(R.drawable.slider_image1,
                getResources().getString(R.string.complete_co), getResources().getString(R.string.jedi_like_p));
        mainActFragment mainFragment_2 = new mainActFragment(R.drawable.slider2,
                getResources().getString(R.string.guides), getResources().getString(R.string.we_got));
        mainActFragment mainFragment_3 = new mainActFragment(R.drawable.slider3,
                getResources().getString(R.string.assistance), getResources().getString(R.string.get_support));



        pagerAdapter.addFragment(mainFragment_1, "ONE");
        pagerAdapter.addFragment(mainFragment_2, "TWO");
        pagerAdapter.addFragment(mainFragment_3, "THREE");
        mPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mPager, true);
        Log.v(TAG,"after pager");

        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == NUM_PAGES) {
                currentPage = 0;
            }
            mPager.setCurrentItem(currentPage++, true);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500,3000);

        SignInButton googleSignInButton = findViewById(R.id.sign_in_button);
        GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
        builder.requestEmail();
        GoogleSignInOptions gso = builder.build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener(v -> {
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, 101);
        });

        Log.v(TAG,"on Create");

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(TAG, "onActivityResult");

        if (resultCode == Activity.RESULT_OK)
            if (requestCode == 101) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                PreferencesManager.sharedAccountID = account.getId();
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                Intent intent = new Intent(this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {


        private final List<Fragment> mList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();

        ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        void addFragment(Fragment fragment, String title) {
            mList.add(fragment);
            mTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
