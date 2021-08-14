package com.example.projectvegan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Rank extends AppCompatActivity {
    private RecyclerView rank_list;
    private ArrayList<RankItem> rankArrayList, rankFilteredList;
    RankAdapter rankAdapter;
    LinearLayoutManager linearLayoutManager;

    private TextView rank1_name,rank2_name,rank3_name,rank1_point,rank2_point,rank3_point;

    private ArrayList<RankItem> ranklist = new ArrayList<RankItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        rank1_name = findViewById(R.id.rank1_name);
        rank2_name = findViewById(R.id.rank2_name);
        rank3_name = findViewById(R.id.rank3_name);

        rank1_point = findViewById(R.id.rank1_point);
        rank2_point = findViewById(R.id.rank2_point);
        rank3_point = findViewById(R.id.rank3_point);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("랭킹");
        actionBar.setDisplayHomeAsUpEnabled(true);
        /*androidx.appcompat.widget.Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("랭킹");
        mToolbar.setTitleTextColor(Color.BLACK);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/

        Intent intent = getIntent();
        ranklist = (ArrayList<RankItem>) intent.getSerializableExtra("rankList");

        rank1_name.setText(ranklist.get(0).getName());
        rank2_name.setText(ranklist.get(1).getName());
        rank3_name.setText(ranklist.get(2).getName());

        rank1_point.setText(ranklist.get(0).getPoint());
        rank2_point.setText(ranklist.get(1).getPoint());
        rank3_point.setText(ranklist.get(2).getPoint());

        rank_list = findViewById(R.id.rank_list);

        rankFilteredList = new ArrayList<>();
        rankArrayList = new ArrayList<>();

        rankAdapter = new RankAdapter(rankArrayList,getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rank_list.setLayoutManager(linearLayoutManager);
        rank_list.setAdapter(rankAdapter);

        rank_list.addItemDecoration(new RecyclerViewDecoration(60));

        /*//RecyclerView 구분선
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rank_list.getContext(), new LinearLayoutManager(this).getOrientation());
        rank_list.addItemDecoration(dividerItemDecoration);
        */



        settingList(ranklist);

        rankArrayList.addAll(rankFilteredList);
    }


    private void settingList( ArrayList<RankItem> ranklist){

        for (int i = 3; i < ranklist.size(); i++) {
            rankArrayList.add(new RankItem(i, ranklist.get(i).getName(), ranklist.get(i).getPoint()));
        }

//        rankArrayList.add(new RankItem("1","집","돈가스"));
//        rankArrayList.add(new RankItem("2","가고","먹고"));
//        rankArrayList.add(new RankItem("3","싶다","싶다"));

        rankAdapter.notifyDataSetChanged();
    }

    public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

        private final int divHeight;

        public RecyclerViewDecoration(int divHeight)
        {
            this.divHeight = divHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
        {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.top = divHeight;
        }
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