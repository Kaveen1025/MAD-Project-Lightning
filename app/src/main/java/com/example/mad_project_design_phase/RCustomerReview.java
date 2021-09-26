package com.example.mad_project_design_phase;

public class RCustomerReview {

    private String noOfStars;
    private String review;
    private String name;
    private String logo;

    public RCustomerReview() {
    }

    public RCustomerReview(String noOfStars, String review, String name, String logo) {
        this.noOfStars = noOfStars;
        this.review = review;
        this.name = name;
        this.logo = logo;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
