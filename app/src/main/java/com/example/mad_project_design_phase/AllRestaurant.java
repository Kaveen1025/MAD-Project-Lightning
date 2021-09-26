package com.example.mad_project_design_phase;

public class AllRestaurant {

    private String name;
    private String address;
    private  String logo;
    private  String mainImage;
    private String cuisineType;

    public AllRestaurant() {
    }

    public AllRestaurant(String name, String address, String logo, String mainImage, String cuisineType) {
        this.name = name;
        this.address = address;
        this.logo = logo;
        this.mainImage = mainImage;
        this.cuisineType = cuisineType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
}
