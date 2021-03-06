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

        id = PreferenceManager.getString(getApplicationContext(),"id");

        day_list = new ArrayList<>();
        day_list.add("???");
        day_list.add("???");
        day_list.add("???");
        day_list.add("???");
        day_list.add("???");
        day_list.add("???");
        day_list.add("???");

        mCal = Calendar.getInstance();

        // ????????? 1??? ?????? ???????????? ??????
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date))-1,1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        // 1??? ?????? ??????
        for(int i = 1; i< dayNum; i++){
            day_list.add("");
        }
        setCalendarDate(mCal.get(Calendar.MONTH)+1);

        gridAdapter = new GridAdapter(getApplicationContext(),day_list);
        gv_calendar.setAdapter(gridAdapter);

    }

    // ?????? ??? ??? ??? ??????
    private void setCalendarDate(int month){
        mCal.set(Calendar.MONTH, month-1);
        for (int i = 0; i< mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++){
            day_list.add("" + (i+1));
        }
    }

    //?????????
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

            //?????? ?????? ????????? ??????,?????? ??????
            mCal = Calendar.getInstance();
            //?????? day ?????????
            int today = mCal.get(Calendar.DAY_OF_MONTH);
            int month = mCal.get(Calendar.MONTH)+1;
            int year = mCal.get(Calendar.YEAR);
            String sToday = String.valueOf(today);
            String todayDB = year+"???"+month+"???"+today+"???";
            if (sToday.equals(getItem(position))) { //?????? day ????????? ?????? ??????
                holder.tvItemGridView.setTextColor(Color.BLACK);
                convertView.setBackgroundColor(Color.rgb(159,229,84));
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PreferenceManager.setString(getApplicationContext(), "date", todayDB);
                        sendRequestUpdate(todayDB);
                    }
                });
            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvItemGridView;
    }
    // ?????? ????????? ????????????
    public void sendRequestUpdate(String d){
        // Voolley Lib ????????? ???????????? ??????
        queue = Volley.newRequestQueue(this);
        String url = "http://211.63.240.58:8081/3rd_project/MyService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // ?????????????????? ???????????? ???
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        Intent intent = new Intent(getApplicationContext(),MyPage.class);
                        Log.d("kcal",jsonObject.getString("total_calory"));
                        if(jsonObject.getString("total_calory").equals("0")) {
                            startActivity(intent);
                        }else {
                            float carbohydrate = Float.parseFloat(jsonObject.getString("total_carbohydrate"));
                            float protein = Float.parseFloat(jsonObject.getString("total_protein"));
                            float fat = Float.parseFloat(jsonObject.getString("total_fat"));
                            float natrum = Float.parseFloat(jsonObject.getString("total_natrum"));
                            float sugar = Float.parseFloat(jsonObject.getString("total_sugar"));
                            int kcal = Integer.parseInt(jsonObject.getString("total_calory"));



                            intent.putExtra("carbohydrate", carbohydrate);
                            intent.putExtra("protein", protein);
                            intent.putExtra("fat", fat);
                            intent.putExtra("natrum", natrum);
                            intent.putExtra("sugar", sugar);
                            intent.putExtra("kcal", kcal);

                            startActivity(intent);
                            finish();
                        }
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"?????? ??????",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            // ???????????? ?????? ????????? ??????
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override //response??? UTF8??? ??????????????? ????????????
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

            // ?????? ???????????? ???????????? ???
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //????????? ?????????
                params.put("user_id",id);
                params.put("today_date",d);

                return params;
            }
        };
        queue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar??? back??? ????????? ??? ??????
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}