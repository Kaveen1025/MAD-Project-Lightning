package com.example.mad_project_design_phase;

public class CustomerAddress {

    private String Province;
    private String City;
    private String Address;


    public CustomerAddress(String province, String city, String address) {
        Province = province;
        City = city;
        Address = address;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
