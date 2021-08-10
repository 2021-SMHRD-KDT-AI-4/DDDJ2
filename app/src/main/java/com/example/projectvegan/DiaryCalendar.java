package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;

public class DiaryCalendar extends AppCompatActivity {
    private GridView gv_calendar;
    private GridAdapter gridAdapter;

    private ArrayList<String> day_list;

    private Calendar mCal;
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