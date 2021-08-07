package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MyPage extends AppCompatActivity {
    private String[] title = {"탄수화물","단백질","지방","비타민","나트륨"};
    private int[] color_array = {Color.rgb(76,175,80),Color.rgb(139,195,74),
                                 Color.rgb(205,220,57),Color.rgb(255,235,59),
                                 Color.rgb(255,193,7)};

    private ImageView edit_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("마이페이지");

        actionBar.setDisplayHomeAsUpEnabled(true);

        edit_info = findViewById(R.id.edit_info);

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


        ArrayList<String> labels = new ArrayList<String>();

        labels.add("탄수화물");
        labels.add("단백질");
        labels.add("지방");
        labels.add("비타민");
        labels.add("나트륨");
        labels.add("June");

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

        /*//칼로리 파이차트
        PieChart pieChart = findViewById(R.id.pie_chart);
        ArrayList<PieEntry> kcal = new ArrayList<>();
        kcal.add(new PieEntry(1,"탄"));
        kcal.add(new PieEntry(1,"단"));
        kcal.add(new PieEntry(1,"지"));
        kcal.add(new PieEntry(1,"비"));
        kcal.add(new PieEntry(1,"나"));

        PieDataSet pieDataSet = new PieDataSet(kcal,"칼로리");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("칼로리");
        pieChart.animate();*/
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