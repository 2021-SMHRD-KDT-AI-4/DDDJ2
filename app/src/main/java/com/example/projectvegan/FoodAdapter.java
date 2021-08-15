package com.example.projectvegan;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodItem> foodItemArrayList;
    Activity activity;

    // Server에게 요청하는 객체
    private RequestQueue queue;
    // 요청할때 보낼 데이터 전송방식, 보내는 데이터, 받아올 데이터 타입 정의 등 설정하는 문자열
    private StringRequest stringRequest;
    public FoodAdapter(ArrayList<FoodItem> foodItemArrayList, Activity activity) {
        this.foodItemArrayList = foodItemArrayList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.foodName.setText(foodItemArrayList.get(position).getFoodName());
        holder.foodCom.setText("["+foodItemArrayList.get(position).getFoodCom()+ "]");
        holder.foodKcal.setText(foodItemArrayList.get(position).getFoodKcal() + "kcal");
    }

    @Override
    public int getItemCount() {
        return foodItemArrayList.size();
    }

    public void filterList(ArrayList<FoodItem> filteredList) {
        foodItemArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView foodName, foodCom, foodKcal;

        public ViewHolder(View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodCom = itemView.findViewById(R.id.foodCom);
            foodKcal = itemView.findViewById(R.id.foodKcal);
/*
            if (foodCheck.isChecked()) {
                String name = foodName.getText().toString();
                sendRequest(name);
            }*/



            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        sendRequest(foodItemArrayList.get(pos).getFoodName());
                    }
                }
            });
        }
    }
    public void sendRequest(String name) {

        // Voolley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(activity);
        String url = "http://211.63.240.58:8081/3rd_project/TotalService";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // Log.v("resultValue", jsonObject.getString("isCheck"));
                    float carbohydrate = Float.parseFloat(jsonObject.getString("total_carbohydrate"));
                    float protein = Float.parseFloat(jsonObject.getString("total_protein"));
                    float fat = Float.parseFloat(jsonObject.getString("total_fat"));
                    float natrum = Float.parseFloat(jsonObject.getString("total_natrum"));
                    float sugar = Float.parseFloat(jsonObject.getString("total_sugar"));
                    int kcal = jsonObject.getInt("total_calory");

                    Intent intent = new Intent(activity,MyPage.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Intent intent = new Intent(context, RecipeFoodInfo.class);
                    intent.putExtra("carbohydrate", carbohydrate);
                    intent.putExtra("protein", protein);
                    intent.putExtra("fat", fat);
                    intent.putExtra("natrum", natrum);
                    intent.putExtra("sugar", sugar);
                    intent.putExtra("kcal", kcal);

                    activity.startActivity(intent);
                    activity.finish();

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
        }) {
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

                String todayDate = PreferenceManager.getString(activity.getApplicationContext(), "date");

                params.put("user_id", PreferenceManager.getString(activity.getApplicationContext(), "id"));
                params.put("food_name",name);
                params.put("today_date", todayDate);
                params.put("time", PreferenceManager.getString(activity.getApplicationContext(), "time"));
                return params;
            }
        };
        queue.add(stringRequest);
    } // End sendRequest
}
