package com.example.projectvegan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    ArrayList<FoodItem> foodItemArrayList;
    Activity activity;


    public FoodAdapter(ArrayList<FoodItem> foodItemArrayList, Activity activity) {
        this.foodItemArrayList = foodItemArrayList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_food,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.foodName.setText(foodItemArrayList.get(position).getFoodName());

    }

    @Override
    public int getItemCount() {
        return foodItemArrayList.size();
    }

    public void  filterList(ArrayList<FoodItem> filteredList) {
        foodItemArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView foodName;

        public ViewHolder(View itemView) {
            super(itemView);

            this.foodName=itemView.findViewById(R.id.foodName);

        }
    }
}
