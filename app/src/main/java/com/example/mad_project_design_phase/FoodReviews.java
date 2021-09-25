package com.example.mad_project_design_phase;

public class FoodReviews {

    private String noOfStars;
    private  String review;

    public FoodReviews() {
    }

    public FoodReviews(String noOfStars, String review) {
        this.noOfStars = noOfStars;
        this.review = review;
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
}
