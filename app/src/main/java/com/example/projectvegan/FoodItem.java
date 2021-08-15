package com.example.projectvegan;

import java.io.Serializable;

public class FoodItem implements Serializable {
    private String foodName;
    private String foodCom;
    private int foodKcal;
    private double carbohydrate,protein,fat,natrum,sugar;
    private int kcal;

    public FoodItem(String foodName, String foodCom, int foodKcal, double carbohydrate, double protein, double fat, double natrum, double sugar, int kcal) {
        this.foodName = foodName;
        this.foodCom = foodCom;
        this.foodKcal = foodKcal;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.natrum = natrum;
        this.sugar = sugar;
        this.kcal = kcal;
    }

    public FoodItem(String foodCom, String foodName, int foodKcal) {
        this.foodCom = foodCom;
        this.foodName = foodName;
        this.foodKcal = foodKcal;
    }

    public String getFoodCom() {
        return foodCom;
    }

    public void setFoodCom(String foodCom) {
        this.foodCom = foodCom;
    }

    public int getFoodKcal() {
        return foodKcal;
    }

    public void setFoodKcal(int foodKcal) {
        this.foodKcal = foodKcal;
    }


    public String getFoodName(){
        return foodName;
    }

    public void setFoodName(String foodName){
        this.foodName = foodName;
    }

    public double getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getNatrum() {
        return natrum;
    }

    public void setNatrum(double natrum) {
        this.natrum = natrum;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }
}
