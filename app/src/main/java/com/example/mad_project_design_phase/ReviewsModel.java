package com.example.mad_project_design_phase;

public class ReviewsModel {
     String description, foodImage, name,restID;

    public ReviewsModel(){}

    public ReviewsModel(String description, String foodImage, String name,String restID) {
        this.description = description;
        this.foodImage = foodImage;
        this.name = name;
        this.restID = restID;
    }

    public String getRestID() {
        return restID;
    }

    public void setRestID(String restID) {
        this.restID = restID;
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
