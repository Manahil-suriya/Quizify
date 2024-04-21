package com.example.quizify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etname, etstudentid;
    Button btnlogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        init();


       btnlogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name = Objects.requireNonNull(etname.getText()).toString().trim();
               String id = Objects.requireNonNull(etstudentid.getText()).toString().trim();

               int flag = 0;
               if (name.isEmpty()) {
                   flag = 1;

                   Toast.makeText(MainActivity.this, "Please Enter name", Toast.LENGTH_SHORT).show();

               }

               if (id.isEmpty()) {

                   flag = 1;

                   Toast.makeText(MainActivity.this, "Please Enter id", Toast.LENGTH_SHORT).show();
               }
               if (flag == 0) {

                   Intent i = new Intent(MainActivity.this, Dashboard.class);

                   i.putExtra("key_name", name);
                   i.putExtra("key_id", id);

                   startActivity(i);
                   finish();
               }
           }
       });

    }
    private void init(){
        etname=findViewById(R.id.etname);
        etstudentid=findViewById(R.id.etstudentid);
        btnlogin=findViewById(R.id.btnlogin);

    }



}