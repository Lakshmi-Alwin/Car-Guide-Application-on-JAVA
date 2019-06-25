package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;
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
        left.setOnClickListener(v -> UnitofMeasureActivity.super.onBackPressed());
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_km:
                PreferencesManager.saveUnitOfMeasurement("Kilometer");
                if (checked)
                    break;
            case R.id.radio_miles:
                PreferencesManager.saveUnitOfMeasurement("Miles");
                if (checked)
                    break;
        }
    }
}
