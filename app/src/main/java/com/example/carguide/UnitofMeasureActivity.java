package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;


public class UnitofMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitof_measure);
        ImageView left = findViewById(R.id.vector_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnitofMeasureActivity.super.onBackPressed();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        SharedPreferences preferences = getSharedPreferences(MainActivity.SHAREDPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        switch(view.getId()) {
            case R.id.radio_km:
                editor.putString("unitofmeasurement","Kilometer");
                if (checked)
                    break;
            case R.id.radio_miles:
                editor.putString("unitofmeasurement","Miles");
                if (checked)
                    break;
        }
    }
}
