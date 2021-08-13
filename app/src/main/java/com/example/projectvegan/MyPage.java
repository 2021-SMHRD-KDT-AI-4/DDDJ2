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

    private TextView tv_breakfast,tv_lunch,tv_dinner,tv_my_cal_date,
            tv_kcal,tv_my_kcal,tv_protein,tv_natrum,tv_fat,tv_sugar,tv_carb,
            tv_my_level_title;
    private ProgressBar pg_kcal;

    private ImageView edit_info;
    private Button btn_my_calendar;

    private float carbohydrate = 0;
    private float protein = 0;
    private float fat = 0;
    private float natrum = 0;
    private float sugar = 0;

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

        tv_my_level_title = findViewById(R.id.tv_my_level_title);


        tv_breakfast = findViewById(R.id.tv_breakfast);
        tv_lunch = findViewById(R.id.tv_lunch);
        tv_dinner = findViewById(R.id.tv_dinner);
        btn_my_calendar = findViewById(R.id.btn_my_calendar);

        tv_my_kcal = findViewById(R.id.tv_my_kcal);
        tv_carb = findViewById(R.id.tv_carb);
        tv_protein = findViewById(R.id.tv_protein);
        tv_fat = findViewById(R.id.tv_fat);
        tv_natrum = findViewById(R.id.tv_natrum);
        tv_sugar = findViewById(R.id.tv_sugar);

        pg_kcal = findViewById(R.id.pg_kcal);

        tv_my_cal_date = findViewById(R.id.tv_my_cal_date);
        tv_my_cal_date.setText(cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE));

        edit_info = findViewById(R.id.edit_info);

        Intent intent = getIntent();
        int age = Integer.parseInt(intent.getStringExtra("age"));
        String gender = intent.getStringExtra("gender");

        if (age>=10 && age<30 && gender.equals("남")){
            tv_kcal.setText("칼로리 : 2700kcal");
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 55g");
            tv_fat.setText("지방 : 50g");
            tv_natrum.setText("나트륨 : 1500mg");
            tv_sugar.setText("당류 : 50g");
        }else if(age>=10 && age<30 && gender.equals("여")){
            tv_kcal.setText("칼로리 : 2000kcal");
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 50g");
            tv_fat.setText("지방 : 50g");
            tv_natrum.setText("나트륨 : 1500mg");
            tv_sugar.setText("당류 : 50g");
        }else if(age>=30 && age<50 && gender.equals("남")){
            tv_kcal.setText("칼로리 : 2400kcal");
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 55g");
            tv_fat.setText("지방 : 50g");
            tv_natrum.setText("나트륨 : 1500mg");
            tv_sugar.setText("당류 : 50g");
        }else if(age>=30 && age<50 && gender.equals("여")){
            tv_kcal.setText("칼로리 : 1900kcal");
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 45g");
            tv_fat.setText("지방 : 45g");
            tv_natrum.setText("나트륨 : 1500mg");
            tv_sugar.setText("당류 : 45g");
        }else if(age>=50 && age<65 && gender.equals("남")){
            tv_kcal.setText("칼로리 : 2200kcal");
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 50g");
            tv_fat.setText("지방 : 45g");
            tv_natrum.setText("나트륨 : 1400mg");
            tv_sugar.setText("당류 : 45g");
        }else if(age>=50 && age<65 && gender.equals("여")){
            tv_kcal.setText("칼로리 : 1800kcal");
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 45g");
            tv_fat.setText("지방 : 40g");
            tv_natrum.setText("나트륨 : 1400mg");
            tv_sugar.setText("당류 : 40g");
        }

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

        int progress = breakfast+lunch+dinner;

        pg_kcal.setProgress(progress);
        tv_my_kcal.setText(progress+"");

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

        nutrient.add(new BarEntry(1,carbohydrate));
        nutrient.add(new BarEntry(3,protein));
        nutrient.add(new BarEntry(5,fat));
        nutrient.add(new BarEntry(7,natrum));
        nutrient.add(new BarEntry(9,sugar));

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