package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Pattern;

public class AddVehicle extends AppCompatActivity {

    ImageView vectorLeft, outerRing, midRing, innerRing, successfulVin;
    Button addVehicle;
    EditText vin, nickName;
    TextView result;
    RelativeLayout loading_layLayout;
    private static Pattern ALPHANUMERICUPPERCASE = Pattern.compile("^[A-Z0-9]*$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        vectorLeft = findViewById(R.id.vector_left);
        addVehicle = findViewById(R.id.addVehicle);
        vin = findViewById(R.id.vin);
        nickName  = findViewById(R.id.vehicle_nickname);
        outerRing = findViewById(R.id.outerRing);
        midRing = findViewById(R.id.midRing);
        innerRing = findViewById(R.id.innerRing);
        result = findViewById(R.id.vehicle_result);
        successfulVin = findViewById(R.id.successful_vin);
        loading_layLayout=findViewById(R.id.loading_layout);

        successfulVin.setVisibility(View.GONE);

        Animation outerAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        outerAnimation.setDuration(2000);
        outerAnimation.setRepeatCount(-1);
        outerAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return v;
            }
        });
        outerRing.setAnimation(outerAnimation);

        Animation middleAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        middleAnimation.setDuration(2000);
        middleAnimation.setRepeatCount(-1);
        middleAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return v;
            }
        });
        midRing.setAnimation(middleAnimation);

        Animation innerAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        innerAnimation.setDuration(2000);
        innerAnimation.setRepeatCount(-1);
        innerAnimation.setInterpolator(new Interpolator() {
            @Override
            public float getInterpolation(float v) {
                return v;
            }
        });
        innerRing.setAnimation(innerAnimation);

        loading_layLayout.setVisibility(View.GONE);

        vectorLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String editTextVin = vin.getText().toString();
                if(editTextVin.length() != 17)
                {
                    vin.setError("VIN should have 17 characters");
                    return;
                }
                if(!isAlphaNumericUpperCase(editTextVin))
                {
                    vin.setError("VIN should only contain uppercase letters and numbers");
                    return;
                }
                vectorLeft.setVisibility(View.GONE);
                vin.setVisibility(View.GONE);
                nickName.setVisibility(View.GONE);
                addVehicle.setVisibility(View.GONE);

                loading_layLayout.setVisibility(View.VISIBLE);
                loading_layLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loading_layLayout.setVisibility(View.GONE);
                    }
                },3000);

                successfulVin.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        successfulVin.setRotation(outerRing.getRotation());
                        successfulVin.setVisibility(View.VISIBLE);
                    }
                },3000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.SHAREDPREFERENCES, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("VIN", editTextVin);
                        editor.apply();
                        Intent intent = new Intent(AddVehicle.this, HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }, 6000);
            }
        });
    }

    private boolean isAlphaNumericUpperCase(String s) {
        return ALPHANUMERICUPPERCASE.matcher(s).find();
    }
}
