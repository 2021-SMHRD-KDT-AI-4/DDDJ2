package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchItem extends AppCompatActivity {
    private RecyclerView rv_search_item;          // 검색을 보여줄 리스트변수
    private EditText edt_search_item;        // 검색어를 입력할 Input 창
    private TextView tv_search_btn;
    private ArrayList<FoodItem> foodItemArrayList, filteredList;
    FoodAdapter foodAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("메뉴 검색");

        actionBar.setDisplayHomeAsUpEnabled(true);

        edt_search_item = findViewById(R.id.edt_rc_search_item);
        tv_search_btn = findViewById(R.id.tv_rc_search_btn);
        rv_search_item = findViewById(R.id.rv_rc_search_item);

        filteredList = new ArrayList<>();
        foodItemArrayList = new ArrayList<>();

        foodAdapter = new FoodAdapter(foodItemArrayList,this);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_search_item.setLayoutManager(linearLayoutManager);
        rv_search_item.setAdapter(foodAdapter);

        //RecyclerView 구분선
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv_search_item.getContext(), new LinearLayoutManager(this).getOrientation());
        rv_search_item.addItemDecoration(dividerItemDecoration);

        settingList();

        foodItemArrayList.addAll(filteredList);


        tv_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt_search_item.getText().toString();
                search(text);
            }
        });
    }

    public void search(String searchText){
        filteredList.clear();
        // 리스트의 모든 데이터를 검색한다.
        for(int i = 0;i < foodItemArrayList.size(); i++)
        {
            // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
            if (foodItemArrayList.get(i).getFoodName().toLowerCase().contains(searchText.toLowerCase()))
            {
                // 검색된 데이터를 리스트에 추가한다.
                filteredList.add(foodItemArrayList.get(i));
            }
        }
       foodAdapter.filterList(filteredList);
    }

    private void settingList(){
        foodItemArrayList.add(new FoodItem("피자"));
        foodItemArrayList.add(new FoodItem("햄버거"));
        foodAdapter.notifyDataSetChanged();

    }

    //toolbar의 back키 눌렀을 때 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}