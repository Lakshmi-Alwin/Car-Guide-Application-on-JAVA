package com.example.carguide;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FAQActivity extends AppCompatActivity {

    public static final String QUESTIONSNO = "Question NO";

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        int position = getIntent().getIntExtra(QUESTIONSNO,-1);
        TextView question = findViewById(R.id.faqQuestion);
        TextView answer = findViewById(R.id.faqAnswer);
        String[] quesList = getResources().getStringArray(R.array.questions);
        String[] ansList = getResources().getStringArray(R.array.answers);

        question.setText(quesList[position]);
        answer.setText(ansList[position]);
        switch (position){
            case 0:
                answer.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.start_engine_selected, 0);
                break;
            case 1:
                answer.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lock_selected, 0, R.drawable.unlocked_selected, 0);
                break;
            case 2:
                answer.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.triangle, 0);
                break;
        }
        ImageView vector = findViewById(R.id.vector_left);
        vector.setOnClickListener(v -> FAQActivity.super.onBackPressed());
    }
}
