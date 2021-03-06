package com.example.mad_project_design_phase;

public class CustomerRestaurantReviews {
    private  String noOfStars;
    private String review;
    private String name;
    private String customerProfile;

    public CustomerRestaurantReviews() {
    }

    public CustomerRestaurantReviews(String noOfStars, String review,String name,String customerProfile) {
        this.noOfStars = noOfStars;
        this.review = review;
    }

    public String getNoOfStars() {
        return noOfStars;
    }

    public String getName(){
        return name;
    }

    public String getCustomerProfile(){
        return customerProfile;
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
