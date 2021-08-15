package com.example.projectvegan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private ArrayList<RecipeItem> recipeItemArrayList;
    private Context context;


    // Server에게 요청하는 객체
    private RequestQueue queue;
    // 요청할때 보낼 데이터 전송방식, 보내는 데이터, 받아올 데이터 타입 정의 등 설정하는 문자열
    private StringRequest stringRequest;

    public RecipeAdapter(ArrayList<RecipeItem> recipeItemArrayList, Context context) {
        this.recipeItemArrayList = recipeItemArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipe,null);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.recipeFoodName.setText(recipeItemArrayList.get(position).getRecipeFoodName());

    }

    @Override
    public int getItemCount() {
        return recipeItemArrayList.size();
    }

    public void rFilterList(ArrayList<RecipeItem> rFilteredList) {
        recipeItemArrayList = rFilteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView recipeFoodName;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeFoodName=itemView.findViewById(R.id.recipeFoodName);


            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){

                        sendRequest(recipeItemArrayList.get(pos).getRecipeFoodName());


//                        Intent intent = new Intent(context,RecipeFoodInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.putExtra("title", );
//
//                        intent.putExtra("recipe",recipeItemArrayList.get(pos).getRecipeFoodRc());
//                        intent.putExtra("ingredient",recipeItemArrayList.get(pos).getRecipeFoodIngredient());
//
//                        context.startActivity(intent);
                    }
                }
            });


        }
    }
    public void sendRequest(String name) {

        // Voolley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(context);
        String url = "http://211.63.240.58:8081/3rd_project/RecipeInfoService";
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
                    String rName = jsonObject.getString("recipe_title");
                    String rCon = jsonObject.getString("recipe_content");
                    String rIn = jsonObject.getString("recipe_ingredient");

                    Intent intent = new Intent(context,RecipeFoodInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    // Intent intent = new Intent(context, RecipeFoodInfo.class);
                    intent.putExtra("rName",rName);
                    intent.putExtra("rCon",rCon);
                    intent.putExtra("rIn",rIn);
                    context.startActivity(intent);

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

                params.put("recipe_name", name);
                return params;
            }
        };
        queue.add(stringRequest);
    } // End sendRequest


}
