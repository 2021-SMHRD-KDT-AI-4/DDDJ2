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
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Recipe extends AppCompatActivity {
    private RecyclerView rv_rc_search_item;          // 검색을 보여줄 리스트변수
    private EditText edt_rc_search_item;        // 검색어를 입력할 Input 창
    private TextView tv_rc_search_btn;
    private ArrayList<RecipeItem> recipeFoodItemArrayList, rFilteredList;
    RecipeAdapter recipeAdapter;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("레시피");

        actionBar.setDisplayHomeAsUpEnabled(true);
        edt_rc_search_item = findViewById(R.id.edt_rc_search_item);
        tv_rc_search_btn = findViewById(R.id.tv_rc_search_btn);
        rv_rc_search_item = findViewById(R.id.rv_rc_search_item);

        rFilteredList = new ArrayList<>();
        recipeFoodItemArrayList = new ArrayList<>();

        recipeAdapter = new RecipeAdapter(recipeFoodItemArrayList,getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv_rc_search_item.setLayoutManager(linearLayoutManager);
        rv_rc_search_item.setAdapter(recipeAdapter);

        //RecyclerView 구분선
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv_rc_search_item.getContext(), new LinearLayoutManager(this).getOrientation());
        rv_rc_search_item.addItemDecoration(dividerItemDecoration);

        settingList();

        recipeFoodItemArrayList.addAll(rFilteredList);


        tv_rc_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt_rc_search_item.getText().toString();
                search(text);
            }
        });
    }

    public void search(String searchText){
        rFilteredList.clear();
        // 리스트의 모든 데이터를 검색한다.
        for(int i = 0; i < recipeFoodItemArrayList.size(); i++)
        {
            // arraylist의 모든 데이터에 입력받은 단어(searchText)가 포함되어 있으면 true를 반환한다.
            if (recipeFoodItemArrayList.get(i).getRecipeFoodName().toLowerCase().contains(searchText.toLowerCase()))
            {
                // 검색된 데이터를 리스트에 추가한다.
                rFilteredList.add(recipeFoodItemArrayList.get(i));
            }
        }
        recipeAdapter.rFilterList(rFilteredList);
    }

    private void settingList(){
        recipeFoodItemArrayList.add(new RecipeItem("피자","맛있다","치즈"));
        recipeFoodItemArrayList.add(new RecipeItem("햄버거","맛있다","빵"));
        recipeFoodItemArrayList.add(new RecipeItem("피자치킨햄버거","맛있다","뭐냐"));

        recipeAdapter.notifyDataSetChanged();
    }
    //toolbar의 back키 눌렀을 때 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}