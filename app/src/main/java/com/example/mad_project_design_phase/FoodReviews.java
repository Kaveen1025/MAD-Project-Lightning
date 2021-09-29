package com.example.mad_project_design_phase;

public class FoodReviews {
     private String NoOfStars;
     private String Review;

    public FoodReviews() {

    }

    public FoodReviews(String noOfStars, String review) {
        NoOfStars = noOfStars;
        Review = review;
    }

    public String getNoOfStars() {
        return NoOfStars;
    }

    public String getReview() {
        return Review;
    }

    public void setNoOfStars(String noOfStars) {
        NoOfStars = noOfStars;
    }

    public void setReview(String review) {
        Review = review;
    }
}
