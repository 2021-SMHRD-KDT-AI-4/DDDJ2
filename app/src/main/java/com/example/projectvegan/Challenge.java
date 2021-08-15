package com.example.projectvegan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Calendar;

public class Challenge extends AppCompatActivity {
    private Button btn_notice,btn_ex,btn_chal;
    private TextView tv_ex_ok,tv_chal_ok;

    Calendar cal = Calendar.getInstance();
    private String today =cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("챌린지");

        actionBar.setDisplayHomeAsUpEnabled(true);

        btn_notice = findViewById(R.id.btn_notice);
        btn_ex = findViewById(R.id.btn_ex);
        btn_chal = findViewById(R.id.btn_chal);

        tv_ex_ok = findViewById(R.id.tv_ex_ok);
        tv_chal_ok = findViewById(R.id.tv_chal_ok);


        String id = PreferenceManager.getString(getApplicationContext(), "id");
        String category = PreferenceManager.getString(getApplicationContext(), "category");
        String startEx = PreferenceManager.getString(getApplicationContext(), "startEx");
        String startChal = PreferenceManager.getString(getApplicationContext(), "startChal");

        if(!startEx.equals("데이터가 없습니다.")){
            tv_ex_ok.setVisibility(View.VISIBLE);
        }else{
            tv_ex_ok.setVisibility(View.INVISIBLE);
        }

        if(!startChal.equals("데이터가 없습니다.")){
            tv_chal_ok.setVisibility(View.VISIBLE);
        }else {
            tv_chal_ok.setVisibility(View.INVISIBLE);
        }

        btn_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Notice.class));
            }
        });
        btn_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!category.equals("채식 경험 없음")){
                    Toast.makeText(getApplicationContext(),"채식 경험 없는 경우 도전 가능합니다.",Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Challenge.this);
                    builder.setTitle("7일 체험하기");
                    builder.setMessage("채식 단계를 확인하러 가시겠습니까?.");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), ChalEx.class);
                            startActivityForResult(intent, 1000);
                        }
                    });
                    builder.setNegativeButton("아니오", null);
                    builder.create().show();
                }
            }
        });
        btn_chal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Challenge.this);
                builder.setTitle("30일 챌린지");
                builder.setMessage("30일 동안 채식에 도전해보세요");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(),ChalStart.class);
                        startActivityForResult(intent,1001);
                    }
                });
                builder.setNegativeButton("아니오",  null);
                builder.create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if(requestCode==1000 && resultCode == RESULT_OK){
            tv_ex_ok.setVisibility(View.VISIBLE);
        }else if(requestCode==1001 && resultCode == RESULT_OK){
            tv_chal_ok.setVisibility(View.VISIBLE);
        }else if (requestCode==1000 && resultCode == RESULT_CANCELED){
            tv_ex_ok.setVisibility(View.INVISIBLE);
        }else if (requestCode==1001 && resultCode == RESULT_CANCELED){
            tv_chal_ok.setVisibility(View.INVISIBLE);
        }

        super.onActivityResult(requestCode, resultCode, data);
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