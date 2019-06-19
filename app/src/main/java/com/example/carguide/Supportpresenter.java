package com.example.carguide;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;


public class Supportpresenter implements SupportContract.Presenter  {

    private SupportContract.View view;

    public Supportpresenter(SupportContract.View view) {
        this.view = view;
    }

    @Override
    public void onClickSupport(FragmentActivity a) {
        if (ActivityCompat.checkSelfPermission(a, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.CALL_PHONE}, 1);
            Log.v(MainActivity.TAG, "No permission to call");
            return;
        }
    }
}
