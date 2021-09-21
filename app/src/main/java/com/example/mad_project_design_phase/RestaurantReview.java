package com.example.mad_project_design_phase;

public class RestaurantReview {
    private  String Review;
    private String NoOfStars;

    public RestaurantReview() {
    }

    public RestaurantReview(String review, String noOfStars) {
        Review = review;
        NoOfStars = noOfStars;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public String getNoOfStars() {
        return NoOfStars;
    }

    public void setNoOfStars(String noOfStars) {
        NoOfStars = noOfStars;
    }
}
