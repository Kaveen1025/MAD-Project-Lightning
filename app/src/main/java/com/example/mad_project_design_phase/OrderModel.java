package com.example.mad_project_design_phase;

public class OrderModel {

    private String restImage;
    private String restaurantName;
    private String totalPrice;
    private  String mainFood;

    public OrderModel() {
    }

    public OrderModel(String restImage, String restaurantName, String totalPrice,String mainFood) {
        this.restImage = restImage;
        this.restaurantName = restaurantName;
        this.totalPrice = totalPrice;
        this.mainFood = mainFood;
    }

    public String getMainFood() {
        return mainFood;
    }

    public void setMainFood(String mainFood) {
        this.mainFood = mainFood;
    }

    public String getRestImage() {
        return restImage;
    }

    public void setRestImage(String restImage) {
        this.restImage = restImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }


}
