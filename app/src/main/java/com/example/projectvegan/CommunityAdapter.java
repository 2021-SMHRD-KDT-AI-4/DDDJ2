package com.example.projectvegan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projectvegan.DTO.SNSDTO;
import com.google.api.client.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    ArrayList<SNSDTO> communityItemArrayList;
    Context context;


    public CommunityAdapter(ArrayList<SNSDTO> communityItemArrayList, Context context) {
        this.communityItemArrayList = communityItemArrayList;
        this.context = context;
    }

    @Override
    public CommunityAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.community_item,null);
        CommunityAdapter.ViewHolder viewHolder=new CommunityAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommunityAdapter.ViewHolder holder, final int position) {

        Bitmap myBitmap = StringToBitmap(communityItemArrayList.get(position).getSns_img());

        holder.userName.setText(communityItemArrayList.get(position).getUser_name());
        holder.userEmail.setText(communityItemArrayList.get(position).getUser_id());
        holder.comTitle.setText(communityItemArrayList.get(position).getSns_title());
        holder.comImg.setImageBitmap(myBitmap);
        holder.userText.setText(communityItemArrayList.get(position).getSns_content());
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decodeBase64(encodedString);// String 화 된 이미지를  base64방식으로 인코딩하여 byte배열을 만듬
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);//byte배열을 bitmapfactory 메소드를 이용하여 비트맵으로 바꿔준다.
            return bitmap;//만들어진 bitmap을 return
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


    @Override
    public int getItemCount() {
        return communityItemArrayList.size();
    }

    public void  filterList(ArrayList<SNSDTO> filteredList) {
        communityItemArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName, userEmail, comTitle, userText;
        ImageView comImg;

        public ViewHolder(View itemView) {
            super(itemView);

            this.userName = itemView.findViewById(R.id.user_name);
            this.userEmail = itemView.findViewById(R.id.user_email);
            this.comTitle = itemView.findViewById(R.id.com_title);
            this.comImg = itemView.findViewById(R.id.com_img);
            this.userText = itemView.findViewById(R.id.user_text);

        }
    }

}
