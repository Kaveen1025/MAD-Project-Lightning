package com.example.mad_project_design_phase;

public class RestaurantReview {
    private  String Review;
    private String NoOfStars;
    private String name;
    private String userImage;

    public RestaurantReview() {
    }

    public RestaurantReview(String review, String noOfStars, String name, String userImage) {
        Review = review;
        NoOfStars = noOfStars;
        this.name = name;
        this.userImage = userImage;
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
