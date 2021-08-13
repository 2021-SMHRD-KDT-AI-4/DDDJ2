package com.example.projectvegan;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    ArrayList<RankItem> rankItemArrayList;
    Context context;


    public RankAdapter(ArrayList<RankItem> rankItemArrayList, Context context) {
        this.rankItemArrayList = rankItemArrayList;
        this.context = context;
    }

    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rank_profile,null);
        RankAdapter.ViewHolder viewHolder=new RankAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RankAdapter.ViewHolder holder, final int position) {

        holder.rankNum.setText(rankItemArrayList.get(position).getNum());
        holder.rankNick.setText(rankItemArrayList.get(position).getName());
        holder.rankPoint.setText(rankItemArrayList.get(position).getPoint());
    }

    @Override
    public int getItemCount() {
        return rankItemArrayList.size();
    }

    public void  filterList(ArrayList<RankItem> filteredList) {
        rankItemArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView rankNum,rankNick,rankPoint;

        public ViewHolder(View itemView) {
            super(itemView);

            this.rankNum=itemView.findViewById(R.id.rank_num);
            this.rankNick=itemView.findViewById(R.id.rank_nick);
            this.rankPoint=itemView.findViewById(R.id.rank_point);

        }
    }
}
