package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    private Button update_com;
    private RequestQueue queue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("커뮤니티");
        actionBar.setDisplayHomeAsUpEnabled(true);

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
        ArrayList<SNSDTO> snsList = (ArrayList<SNSDTO>) intent.getSerializableExtra("snsList");

        for (int i = 0; i < snsList.size(); i++) {
            communityItemsArrayList.add(new SNSDTO(snsList.get(i).getSns_code(),
                    snsList.get(i).getUser_id(),
                    snsList.get(i).getSns_title(),
                    snsList.get(i).getSns_content(),
                    snsList.get(i).getSns_img(),
                    snsList.get(i).getUser_name()
                    ));
        }
//        communityItemsArrayList.add(new CommunityItem(R.drawable.basic,"나","qwer1@naver.com","집에가자!",R.drawable.quizimg1,"이제 집에 가이지"));
//        communityItemsArrayList.add(new CommunityItem(R.drawable.basic,"너","qwer2@naver.com","가자!",R.drawable.quizimg2,"프로젝트 그만!!!"));
//        communityItemsArrayList.add(new CommunityItem(R.drawable.basic,"우리","qwer3@naver.com","가자!!!!!",R.drawable.quizimg3,"가즈아!!!!"));

        communityAdapter.notifyDataSetChanged();
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