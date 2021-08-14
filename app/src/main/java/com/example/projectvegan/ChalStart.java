package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChalStart extends AppCompatActivity {
    private TextView btn_chal_cancel;
    private Button btn_chal_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chal_start);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("체험하기");

        actionBar.setDisplayHomeAsUpEnabled(true);

        btn_chal_cancel = findViewById(R.id.btn_chal_cancel);
        btn_chal_start = findViewById(R.id.btn_chal_start);

        SpannableString content = new SpannableString(btn_chal_cancel.getText().toString());
        content.setSpan(new UnderlineSpan(),0,content.length(),0);
        btn_chal_cancel.setText(content);

        btn_chal_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btn_chal_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChalStart.this);
                builder.setTitle("포기하기");
                builder.setMessage("포기하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        setResult(RESULT_CANCELED,intent);
                    }
                });
                builder.setNegativeButton("아니오",  null);
                builder.create().show();
            }
        });
    }

    //toolbar의 back키 눌렀을 때 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}