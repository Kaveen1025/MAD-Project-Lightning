package com.example.mad_project_design_phase;

public class rate_Model {

    String name, noOfStars, review, userImage;

    rate_Model(){}

    public rate_Model(String name, String noOfStars, String review, String userImage) {
        this.name = name;
        this.noOfStars = noOfStars;
        this.review = review;
        this.userImage = userImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
