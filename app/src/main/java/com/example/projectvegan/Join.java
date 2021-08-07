package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.HashMap;
import java.util.Map;

public class Join extends AppCompatActivity {
    private Button btn_join,btn_back;
    private EditText edt_join_id,edt_join_pw,edt_join_pw_check,
            edt_join_name,edt_join_tel,edt_join_age;
    private RadioButton rb_join_male,rb_join_female;
    private RadioGroup rg_join;
    private RadioButton rb_gender;

    private RequestQueue queue;
    private StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("회원가입");

        actionBar.setDisplayHomeAsUpEnabled(true);

        edt_join_id = findViewById(R.id.edt_join_id);
        edt_join_pw = findViewById(R.id.edt_join_pw);
        edt_join_pw_check = findViewById(R.id.edt_join_pw_check);
        edt_join_name = findViewById(R.id.edt_join_name);
        edt_join_tel = findViewById(R.id.edt_join_tel);
        edt_join_age = findViewById(R.id.edt_join_age);

        rg_join = findViewById(R.id.rg_join);
        rb_join_male = findViewById(R.id.rb_join_male);
        rb_join_female = findViewById(R.id.rb_join_female);

        btn_join = findViewById(R.id.btn_join);
        btn_back = findViewById(R.id.btn_back);

        rg_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = rg_join.getCheckedRadioButtonId();
                rb_gender = (RadioButton)findViewById(id);
            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
    public void sendRequest(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://59.0.236.151:8081/AndroidServer/JoinService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
//                        Log.v("resultValue",jsonObject.getString("isCheck"));
                    String result = jsonObject.getString("isCheck");
                    if(result.equals("true")){
                        if ( !edt_join_pw.getText().toString().equals(edt_join_pw_check.getText().toString()) ) {
                            Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                            edt_join_pw.setText("");
                            edt_join_pw_check.setText("");
                            edt_join_pw.requestFocus();
                            return;
                        }else {
                            Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"회원가입 실패..",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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

                params.put("id",edt_join_id.getText().toString());
                params.put("pw",edt_join_pw.getText().toString());
                params.put("pw_check",edt_join_pw_check.getText().toString());
                params.put("name",edt_join_name.getText().toString());
                params.put("gender",rb_gender.getText().toString());
                params.put("age",edt_join_age.getText().toString());
                params.put("tel",edt_join_tel.getText().toString());

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