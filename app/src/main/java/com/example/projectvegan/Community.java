package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.example.projectvegan.DTO.SNSDTO;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Community extends AppCompatActivity {
    private RecyclerView com_list;
    private ArrayList<SNSDTO> communityItemsArrayList, comFilteredList;
    CommunityAdapter communityAdapter;
    LinearLayoutManager linearLayoutManager;
    private Button update_com,btn_chal_check;
    private RequestQueue queue;
    private StringRequest stringRequest;



//    private ArrayList<SNSDTO> snsList = new ArrayList<SNSDTO>();

    String id = "";
    String name = "";
    String title = "";
    String text = "";
    int img;
    int point;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("커뮤니티");
        actionBar.setDisplayHomeAsUpEnabled(true);

        id = PreferenceManager.getString(getApplicationContext(), "id");
        name = PreferenceManager.getString(getApplicationContext(), "name");
        String startEx = PreferenceManager.getString(getApplicationContext(), "startEx");
        String startChal = PreferenceManager.getString(getApplicationContext(), "startChal");
        point =PreferenceManager.getInt(getApplicationContext(),"point");

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        text = intent.getStringExtra("text");
        img = intent.getIntExtra("img",0);


        btn_chal_check = findViewById(R.id.btn_chal_check);

        com_list = findViewById(R.id.com_list);
        update_com = findViewById(R.id.update_com);

        comFilteredList = new ArrayList<>();
        communityItemsArrayList = new ArrayList<>();

        communityAdapter = new CommunityAdapter(communityItemsArrayList,getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        com_list.setLayoutManager(linearLayoutManager);
        com_list.setAdapter(communityAdapter);

        //RecyclerView 구분선
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(com_list.getContext(), new LinearLayoutManager(this).getOrientation());
        com_list.addItemDecoration(dividerItemDecoration);

        settingList();

        Log.d("chal",startChal);
        Log.d("ex",startEx);

        if(startEx.equals("데이터가 없습니다.")){
            btn_chal_check.setVisibility(View.INVISIBLE);
        }else {
            btn_chal_check.setVisibility(View.VISIBLE);

            btn_chal_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"2인 이상 채식 사진 찍기",Toast.LENGTH_LONG).show();
                }
            });
        }
        if(startChal.equals("데이터가 없습니다.")){
            btn_chal_check.setVisibility(View.INVISIBLE);
        }else {
            btn_chal_check.setVisibility(View.VISIBLE);

            btn_chal_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"2인 이상 채식 사진 찍기",Toast.LENGTH_LONG).show();
                }
            });
        }


        communityItemsArrayList.addAll(comFilteredList);

        update_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CommunityUpdate.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void settingList(){

        Intent intent = getIntent();
//        snsList = (ArrayList<SNSDTO>) intent.getSerializableExtra("snsList");

        /*for (int i = 0; i < snsList.size(); i++) {
            communityItemsArrayList.add(
                    new SNSDTO(
                    snsList.get(i).getSns_code(),
                    snsList.get(i).getUser_id(),
                    snsList.get(i).getSns_title(),
                    snsList.get(i).getSns_content(),
                    snsList.get(i).getSns_img(),
                    snsList.get(i).getUser_name())
            );
        }*/
        communityItemsArrayList.add(new SNSDTO("dodo@gmail.com","도도동","채식 1일차","채식 도전 1일차 생각보다 할만했다", R.drawable.vgimg1));
        communityItemsArrayList.add(new SNSDTO("aa@gmail.com","aa","채식 도전!!","하루 한 끼 채식으로 환경을 지키자~", R.drawable.vgimg2));
        if(title != null) {
            communityItemsArrayList.add(new SNSDTO(id, name, title, text,img));
            Toast.makeText(getApplicationContext(),"미션 성공",Toast.LENGTH_LONG).show();
        }
        communityAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                Intent intent = new Intent(this, Main.class);
                startActivity(intent);
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}