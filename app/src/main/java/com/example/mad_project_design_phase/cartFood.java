package com.example.mad_project_design_phase;

public class cartFood {

    String description, foodImage, name, price;

    public cartFood() {
    }

    public cartFood(String description, String foodImage, String name, String price) {
        this.description = description;
        this.foodImage = foodImage;
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
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
}
