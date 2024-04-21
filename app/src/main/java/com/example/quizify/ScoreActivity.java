package com.example.quizify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    TextView tv_percentage, tv_correct_answers;
    Button btnRetake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        init();

        // Retrieve percentage and other information from the intent
        int percentage = getIntent().getIntExtra("percentage", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);
        int correctAnswers = getIntent().getIntExtra("correctAnswers", 0);

        // Display percentage and other information
        tv_percentage.setText("Percentage: " + percentage + "%");
        tv_correct_answers.setText("Correct Answers: " + correctAnswers + "/" + totalQuestions);

        btnRetake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScoreActivity.this, Dashboard.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void init() {
        tv_percentage = findViewById(R.id.tv_percentage);
        tv_correct_answers = findViewById(R.id.tv_correct_answers);
        btnRetake = findViewById(R.id.btnRetake);
    }
}
