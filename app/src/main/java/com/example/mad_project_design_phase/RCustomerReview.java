package com.example.mad_project_design_phase;

public class RCustomerReview {

    private String noOfStars;
    private String review;
    private String restName;
    private String restaurantLogo;

    public RCustomerReview() {
    }

    public RCustomerReview(String noOfStars, String review, String restName, String restaurantLogo) {
        this.noOfStars = noOfStars;
        this.review = review;
        this.restName = restName;
        this.restaurantLogo = restaurantLogo;
    }

    public String getNoOfStars() {
        return noOfStars;
    }

    public void setNoOfStars(String noOfStars) {
        this.noOfStars = noOfStars;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public String getRestaurantLogo() {
        return restaurantLogo;
    }

    public void setRestaurantLogo(String restaurantLogo) {
        this.restaurantLogo = restaurantLogo;
    }
}
