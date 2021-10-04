package com.example.mad_project_design_phase;

public class FoodReviews {
     private String NoOfStars;
     private String Review;
     private String name;
     private String userImage;

    public FoodReviews() {
    }

    public FoodReviews(String noOfStars, String review, String name, String userImage) {
        NoOfStars = noOfStars;
        Review = review;
        this.name = name;
        this.userImage = userImage;
    }

    public String getNoOfStars() {
        return NoOfStars;
    }

    public void setNoOfStars(String noOfStars) {
        NoOfStars = noOfStars;
    }

    public String getReview() {
        return Review;
    }

    public void setReview(String review) {
        Review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
