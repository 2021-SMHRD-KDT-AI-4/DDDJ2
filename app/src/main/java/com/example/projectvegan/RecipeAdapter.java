package com.example.projectvegan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private ArrayList<RecipeItem> recipeItemArrayList;
    private Context context;

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
                        Intent intent = new Intent(context,RecipeFoodInfo.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("title", recipeItemArrayList.get(pos).getRecipeFoodName());

                        intent.putExtra("recipe",recipeItemArrayList.get(pos).getRecipeFoodRc());
                        intent.putExtra("ingredient",recipeItemArrayList.get(pos).getRecipeFoodIngredient());

                        context.startActivity(intent);
                    }
                }
            });


        }
    }
}
