package com.example.carguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FAQActivity extends AppCompatActivity {

    public static final String QUESTIONSNO = "Question NO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        int position = getIntent().getIntExtra(QUESTIONSNO,-1);
        TextView question = findViewById(R.id.faqQuestion);
        TextView answer = findViewById(R.id.faqAnswer);
        String quesList[] = getResources().getStringArray(R.array.questions);
        String ansList[] = getResources().getStringArray(R.array.answers);

        question.setText(quesList[position]);
        answer.setText(ansList[position]);
    }
}
