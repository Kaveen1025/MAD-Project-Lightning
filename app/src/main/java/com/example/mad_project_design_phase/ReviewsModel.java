package com.example.mad_project_design_phase;

public class ReviewsModel {
     String description, foodImage, name;

    public ReviewsModel(){}

    public ReviewsModel(String description, String foodImage, String name) {
        this.description = description;
        this.foodImage = foodImage;
        this.name = name;
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
}
