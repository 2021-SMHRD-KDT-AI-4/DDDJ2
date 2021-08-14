package com.example.projectvegan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MyPage extends AppCompatActivity {
    private int[] color_array = {Color.rgb(76,175,80),Color.rgb(139,195,74),
                                 Color.rgb(170,182,48),Color.rgb(211,189,4),
                                 Color.rgb(188,141,3)};

    private TextView tv_breakfast,tv_lunch,tv_dinner,tv_my_cal_date,tv_my_id,
            tv_kcal,tv_my_kcal,tv_protein,tv_natrum,tv_fat,tv_sugar,tv_carb,
            tv_my_level_title,tv_my_name;

    private ProgressBar pg_kcal;

    private ImageView edit_info;
    private Button btn_my_calendar;


    //영양 정보 초기값
    private float carbohydrate,protein,fat,natrum,sugar = 0;
    int kcal,progress,max_kcal = 0;


    // 회원 정보
    private String id,name,gender,category = null;
    int age = 0;

    private int breakfast,lunch,dinner = 0;

    Calendar cal = Calendar.getInstance();

    private String today =cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DATE);
    private RequestQueue queue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        // 액션바 설정
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("마이페이지");

        actionBar.setDisplayHomeAsUpEnabled(true);

        tv_my_level_title = findViewById(R.id.tv_my_level_title);
        tv_my_id = findViewById(R.id.tv_my_id);
        tv_my_name = findViewById(R.id.tv_my_name);

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
        tv_kcal = findViewById(R.id.tv_kcal);

        pg_kcal = findViewById(R.id.pg_kcal);

        tv_my_cal_date = findViewById(R.id.tv_my_cal_date);
        tv_my_cal_date.setText(today);

        edit_info = findViewById(R.id.edit_info);

        // 받아온 값
        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");
        age = intent.getIntExtra("user_age",0);
        name = intent.getStringExtra("user_name");
        gender = intent.getStringExtra("user_gender");
        category = intent.getStringExtra("user_category");


        tv_my_level_title.setText(category);
        tv_my_name.setText(name);
        tv_my_id.setText(id);
        pg_kcal.setProgress(progress);
        tv_my_kcal.setText(progress+"");


        // 영양소 정보
        if (age>=10 && age<30 && gender.equals("남")){
            tv_kcal.setText("칼로리 : 2700kcal");
            max_kcal = 2700;
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 55g");
            tv_fat.setText("지방 : 50g");
            tv_natrum.setText("나트륨 : 1500mg");
            tv_sugar.setText("당류 : 50g");
        }else if(age>=10 && age<30 && gender.equals("여")){
            tv_kcal.setText("칼로리 : 2000kcal");
            max_kcal = 2000;
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 50g");
            tv_fat.setText("지방 : 50g");
            tv_natrum.setText("나트륨 : 1500mg");
            tv_sugar.setText("당류 : 50g");
        }else if(age>=30 && age<50 && gender.equals("남")){
            tv_kcal.setText("칼로리 : 2400kcal");
            max_kcal = 2400;
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 55g");
            tv_fat.setText("지방 : 50g");
            tv_natrum.setText("나트륨 : 1500mg");
            tv_sugar.setText("당류 : 50g");
        }else if(age>=30 && age<50 && gender.equals("여")){
            tv_kcal.setText("칼로리 : 1900kcal");
            max_kcal = 1900;
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 45g");
            tv_fat.setText("지방 : 45g");
            tv_natrum.setText("나트륨 : 1500mg");
            tv_sugar.setText("당류 : 45g");
        }else if(age>=50 && age<65 && gender.equals("남")){
            tv_kcal.setText("칼로리 : 2200kcal");
            max_kcal = 2200;
            tv_carb.setText("탄수화물 : 328g");
            tv_protein.setText("단백질 : 50g");
            tv_fat.setText("지방 : 45g");
            tv_natrum.setText("나트륨 : 1400mg");
            tv_sugar.setText("당류 : 45g");
        }else if(age>=50 && age<65 && gender.equals("여")){
            tv_kcal.setText("칼로리 : 1800kcal");
            max_kcal = 1800;
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
                if (tv_my_cal_date.getText().toString().equals(today)){
                    Intent intent = new Intent(getApplicationContext(),SearchItem.class);
                    startActivityForResult(intent,1001);

                    //저장 값
                    breakfast = kcal;
                    pg_kcal.setProgress(progress);
                    tv_my_kcal.setText(progress+"");
                }
            }
        });

        // 점심 정보
        tv_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_my_cal_date.getText().toString().equals(today)){
                    Intent intent = new Intent(getApplicationContext(),SearchItem.class);
                    startActivityForResult(intent,1002);

                    //저장 값
                    lunch = kcal;
                    pg_kcal.setProgress(progress);
                    tv_my_kcal.setText(progress+"");

                }
            }
        });

        // 저녁 정보
        tv_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_my_cal_date.getText().toString().equals(today)){
                    Intent intent = new Intent(getApplicationContext(),SearchItem.class);
                    startActivityForResult(intent,1003);

                    //저장 값
                    dinner = kcal;
                    pg_kcal.setProgress(progress);
                    tv_my_kcal.setText(progress+"");

                }
            }
        });

        //선택 날짜 정보
        btn_my_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(getCurrentFocus());
                // 불러온 값
                sendRequestSelect();

                pg_kcal.setProgress(progress);
                tv_my_kcal.setText(progress+"");

            }
        });

        // 나의 권장 칼로리 정보
        pg_kcal.setMax(max_kcal);

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

        sendRequestUpdate();

    }

    public void showDatePicker(View view){
        DialogFragment newFragment = new DatePickerFragment();

        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){
        tv_my_cal_date.setText(String.format("%d-%d-%d",year,month+1,day));
    }

    // 선택한 날짜 데이터 받아오기
    public void sendRequestSelect(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://211.63.240.58:8081/3rd_project/UploadService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        // java에서 넘겨받은 값
                        JSONObject jsonObject = new JSONObject(response);
                        breakfast = Integer.parseInt(jsonObject.getString("breakfast"));
                        lunch = Integer.parseInt(jsonObject.getString("lunch"));
                        dinner = Integer.parseInt(jsonObject.getString("dinner"));
                        carbohydrate = Float.parseFloat(jsonObject.getString("carbohydrate"));
                        protein = Float.parseFloat(jsonObject.getString("protein"));
                        fat = Float.parseFloat(jsonObject.getString("fat"));
                        natrum = Float.parseFloat(jsonObject.getString("natrum"));
                        sugar = Float.parseFloat(jsonObject.getString("sugar"));
                        kcal = Integer.parseInt(jsonObject.getString("kcal"));

                        Intent intent = new Intent(getApplicationContext(),MyPage.class);

                        intent.putExtra("breakfast",breakfast);
                        intent.putExtra("lunch",lunch);
                        intent.putExtra("dinner",dinner);
                        intent.putExtra("carbohydrate",carbohydrate);
                        intent.putExtra("protein",protein);
                        intent.putExtra("fat",fat);
                        intent.putExtra("natrum",natrum);
                        intent.putExtra("sugar",sugar);
                        intent.putExtra("kcal",kcal);

                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"갱신 실패",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("user_id",id);
                params.put("pick_date",tv_my_cal_date.getText().toString());

                return params;
            }
        };
        queue.add(stringRequest);
    }

    // 오늘 데이터 넘겨주기
    public void sendRequestUpdate(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://211.63.240.58:8081/3rd_project/UploadService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"등록 실패",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //오늘의 데이터
                params.put("user_id",id);
                params.put("today_date",today);
                params.put("breakfast",String.valueOf(breakfast));
                params.put("lunch",String.valueOf(lunch));
                params.put("dinner",String.valueOf(dinner));
                params.put("carbohydrate",String.valueOf(carbohydrate));
                params.put("protein",String.valueOf(protein));
                params.put("fat",String.valueOf(fat));
                params.put("natrum",String.valueOf(natrum));
                params.put("sugar",String.valueOf(sugar));
                params.put("kcal",String.valueOf(kcal));

                return params;
            }
        };
        queue.add(stringRequest);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001 && resultCode == RESULT_OK) {
            // 영양소 계산
            carbohydrate += Float.parseFloat(data.getStringExtra("carbohydrate"));
            protein += Float.parseFloat(data.getStringExtra("protein"));
            fat += Float.parseFloat(data.getStringExtra("fat"));
            natrum += Float.parseFloat(data.getStringExtra("natrum"));
            sugar += Float.parseFloat(data.getStringExtra("sugar"));
            kcal += Integer.parseInt(data.getStringExtra("kcal"));
            progress += kcal;
            tv_breakfast.setText("섭취완료");
        }else if(requestCode==1002 && resultCode == RESULT_OK){
            carbohydrate += Float.parseFloat(data.getStringExtra("carbohydrate"));
            protein += Float.parseFloat(data.getStringExtra("protein"));
            fat += Float.parseFloat(data.getStringExtra("fat"));
            natrum += Float.parseFloat(data.getStringExtra("natrum"));
            sugar += Float.parseFloat(data.getStringExtra("sugar"));
            kcal += Integer.parseInt(data.getStringExtra("kcal"));
            progress += kcal;
            tv_lunch.setText("섭취완료");
        }else if(requestCode==1003 && resultCode == RESULT_OK){
            carbohydrate += Float.parseFloat(data.getStringExtra("carbohydrate"));
            protein += Float.parseFloat(data.getStringExtra("protein"));
            fat += Float.parseFloat(data.getStringExtra("fat"));
            natrum += Float.parseFloat(data.getStringExtra("natrum"));
            sugar += Float.parseFloat(data.getStringExtra("sugar"));
            kcal += Integer.parseInt(data.getStringExtra("kcal"));
            progress += kcal;
            tv_dinner.setText("섭취완료");
        }
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