package com.example.quizify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class Questions extends AppCompatActivity {

    TextView tvquestions;
    RadioGroup radioGroup;
    Button btnPrevious, btnNext, btnSubmit;
    String[][] currentOptions;
    String[] currentQuestions;
    int[] selectedOptions;
    int currentIndex = 0;

    private final String[] oopQuestions = {"1. Which of the following is NOT a pillar of OOP?",
            "2. What is the purpose of a constructor in a class?",
            "3. What is the relationship between a superclass and a subclass in inheritance?"};

    private final String[][] oopOptions = {
            {"Encapsulation", "Inheritance", "Polymorphism", "Iteration"},
            {"To initialize the state (attributes) of an object.", "To define the behavior of the class.",
                    "To handle user input for the object.", "To connect the object to a database."},
            {"The subclass inherits properties and behavior from the superclass.",
                    "They are both specializations of the same abstract class.",
                    "They are independent classes with no relation.",
                    "The superclass inherits properties from the subclass."}};

    private final int[] oopCorrectIndices = {3, 1, 2}; // Correct answer indices for OOP questions

    private final String[] dsaQuestions = {"1. What data structure offers constant time (O(1)) average access for any element?",
            "2. What is the time complexity of inserting an element at the end of a singly linked list?",
            "3. What is the difference between a DFS (Depth-First Search) and BFS (Breadth-First Search) algorithm?"};

    private final String[][] dsaOptions = {
            {"Array", "Linked List", "Stack", "Hash Table"},
            {"O(n) (requires iterating through the list to find the end)",
                    "O(1) (constant time operation)", "O(log n)",
                    "O(log log n)"},
            {"DFS explores neighboring nodes as deeply as possible before moving to the next level.",
                    "DFS requires a stack data structure, while BFS uses a queue.",
                    "DFS is used for sorting, while BFS is used for searching.",
                    "All of the above are true."}};

    private final int[] dsaCorrectIndices = {3, 2, 3}; // Correct answer indices for DSA questions

    private final String[] databaseQuestions = {"1. What is the difference between a primary key and a foreign key in a relational database?",
            "2. What are the different types of joins used in SQL queries to combine data from multiple tables?",
            "3. What is the purpose of indexing in a database?"};

    private final String[][] databaseOptions = {
            {"A primary key uniquely identifies a row in a table, while a foreign key references the primary key of another table to establish relationships.",
                    "Both primary and foreign keys are used for data security.",
                    "A primary key can have multiple columns, while a foreign key can only have one.",
                    "There is no difference; they are interchangeable terms."},
            {" Inner join: returns rows where there's a match in both tables, Left join: returns all rows from the left table and matching rows from the right.",
                    "All of the above are correct descriptions of different join types in SQL.",
                    "There are only two types of joins: simple join and complex join.,",
                    "Joins are used to update data in multiple tables simultaneously."},
            {"To compress data and reduce storage requirements.",
                    "To improve the efficiency of data retrieval by enabling faster searching.",
                    "To enforce data integrity and prevent duplicate entries.",
                    "To encrypt sensitive data for security reasons."}};

    private final int[] databaseCorrectIndices = {0, 1, 1}; // Correct answer indices for Database questions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        init();

        String category = getIntent().getStringExtra("Category");

        switch (Objects.requireNonNull(category)) {
            case "OOP":
                currentQuestions = oopQuestions;
                currentOptions = oopOptions;
                selectedOptions = new int[currentQuestions.length];
                // Initialize with -1 to indicate no selection
                for (int i = 0; i < selectedOptions.length; i++) {
                    selectedOptions[i] = -1;
                }
                break;
            case "DSA":
                currentQuestions = dsaQuestions;
                currentOptions = dsaOptions;
                selectedOptions = new int[currentQuestions.length];
                for (int i = 0; i < selectedOptions.length; i++) {
                    selectedOptions[i] = -1;
                }
                break;
            case "DATABASE":
                currentQuestions = databaseQuestions;
                currentOptions = databaseOptions;
                selectedOptions = new int[currentQuestions.length];
                for (int i = 0; i < selectedOptions.length; i++) {
                    selectedOptions[i] = -1;
                }
                break;
        }

        displayCurrentQuestion();

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = Math.max(0, currentIndex - 1);
                displayCurrentQuestion();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = Math.min(currentQuestions.length - 1, currentIndex + 1);
                displayCurrentQuestion();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitQuiz();
            }
        });
    }

    private void init() {
        tvquestions = findViewById(R.id.tvquestions);
        radioGroup = findViewById(R.id.radio_group);
        btnPrevious = findViewById(R.id.btn_previous);
        btnNext = findViewById(R.id.btn_next);
        btnSubmit = findViewById(R.id.btn_submit);
    }

    private void displayCurrentQuestion() {
        tvquestions.setText(currentQuestions[currentIndex]);
        radioGroup.removeAllViews();
        for (int i = 0; i < currentOptions[currentIndex].length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(currentOptions[currentIndex][i]);
            radioButton.setId(i);
            radioButton.setTextColor(getResources().getColor(R.color.black)); // Set text color to black
            radioGroup.addView(radioButton);
        }
        if (selectedOptions[currentIndex] != -1) {
            radioGroup.check(selectedOptions[currentIndex]);
        } else {
            radioGroup.clearCheck();
        }
        updateButtonVisibility();
    }

    private void updateButtonVisibility() {
        btnPrevious.setVisibility(currentIndex > 0 ? View.VISIBLE : View.GONE);
        btnNext.setVisibility(currentIndex < currentQuestions.length - 1 ? View.VISIBLE : View.GONE);
        btnSubmit.setVisibility(currentIndex == currentQuestions.length - 1 ? View.VISIBLE : View.GONE);
    }

    private void submitQuiz() {
        int totalQuestions = currentQuestions.length;
        int correctAnswers = 0;

        // Loop through all questions to check correctness
        for (int i = 0; i < totalQuestions; i++) {
            // If selected option matches the correct index
            if (selectedOptions[i] == getCorrectIndex(i)) {
                correctAnswers++;
            }
        }

        // Calculate the percentage of correct answers
        int percentage = (correctAnswers * 100) / totalQuestions;

        // Pass the percentage and other information to the next activity
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra("percentage", percentage);
        intent.putExtra("totalQuestions", totalQuestions);
        intent.putExtra("correctAnswers", correctAnswers);
        startActivity(intent);
    }

    private int getCorrectIndex(int questionIndex) {
        String category = getIntent().getStringExtra("Category");
        Objects.requireNonNull(category, "Category cannot be null");
        switch (category) {
            case "OOP":
                return oopCorrectIndices[questionIndex];
            case "DSA":
                return dsaCorrectIndices[questionIndex];
            case "DATABASE":
                return databaseCorrectIndices[questionIndex];
            default:
                return -1; // Invalid category
        }
    }

    public void onRadioButtonClicked(View view) {
        selectedOptions[currentIndex] = view.getId();
    }
}
