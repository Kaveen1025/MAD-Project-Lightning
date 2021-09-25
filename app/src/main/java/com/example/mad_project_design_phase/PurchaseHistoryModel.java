package com.example.mad_project_design_phase;

public class PurchaseHistoryModel {
    private String id;
    private String dateAndTime;
    private String price;

    public PurchaseHistoryModel() {
    }

    public PurchaseHistoryModel(String id, String dateAndTime, String price) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
