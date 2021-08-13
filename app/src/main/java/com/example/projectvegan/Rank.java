package com.example.projectvegan;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("랭킹");
        actionBar.setDisplayHomeAsUpEnabled(true);
        /*androidx.appcompat.widget.Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("랭킹");
        mToolbar.setTitleTextColor(Color.BLACK);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/

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
        settingList();

        rankArrayList.addAll(rankFilteredList);
    }


    private void settingList(){
        rankArrayList.add(new RankItem("1","집","돈가스"));
        rankArrayList.add(new RankItem("2","가고","먹고"));
        rankArrayList.add(new RankItem("3","싶다","싶다"));

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