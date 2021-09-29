package com.example.mad_project_design_phase;

public class FavouriteModel {

    String name, calories, price, foodImage;

    public FavouriteModel() {}

    public FavouriteModel(String name, String calories, String price, String foodImage) {
        this.name = name;
        this.calories = calories;
        this.price = price;
        this.foodImage = foodImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }
}
