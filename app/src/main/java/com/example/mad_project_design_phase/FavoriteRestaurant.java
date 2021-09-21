package com.example.mad_project_design_phase;

public class FavoriteRestaurant {

    private String Address;
    private String CuisineType;
    private String Name;

    public FavoriteRestaurant() {
    }

    public FavoriteRestaurant(String address, String cuisineType, String name) {
        Address = address;
        CuisineType = cuisineType;
        Name = name;
    }

    public String getAddresss() {
        return Address;
    }

    public void setAddresss(String addresss) {
        Address = addresss;
    }

    public String getCuisineTypes() {
        return CuisineType;
    }

    public void setCuisineTypes(String cuisineTypee) {
        CuisineType = cuisineTypee;
    }

    public String getNames() {
        return Name;
    }

    public void setNames(String namee) {
        Name = namee;
    }
}
