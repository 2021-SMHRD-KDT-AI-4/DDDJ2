package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioGroup;

public class Quiz extends AppCompatActivity {

    private int[] quizPage = {R.layout.quiz1,R.layout.quiz2,R.layout.quiz3,R.layout.activity_quiz};

    private Button btn_quiz;
    private RadioGroup rg_quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int num = intent.getIntExtra("ranNum",0);

        setContentView(quizPage[num]);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("비건 퀴즈");

        actionBar.setDisplayHomeAsUpEnabled(true);

        btn_quiz = findViewById(R.id.btn_quiz);
        rg_quiz = findViewById(R.id.rg_quiz);




    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}