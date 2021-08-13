package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CommunityUpdate extends AppCompatActivity {
    private Button btn_com_cancel,btn_com_check,btn_com_img;
    private EditText edt_com_title,edt_com_text;
    private TextView tv_src_img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_update);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("커뮤니티 등록");
        actionBar.setDisplayHomeAsUpEnabled(true);

        btn_com_cancel = findViewById(R.id.btn_com_cancel);
        btn_com_check = findViewById(R.id.btn_com_check);
        btn_com_img = findViewById(R.id.btn_com_img);

        edt_com_title = findViewById(R.id.edt_com_title);
        edt_com_text = findViewById(R.id.edt_com_text);

        tv_src_img = findViewById(R.id.tv_src_img);
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