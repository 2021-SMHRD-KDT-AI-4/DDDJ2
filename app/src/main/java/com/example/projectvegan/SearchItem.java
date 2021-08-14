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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchItem extends AppCompatActivity {
    private RecyclerView rv_search_item;          // 검색을 보여줄 리스트변수
    private EditText edt_search_item;        // 검색어를 입력할 Input 창
    private TextView tv_search_btn,tv_check_btn;
    private CheckBox foodCheck;

    private ArrayList<FoodItem> foodItemArrayList, filteredList;
    FoodAdapter foodAdapter;
    LinearLayoutManager linearLayoutManager;

    private float carbohydrate = 0;
    private float protein = 0;
    private float fat = 0;
    private float natrum = 0;
    private float sugar = 0;
    private int kcal = 0;

    private RequestQueue queue;
    private StringRequest stringRequest;

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
        tv_check_btn = findViewById(R.id.tv_rc_check_btn);

        foodCheck = findViewById(R.id.foodCheck);

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
        filteredList.clear();
        foodAdapter.filterList(filteredList);

        foodItemArrayList.addAll(filteredList);

        tv_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edt_search_item.getText().toString();
                search(text);
            }
        });

        tv_check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(foodCheck.isChecked()){
                    sendRequest();
                }
            }
        });
    }

    public void search(String searchText){
        filteredList.clear();
        // 리스트의 모든 데이터를 검색한다.
        for(int i = 0; i < foodItemArrayList.size(); i++)
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
        foodItemArrayList.add(new FoodItem("치킨"));
        foodItemArrayList.add(new FoodItem("햄버거"));


        foodAdapter.notifyDataSetChanged();
    }

    public void sendRequest(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://211.63.240.58:8081/3rd_project/UploadService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        carbohydrate = Float.parseFloat(jsonObject.getString("carbohydrate"));
                        protein = Float.parseFloat(jsonObject.getString("protein"));
                        fat = Float.parseFloat(jsonObject.getString("fat"));
                        natrum = Float.parseFloat(jsonObject.getString("natrum"));
                        sugar = Float.parseFloat(jsonObject.getString("sugar"));
                        kcal = Integer.parseInt(jsonObject.getString("kcal"));

                        Intent intent = new Intent();
                        intent.putExtra("carbohydrate",carbohydrate);
                        intent.putExtra("protein",protein);
                        intent.putExtra("fat",fat);
                        intent.putExtra("natrum",natrum);
                        intent.putExtra("sugar",sugar);
                        intent.putExtra("kcal",kcal);
                        setResult(RESULT_OK,intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"음식 선택 실패",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };
        queue.add(stringRequest);
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