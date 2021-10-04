package com.example.mad_project_design_phase;

public class CustomerDetails {
    public static String CustomerID;
    public static String userImage = "https://images.unsplash.com/photo-1541855492-581f618f69a0?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=870&q=80";
    public static String names = "Sonal Uchiru";

    public static String getCustomerID(){
        return  CustomerID;
    }

    public static void setCustomerID(String CustomerIDs){
        CustomerID = CustomerIDs;
    }

    public static String getUserImage() {
        return userImage;
    }

    public static void setUserImage(String userImage) {
        CustomerDetails.userImage = userImage;
    }

    public static String getNames() {
        return names;
    }

    public static void setNames(String names) {
        CustomerDetails.names = names;
    }
}
