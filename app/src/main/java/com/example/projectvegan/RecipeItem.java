package com.example.projectvegan;

import java.io.Serializable;

public class RecipeItem implements Serializable {
    String recipeFoodName;
    String recipeFoodRc;
    String recipeFoodIngredient;

    public RecipeItem(String recipeFoodName) {
        this.recipeFoodName = recipeFoodName;
    }

    public RecipeItem(String recipeFoodName, String recipeFoodRc, String recipeFoodIngredient)
    {
        this.recipeFoodName = recipeFoodName;
        this.recipeFoodRc = recipeFoodRc;
        this.recipeFoodIngredient = recipeFoodIngredient;
    }

    public String getRecipeFoodName(){
        return recipeFoodName;
    }

    public void setRecipeFoodName(String recipeFoodName){
        this.recipeFoodName = recipeFoodName;
    }

    public String getRecipeFoodRc() {
        return recipeFoodRc;
    }

    public void setRecipeFoodRc(String recipeFoodRc) {
        this.recipeFoodRc = recipeFoodRc;
    }

    public String getRecipeFoodIngredient() {
        return recipeFoodIngredient;
    }

    public void setRecipeFoodIngredient(String recipeFoodIngredient) {
        this.recipeFoodIngredient = recipeFoodIngredient;
    }


}
