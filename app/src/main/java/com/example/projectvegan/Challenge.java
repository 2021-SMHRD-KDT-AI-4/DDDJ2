package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Challenge extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    Context context;

    private TabLayout chal_tap;
    private ViewPager2 chal_pager;
    private TabPageAdapter tabPageAdapter;

    private  String[] titles = {"전체", "내 타입"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("챌린지");

        actionBar.setDisplayHomeAsUpEnabled(true);
        context = Challenge.this;

        Fragment frag1 = new ChalFragOne();
        Fragment frag2 = new ChalFragTwo();


        chal_tap = (TabLayout) findViewById(R.id.chal_tab);

        chal_pager = findViewById(R.id.chal_pager);

        tabPageAdapter = new TabPageAdapter(this);
        tabPageAdapter.addFrag(frag1);
        tabPageAdapter.addFrag(frag2);


        chal_pager.setAdapter(tabPageAdapter);

        new TabLayoutMediator(chal_tap,chal_pager,(tab, position) -> tab.setText(titles[position])).attach();


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