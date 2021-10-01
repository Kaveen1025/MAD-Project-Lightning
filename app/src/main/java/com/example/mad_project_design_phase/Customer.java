package com.example.mad_project_design_phase;

public class Customer {


    private String FirstName;
    private String LastName;
    private String Email;
    private String Address;
    private String PhoneNumber;
    private String Password;
    private String pimage;

    public Customer(String firstName, String lastName, String email, String address, String phoneNumber, String password, String pimage) {
        FirstName = firstName;
        LastName = lastName;
        Email = email;
        Address = address;
        PhoneNumber = phoneNumber;
        Password = password;
        this.pimage = pimage;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }
}