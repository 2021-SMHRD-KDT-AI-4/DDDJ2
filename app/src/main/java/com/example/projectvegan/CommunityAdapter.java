package com.example.projectvegan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.ViewHolder> {
    ArrayList<CommunityItem> communityItemArrayList;
    Context context;


    public CommunityAdapter(ArrayList<CommunityItem> communityItemArrayList, Context context) {
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

        holder.userIcon.setImageResource(communityItemArrayList.get(position).getUserIcon());
        holder.userName.setText(communityItemArrayList.get(position).getUserName());
        holder.userEmail.setText(communityItemArrayList.get(position).getUserEmail());
        holder.comTitle.setText(communityItemArrayList.get(position).getComTitle());
        holder.comImg.setImageResource(communityItemArrayList.get(position).getComImg());

    }

    @Override
    public int getItemCount() {
        return communityItemArrayList.size();
    }

    public void  filterList(ArrayList<CommunityItem> filteredList) {
        communityItemArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName, userEmail, comTitle;
        ImageView userIcon, comImg;

        public ViewHolder(View itemView) {
            super(itemView);

            this.userName = itemView.findViewById(R.id.user_name);
            this.userEmail = itemView.findViewById(R.id.user_email);
            this.comTitle = itemView.findViewById(R.id.com_title);
            this.userIcon = itemView.findViewById(R.id.user_icon);
            this.comImg = itemView.findViewById(R.id.com_img);
        }
    }
}
