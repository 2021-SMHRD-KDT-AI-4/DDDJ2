package com.example.projectvegan;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rank extends AppCompatActivity {
    private RecyclerView rank_list;
    private ArrayList<RankItem> rankArrayList, rankFilteredList;
    RankAdapter rankAdapter;
    LinearLayoutManager linearLayoutManager;

    private TextView rank1_name,rank2_name,rank3_name,rank1_point,rank2_point,rank3_point;

    private ArrayList<RankItem> ranklist = new ArrayList<RankItem>();


    private ArrayList<RankItem> rankList = null;

    ArrayList<String> name;
    ArrayList<Integer> point;
    int num = 0;
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

        name = new ArrayList<String>();
        point = new ArrayList<Integer>();



        while (true) {
            name.add(intent.getStringExtra("rankName" + num));
            point.add(intent.getIntExtra("rankPoint" + num, 0));
            num++;
            if(intent.getStringExtra("rankName" + num)==null){
                break;
            }
        }

//        ranklist = (ArrayList<RankItem>) intent.getStringExtra("rankList");


        rank1_name.setText(name.get(0));
        rank2_name.setText(name.get(1));
        rank3_name.setText(name.get(2));

        rank1_point.setText(point.get(0)+"");
        rank2_point.setText(point.get(1)+"");
        rank3_point.setText(point.get(2)+"");

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

        for (int i = 3; i < name.size(); i++) {
            rankArrayList.add(new RankItem(i, name.get(i), point.get(i)));
        }

//        rankArrayList.add(new RankItem("1","집",10));
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