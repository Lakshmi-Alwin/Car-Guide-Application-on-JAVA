package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;


public class UnitofMeasureActivity extends AppCompatActivity {

    private RadioButton km_button;
    private RadioButton mi_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unitof_measure);
        ImageView left = findViewById(R.id.vector_left);
        left.setOnClickListener(v -> UnitofMeasureActivity.super.onBackPressed());

        km_button = findViewById(R.id.radio_km);
        mi_button = findViewById(R.id.radio_miles);

        setRadioButtons();
    }

    public void setRadioButtons() {
        String chosenOption = PreferencesManager.getUnitOfMeasurement();
        if (chosenOption.equals("Kilometer")) {
            km_button.setChecked(true);
        }
        else if(chosenOption.equals("Miles")) {
            mi_button.setChecked(true);
        }
        else {
            km_button.setChecked(true);
            PreferencesManager.saveUnitOfMeasurement("Kilometer");
        }
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
