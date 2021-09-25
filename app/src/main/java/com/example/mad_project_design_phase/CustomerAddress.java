package com.example.mad_project_design_phase;

public class CustomerAddress {

    private String province;
    private String city;
    private String address;

    CustomerAddress(){}

    public CustomerAddress(String province, String city, String address) {
        this.province = province;
        this.city = city;
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
