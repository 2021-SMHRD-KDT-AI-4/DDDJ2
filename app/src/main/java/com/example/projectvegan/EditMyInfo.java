package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.HashMap;
import java.util.Map;

public class EditMyInfo extends AppCompatActivity {
    private RequestQueue queue;
    private StringRequest stringRequest;

    private Button btn_update_back,btn_update;
    private EditText edt_edit_pw_check,edt_edit_pw,edt_edit_tel,
                    edt_edit_name;
    private Spinner edit_cb_level;

    String id,pw,name,tel,category = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("회원정보 수정");

        actionBar.setDisplayHomeAsUpEnabled(true);

        edt_edit_pw = findViewById(R.id.edt_edit_pw);
        edt_edit_pw_check = findViewById(R.id.edt_edit_pw_check);
        edt_edit_name = findViewById(R.id.edt_edit_name);
        edt_edit_tel = findViewById(R.id.edt_edit_tel);

        edit_cb_level = findViewById(R.id.edit_cb_level);

        btn_update = findViewById(R.id.btn_update);
        btn_update_back = findViewById(R.id.btn_update_back);

        Spinner vegetarian = (Spinner)findViewById(R.id.edit_cb_level);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.level, android.R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vegetarian.setAdapter(yearAdapter);

        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");
        pw =intent.getStringExtra("user_pw");
        name =intent.getStringExtra("user_name");
        tel = intent.getStringExtra("user_tel");
        category = intent.getStringExtra("user_category");

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
    }

    public void sendRequest(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://211.63.240.58:8081/3rd_project/UpdateService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        Intent intent = new Intent(getApplicationContext(),MyPage.class);
                        PreferenceManager.setString(getApplicationContext(),"id",jsonObject.getString("user_id"));
                        PreferenceManager.setString(getApplicationContext(),"pw",jsonObject.getString("user_pw"));
                        PreferenceManager.setString(getApplicationContext(),"name",jsonObject.getString("user_name"));
                        PreferenceManager.setString(getApplicationContext(),"tel",jsonObject.getString("user_tel"));
                        PreferenceManager.setString(getApplicationContext(),"category",jsonObject.getString("user_category"));
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"로그인 실패..",Toast.LENGTH_SHORT).show();
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

                params.put("id",id);
                params.put("pw",edt_edit_pw.getText().toString());
                params.put("name",edt_edit_name.getText().toString());
                params.put("tel",edt_edit_name.getText().toString());
                params.put("category",edit_cb_level.getSelectedItem().toString());

                return params;
            }
        };
        queue.add(stringRequest);
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