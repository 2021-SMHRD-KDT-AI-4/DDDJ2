package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;

public class MyPage extends AppCompatActivity {
    private int[] color_array = {Color.rgb(76,175,80),Color.rgb(139,195,74),
                                 Color.rgb(170,182,48),Color.rgb(211,189,4),
                                 Color.rgb(188,141,3)};

    private TextView tv_breakfast,tv_lunch,tv_dinner,tv_my_cal_date;
    private ImageView edit_info;
    private Button btn_my_calendar;

    private int breakfast,lunch,dinner = 0;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        // 액션바 설정
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("마이페이지");

        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_breakfast = findViewById(R.id.tv_breakfast);
        tv_lunch = findViewById(R.id.tv_lunch);
        tv_dinner = findViewById(R.id.tv_dinner);
        btn_my_calendar = findViewById(R.id.btn_my_calendar);

        tv_my_cal_date = findViewById(R.id.tv_my_cal_date);
        tv_my_cal_date.setText(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE));

        edit_info = findViewById(R.id.edit_info);

        // 아침 정보
        tv_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_breakfast.getText().toString().equals("kcal")){
                    Intent intent = new Intent(getApplicationContext(),SearchItem.class);
                    startActivity(intent);
                }else {
                    breakfast = Integer.parseInt(tv_breakfast.getText().toString());

                }
            }
        });

        // 점심 정보
        tv_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_lunch.getText().toString().equals("kcal")){
                    Intent intent = new Intent(getApplicationContext(),SearchItem.class);
                    startActivity(intent);
                }else {
                    lunch = Integer.parseInt(tv_lunch.getText().toString());
                }
            }
        });

        // 저녁 정보
        tv_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_dinner.getText().toString().equals("kcal")){
                    Intent intent = new Intent(getApplicationContext(),SearchItem.class);
                    startActivity(intent);
                }else {
                    dinner = Integer.parseInt(tv_dinner.getText().toString());
                }
            }
        });

        //선택 날짜 정보
        btn_my_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(getCurrentFocus());
            }
        });

        ProgressBar pg_kcal = (ProgressBar) findViewById(R.id.pg_kcal);
        pg_kcal.setProgress(breakfast+lunch+dinner);

        //회원정보 수정
        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),EditMyInfo.class);
                startActivity(intent);
            }
        });
        
        //영양소 막대차트
        BarChart barChart = findViewById(R.id.bar_chart);
        ArrayList<BarEntry> nutrient = new ArrayList<>();

        nutrient.add(new BarEntry(1,100));
        nutrient.add(new BarEntry(3,200));
        nutrient.add(new BarEntry(5,300));
        nutrient.add(new BarEntry(7,20));
        nutrient.add(new BarEntry(9,60));

        BarDataSet barDataSet = new BarDataSet(nutrient, "영양소");
        barDataSet.setColors(color_array);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);

        barChart.getDescription().setEnabled(false);
        barChart.animateY(2000);
    }

    public void showDatePicker(View view){
        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(getSupportFragmentManager(),"datePicker");
    }
    public void processDatePickerResult(int year, int month, int day){
        tv_my_cal_date.setText(String.format("%d-%d-%d",year,month+1,day));
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