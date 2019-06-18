package com.example.carguide;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends FragmentActivity {

    public static final String SHAREDPREFERENCES = "MyPREFERENCES";
    public static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;
    private SignInButton googleSignInButton;

    private static final int NUM_PAGES = 3;
    private ViewPager mPager;
    private PagerAdapter pagerAdapter;
    int currentPage = 0;
    Timer timer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(TAG,"before pager");
        setContentView(R.layout.activity_main);
        mPager = findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new mainActFragment1(), "ONE");
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new mainActFragment2(), "TWO");
        ((ScreenSlidePagerAdapter) pagerAdapter).addFragment(new mainActFragment3(), "THREE");
        mPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(mPager, true);
        Log.v(TAG,"after pager");

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 500,3000);

        googleSignInButton = findViewById(R.id.sign_in_button);
        GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
        builder.requestEmail();
        GoogleSignInOptions gso = builder.build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });

        Log.v(TAG,"on Create");

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v(TAG, "onActivityResult");

        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    // The Task returned from this call is always completed, no need to attach
                    // a listener.
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.finish();
       // System.exit(1);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {


        private final List<Fragment> mList = new ArrayList<>();
        private final List<String> mTitleList = new ArrayList<>();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
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
