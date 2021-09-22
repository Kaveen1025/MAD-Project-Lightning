package com.example.mad_project_design_phase;

public class FavoriteRestaurant {

    private String Address;
    private String CuisineType;
    private String Name;
    private String restImage;

    public FavoriteRestaurant() {
    }

    public FavoriteRestaurant(String address, String cuisineType, String name, String restImage) {
        Address = address;
        CuisineType = cuisineType;
        Name = name;
        this.restImage = restImage;
    }

    public String getRestImage(){ return  restImage;};
    public void setRestImage(String restImage){this.restImage = restImage;};
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCuisineType() {
        return CuisineType;
    }

    public void setCuisineType(String cuisineType) {
        CuisineType = cuisineType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
