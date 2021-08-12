package com.example.projectvegan;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    ArrayList<RecipeItem> recipeItemArrayList;
    Activity activity;


    public RecipeAdapter(ArrayList<RecipeItem> recipeItemArrayList, Activity activity) {
        this.recipeItemArrayList = recipeItemArrayList;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipe,null);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.recipeFoodName.setText(recipeItemArrayList.get(position).getFoodName());

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

            this.recipeFoodName=itemView.findViewById(R.id.recipeFoodName);

        }
    }
}
