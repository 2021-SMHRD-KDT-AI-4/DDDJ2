package com.example.projectvegan;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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

public class Login extends AppCompatActivity {
    private TextView tv_join;
    private EditText edt_login_id,edt_login_pw;
    private Button btn_login;
    private CheckBox cb_login;

    private RequestQueue queue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tv_join = findViewById(R.id.tv_join);
        edt_login_id = findViewById(R.id.edt_login_id);
        edt_login_pw = findViewById(R.id.edt_login_pw);
        cb_login = findViewById(R.id.cb_login);

        btn_login = findViewById(R.id.btn_login);

        String cb_login_id = PreferenceManager.getString(getApplicationContext(),"cb_id");
        String cb_login_pw = PreferenceManager.getString(getApplicationContext(),"cb_pw");

        if(!cb_login_id.equals(PreferenceManager.DEFAULT_STRING)){
            edt_login_id.setText(cb_login_id);
            edt_login_pw.setText(cb_login_pw);
            cb_login.setChecked(true);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("로그인");

        actionBar.setDisplayHomeAsUpEnabled(true);

        SpannableString content = new SpannableString(tv_join.getText().toString());
        content.setSpan(new UnderlineSpan(),0,content.length(),0);
        tv_join.setText(content);

        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Join.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
                String login_id = edt_login_id.getText().toString();
                String login_pw = edt_login_pw.getText().toString();

                String info =PreferenceManager.getString(getApplicationContext(),"info");

                try {
                    JSONObject jsonObject = new JSONObject(info);
                    String join_id = jsonObject.getString("id");
                    String join_pw = jsonObject.getString("pw");

                    if(login_id.equals(join_id) && login_pw.equals(join_pw)){

                        if(cb_login.isChecked()) {
                            PreferenceManager.setString(getApplicationContext(), "cb_id", login_id);
                            PreferenceManager.setString(getApplicationContext(), "cb_pw", login_pw);
                        }else{
                            PreferenceManager.remove(getApplicationContext(),"cb_id");
                            PreferenceManager.remove(getApplicationContext(),"cb_pw");
                        }
                        Intent intent = new Intent(getApplicationContext(),Main.class);
                        startActivity(intent);
                    }
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                Intent intent = new Intent(getApplicationContext(),Main.class);
                startActivity(intent);
            }
        });
    }
    public void sendRequest(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://59.0.236.151:8081/AndroidServer/LoginService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String id = jsonObject.getString("id");
                        Intent intent = new Intent(getApplicationContext(),Main.class);
                        intent.putExtra("id",id);
                        startActivity(intent);

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

                params.put("id",edt_login_id.getText().toString());
                params.put("pw",edt_login_pw.getText().toString());

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