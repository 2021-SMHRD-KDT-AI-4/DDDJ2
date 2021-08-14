package com.example.projectvegan.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.example.projectvegan.Challenge;
import com.example.projectvegan.Community;
import com.example.projectvegan.DTO.SNSDTO;
import com.example.projectvegan.Main;
import com.example.projectvegan.PreferenceManager;
import com.example.projectvegan.Quiz;
import com.example.projectvegan.R;
import com.example.projectvegan.Rank;
import com.example.projectvegan.RankItem;
import com.example.projectvegan.Recipe;
import com.example.projectvegan.RecipeItem;
import com.example.projectvegan.Scanner;
import com.example.projectvegan.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HomeFragment extends Fragment {


    private RecipeItem recipeItem = null;
    private ArrayList<RecipeItem> recipeFoodItemArrayList;
    private FragmentHomeBinding binding;
    private RequestQueue queue;
    private StringRequest stringRequest;

    private ArrayList<RankItem> rankList;
    private ArrayList<SNSDTO> snsList;
    private ArrayList<RecipeItem> recipeList;

    private int[] quizs = {R.drawable.quizimg1,R.drawable.quizimg2,R.drawable.quizimg3,R.drawable.quiz_img};
    private String[] title = {"맥주는 비건 음식일까요","채식을 하는 것이 환경에 도움이 될까요?","소고기 1Kg 생산하는데 배출되는 이산화탄소 양은?",
                            "한국 오레오는 비건 음식일까요?"};

    Random r = new Random();

    private String user_cat = null;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button btn_chal = binding.btnChal;
        final Button btn_scanner = binding.btnScanner;
        final Button btn_rank = binding.btnRank;
        final Button btn_sns = binding.btnSns;
        final TextView tv_main_quiz =  binding.tvMainQuiz;
        final TextView tv_main_recipe = binding.tvMainRecipe;
        final TextView tv_main_quiz_title = binding.tvMainQuizTitle;
        final ImageView img_main_recipe = binding.imgMainRecipe;
        final ImageView img_main_quiz = binding.imgMainQuiz;

        user_cat = PreferenceManager.getString(getContext(), "category");

        int random = r.nextInt(4);

        img_main_quiz.setImageResource(quizs[random]);
        tv_main_quiz_title.setText(title[random]);

        tv_main_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Recipe.class);
                startActivity(intent);
            }
        });

        img_main_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeSendRequest();
            }
        });

        tv_main_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Quiz.class);
                intent.putExtra("ranNum",random);
                startActivity(intent);
            }
        });

        img_main_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Quiz.class);
                intent.putExtra("ranNum",random);
                startActivity(intent);
            }
        });

        btn_chal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Challenge.class);
                startActivity(intent);
            }
        });
        btn_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Scanner.class);
                startActivity(intent);
            }
        });
        btn_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rankSendRequest();
            }
        });
        btn_sns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SNSsendRequest();
            }
        });
        return root;
    }
    public void rankSendRequest(){

        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://211.63.240.58:8081/3rd_project/RankService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        // JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i< jsonArray.length(); i++) {
                            String user_id = jsonArray.getJSONObject(i).getString("user_id");
                            String user_name = jsonArray.getJSONObject(i).getString("user_name");
                            int user_point = jsonArray.getJSONObject(i).getInt("user_point");

                            RankItem rankItem = new RankItem(user_id,user_name,user_point);
                            rankList.add(rankItem);
                        }

                        Intent intent = new Intent(getContext(),Rank.class);
                        intent.putExtra("rankList", rankList);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "오류!", Toast.LENGTH_SHORT).show();
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



    public void recipeSendRequest(){

        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://211.63.240.58:8081/3rd_project/SearchService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        // JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i< jsonArray.length(); i++) {
                            String recipe_title = jsonArray.getJSONObject(i).getString("recipe_title");
                            String recipe_content = jsonArray.getJSONObject(i).getString("recipe_content");
                            String recipe_ingredient = jsonArray.getJSONObject(i).getString("recipe_ingredient");

                            recipeItem = new RecipeItem(recipe_title,recipe_content,recipe_ingredient);
                            recipeList.add(recipeItem);
                        }

                        Intent intent = new Intent(getContext(),Recipe.class);
                        intent.putExtra("recipeList", recipeList);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "오류!", Toast.LENGTH_SHORT).show();
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

                params.put("usercat", user_cat);
                return params;
            }
        };
        queue.add(stringRequest);
    }


    public void SNSsendRequest(){

        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://211.63.240.58:8081/3rd_project/ViewService";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                if(!response.equals("null")){
                    try {
                        // JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i = 0; i< jsonArray.length(); i++) {
                            int sns_code = jsonArray.getJSONObject(i).getInt("sns_code");
                            String user_id = jsonArray.getJSONObject(i).getString("user_id");
                            String sns_title = jsonArray.getJSONObject(i).getString("sns_title");
                            String sns_content = jsonArray.getJSONObject(i).getString("sns_content");
                            String sns_img = jsonArray.getJSONObject(i).getString("sns_img");
                            String user_name = jsonArray.getJSONObject(i).getString("user_name");

                            SNSDTO snsdto = new SNSDTO(sns_code,user_id,sns_title,sns_content, sns_img, user_name);
                            snsList.add(snsdto);
                        }

                        Intent intent = new Intent(getContext(),Community.class);
                        intent.putExtra("snsList", snsList);
                        startActivity(intent);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "오류!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}