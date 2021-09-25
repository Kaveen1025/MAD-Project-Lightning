package com.example.mad_project_design_phase;

public class RestaurantModel {
    private String name;
    private String price;
    private String foodImage;

    public RestaurantModel() {
    }

    public RestaurantModel(String name, String price, String foodImage) {
        this.name = name;
        this.price = price;
        this.foodImage = foodImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
