package com.example.projectvegan;

public class RecipeItem {
    String recipeFoodName;

    public RecipeItem(String recipefoodName){
        this.recipeFoodName = recipefoodName;
    }
    public String getFoodName(){
        return recipeFoodName;
    }

    public void setFoodName(String foodName){
        this.recipeFoodName = recipeFoodName;
    }

}
