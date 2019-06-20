package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

import static android.app.PendingIntent.getActivity;

public class UnitofMeasureActivity extends AppCompatActivity {

    //Context context = getActivity();
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private ImageView left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitof_measure);
        left=findViewById(R.id.vector_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnitofMeasureActivity.super.onBackPressed();
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        preferences = getSharedPreferences(MainActivity.SHAREDPREFERENCES, Context.MODE_PRIVATE);
        editor = preferences.edit();
        switch(view.getId()) {
            case R.id.radio_km:
                editor.putString("unitofmeasurement","Kilometer");
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radio_miles:
                editor.putString("unitofmeasurement","Miles");
                if (checked)
                    // Ninjas rule
                    break;
        }
    }
}
