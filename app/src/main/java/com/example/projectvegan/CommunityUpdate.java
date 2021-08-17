package com.example.projectvegan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CommunityUpdate extends AppCompatActivity {
    private Button btn_com_cancel,btn_com_check;
    private EditText edt_com_title,edt_com_text;
    private ImageView imgView;
    Bitmap bitmap = null;
    // Server에게 요청하는 객체
    private RequestQueue queue;
    // 요청할때 보낼 데이터 전송방식, 보내는 데이터, 받아올 데이터 타입 정의 등 설정하는 문자열
    private StringRequest stringRequest;

    Uri uri = null;
    String resizeImg;

    String id ;
    String pw;
    int point = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_update);
        // 동적 퍼미션
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int permissionResult = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permissionResult == PackageManager.PERMISSION_DENIED) {
                String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 10);
            }
        } else {
            //cv.setVisibility(View.VISIBLE);
        }// End Permission

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("커뮤니티 등록");
        actionBar.setDisplayHomeAsUpEnabled(true);

        point =PreferenceManager.getInt(getApplicationContext(),"point");
        id = PreferenceManager.getString(getApplicationContext(), "id");
        pw = PreferenceManager.getString(getApplicationContext(), "pw");

        imgView = findViewById(R.id.imgView);
        btn_com_cancel = findViewById(R.id.btn_com_cancel);
        btn_com_check = findViewById(R.id.btn_com_check);

        edt_com_title = findViewById(R.id.edt_com_title);
        edt_com_text = findViewById(R.id.edt_com_text);

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                // 핸드폰 갤러리 접속
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 812);

//                Intent intent1 = new Intent(getApplicationContext(),Yolo.class);
//                intent1.putExtra("img",bitmap);
//                startActivityForResult(intent1,900);
            }
        });

        btn_com_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Community.class);
                startActivity(intent);
                finish();
            }
        });

        btn_com_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ImageView 비트맵 가져오기
                /*BitmapDrawable drawable = (BitmapDrawable) imgView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                bitmap = resize(getApplicationContext(), uri, 250);

                resizeImg = BitmapToBase64(bitmap);
                sendRequest();*/

                Intent intent = new Intent(getApplicationContext(),Community.class);
                intent.putExtra("title",edt_com_title.getText().toString());
                intent.putExtra("text",edt_com_text.getText().toString());
                intent.putExtra("img", R.drawable.vgimg3);
                sendPointRequest();
                startActivity(intent);
                finish();

            }
        });
    } // End onCreate

    // 이미지 서버 저장
    public void sendRequest() {

        // Voolley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://211.63.240.58:8081/3rd_project/UploadService";
        // String url = "http://211.223.37.159:8081/3rd_project_server/ImgServer";
        // StringRequest(전송 방식, 주소, 응답받은 데이터)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // Log.v("resultValue", jsonObject.getString("isCheck"));
                    String result = jsonObject.getString("isCheck");
                    if (result.equals("true")) {
                        Intent intent = new Intent(getApplicationContext(), Community.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
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
            @Override //response를 UTF-8로 변경해주는 소스코드
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

                String name = PreferenceManager.getString(getApplicationContext(), "name");

                params.put("id", PreferenceManager.getString(getApplicationContext(), "id"));
                params.put("content", edt_com_text.getText().toString());
                params.put("title", edt_com_title.getText().toString());
                params.put("a", resizeImg);
                params.put("name", name);

                return params;
            }
        };
        queue.add(stringRequest);
    } // End sendRequest

    public void sendPointRequest(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://211.63.240.58:8081/3rd_project/PointService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Log.v("pointtest", String.valueOf(jsonObject.getInt("user_point")));
                        PreferenceManager.setInt(getApplicationContext(), "point", jsonObject.getInt("user_point"));

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
                params.put("pw",pw);

                return params;
            }
        };
        queue.add(stringRequest);
    }
    // 이미지 리사이즈
    private Bitmap resize(Context context, Uri uri, int resize){
        Bitmap resizeBitmap=null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); // 1번

            int width = options.outWidth;
            int height = options.outHeight;
            int samplesize = 1;

            while (true) {//2번
                if (width / 2 < resize || height / 2 < resize)
                    break;
                width /= 2;
                height /= 2;
                samplesize *= 2;
            }

            options.inSampleSize = samplesize;
            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); //3번
            resizeBitmap=bitmap;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resizeBitmap;
    } // End resize

    // Bitmap -> Base64 변환
    public String BitmapToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[]byteArray = byteArrayOutputStream.toByteArray();
        String temp = Base64.encodeToString(byteArray,Base64.DEFAULT);
        return temp;
    }

    // 퍼미션 응답
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 10 :
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED) //사용자가 허가 했다면
                {
                    Toast.makeText(this, "외부 메모리 읽기/쓰기 권한을 허용하였습니다.", Toast.LENGTH_SHORT).show();
                }else{//거부했다면
                    Toast.makeText(this, "외부 메모리 읽기/쓰기 권한을 제한하였습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    } // End Permission


    // OnClickEvent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 812 & resultCode == RESULT_OK) {
            try {
                uri = data.getData();
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                in.close();
                imgView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (resultCode == RESULT_CANCELED) {
            // 이미지 선택 취소했을 때
        }
    } // End OnActivityResult

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