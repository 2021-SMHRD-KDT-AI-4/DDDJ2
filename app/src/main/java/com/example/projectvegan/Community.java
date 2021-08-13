package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Community extends AppCompatActivity {
    private RecyclerView com_list;
    private ArrayList<CommunityItem> communityItemsArrayList, comFilteredList;
    CommunityAdapter communityAdapter;
    LinearLayoutManager linearLayoutManager;
    private Button update_com;

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
            }
        });
    }

    private void settingList(){
        communityItemsArrayList.add(new CommunityItem(R.drawable.basic,"나","qwer1@naver.com","집에가자!",R.drawable.quizimg1,"이제 집에 가이지"));
        communityItemsArrayList.add(new CommunityItem(R.drawable.basic,"너","qwer2@naver.com","가자!",R.drawable.quizimg2,"프로젝트 그만!!!"));
        communityItemsArrayList.add(new CommunityItem(R.drawable.basic,"우리","qwer3@naver.com","가자!!!!!",R.drawable.quizimg3,"가즈아!!!!"));

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