package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TermsandPrivacyActivity extends AppCompatActivity {

    private ImageView left;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsand_privacy);
        left=findViewById(R.id.vector_left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermsandPrivacyActivity.super.onBackPressed();
            }
        });
    }
}
