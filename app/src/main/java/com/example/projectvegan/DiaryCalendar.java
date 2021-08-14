package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;
import java.util.Map;

public class DiaryCalendar extends AppCompatActivity {
    private GridView gv_calendar;
    private GridAdapter gridAdapter;

    private ArrayList<String> day_list;

    private Calendar mCal;

    private RequestQueue queue;
    private StringRequest stringRequest;

    private String id,pw,name,category,gender,tel = "";
    private int age,point = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_calendar);



        gv_calendar = findViewById(R.id.gv_calendar);

        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(curYearFormat.format(date)+"/"+curMonthFormat.format(date));

        actionBar.setDisplayHomeAsUpEnabled(true);

        day_list = new ArrayList<>();
        day_list.add("일");
        day_list.add("월");
        day_list.add("화");
        day_list.add("수");
        day_list.add("목");
        day_list.add("금");
        day_list.add("토");

        mCal = Calendar.getInstance();

        // 이번달 1일 무슨 요일인지 판단
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date))-1,1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        // 1일 요일 매칭
        for(int i = 1; i< dayNum; i++){
            day_list.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH)+1);

        gridAdapter = new GridAdapter(getApplicationContext(),day_list);
        gv_calendar.setAdapter(gridAdapter);

        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");
        pw = intent.getStringExtra("user_pw");
        name = intent.getStringExtra("user_name");
        category = intent.getStringExtra("user_category");
        age = intent.getIntExtra("user_age",0);
        gender = intent.getStringExtra("user_gender");
        point = intent.getIntExtra("user_point",0);
        tel = intent.getStringExtra("user_tel");
    }

    // 해당 월 일 수 계산
    private void setCalendarDate(int month){
        mCal.set(Calendar.MONTH, month-1);
        for (int i = 0; i< mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            day_list.add("" + (i+1));
        }
    }

    //어댑터
    private class GridAdapter extends BaseAdapter {

        private final List<String> list;

        private final LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.tv_item_gridview);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));

            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
                holder.tvItemGridView.setTextColor(Color.BLACK);
                convertView.setBackgroundColor(Color.rgb(159,229,84));
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),MyPage.class);
                        intent.putExtra("user_id",id);
                        intent.putExtra("user_pw",pw);
                        intent.putExtra("user_name",name);
                        intent.putExtra("user_category",category);
                        intent.putExtra("user_age",age);
                        intent.putExtra("user_gender",gender);
                        intent.putExtra("user_point",point);
                        intent.putExtra("user_tel",tel);

//                        intent.putExtra("date",today);
                        startActivity(intent);
                    }
                });
            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvItemGridView;
    }

    /*public void sendRequest(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://211.63.240.58:8081/3rd_project/Diary";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        PreferenceManager.setString(getApplicationContext(), "id", jsonObject.getString("user_id"));
                        PreferenceManager.setString(getApplicationContext(), "pw", jsonObject.getString("user_pw"));
                        PreferenceManager.setString(getApplicationContext(), "name", jsonObject.getString("user_name"));
                        PreferenceManager.setString(getApplicationContext(), "category", jsonObject.getString("user_category"));
                        PreferenceManager.setInt(getApplicationContext(), "age", jsonObject.getInt("user_age"));
                        PreferenceManager.setString(getApplicationContext(), "gender", jsonObject.getString("user_gender"));
                        PreferenceManager.setString(getApplicationContext(), "tel", jsonObject.getString("user_tel"));
                        Intent intent = new Intent(getApplicationContext(),MyPage.class);


                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"로그인 실패..",Toast.LENGTH_SHORT).show();
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

                return params;
            }
        };
        queue.add(stringRequest);
    }*/

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