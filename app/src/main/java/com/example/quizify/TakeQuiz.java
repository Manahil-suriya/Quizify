package com.example.quizify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TakeQuiz extends AppCompatActivity {

    Button btnoop,btndsa,btndatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_take_quiz);
        init();
        btnoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TakeQuiz.this,Questions.class);
                i.putExtra("Category","OOP");
                startActivity(i);
            }
        });

        btndsa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TakeQuiz.this,Questions.class);
                i.putExtra("Category","DSA");
                startActivity(i);
            }
        });

        btndatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TakeQuiz.this,Questions.class);
                i.putExtra("Category","DATABASE");
                startActivity(i);
            }
        });

    }
    private void init(){

        btnoop=findViewById(R.id.btnoop);
        btndsa=findViewById(R.id.btndsa);
        btndatabase=findViewById(R.id.btndatabase);


    }
}