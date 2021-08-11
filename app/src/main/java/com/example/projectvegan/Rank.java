package com.example.projectvegan;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class Rank extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    Context context;

    private TabLayout tabLayout;
    private ViewPager2 diary_pager;
    private TabPageAdapter tabPageAdapter;

    private  String[] titles = {"전체", "내 타입"};

    String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("랭킹");
        actionBar.setDisplayHomeAsUpEnabled(true);

        context = Rank.this;

        Fragment frag1 = new FragmentOne();
        Fragment frag2 = new FragmentTwo();



        tabLayout = (TabLayout) findViewById(R.id.diary_tab);

        diary_pager = findViewById(R.id.diary_pager);

        tabPageAdapter = new TabPageAdapter(this);
        tabPageAdapter.addFrag(frag1);
        tabPageAdapter.addFrag(frag2);


        diary_pager.setAdapter(tabPageAdapter);

        new TabLayoutMediator(tabLayout,diary_pager,(tab, position) -> tab.setText(titles[position])).attach();

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