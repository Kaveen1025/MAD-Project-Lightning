package com.example.mad_project_design_phase;

public class restRatingModel {
    private String noOfStars;
    private String review;
    private String name;
    private String userImage;

    public restRatingModel() {
    }

    public restRatingModel(String noOfStars, String review, String name, String userImage) {
        this.noOfStars = noOfStars;
        this.review = review;
        this.name = name;
        this.userImage = userImage;
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
