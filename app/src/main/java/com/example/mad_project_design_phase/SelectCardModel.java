package com.example.mad_project_design_phase;

public class SelectCardModel {
    private String cardType;
    private String cardNumber;


    public SelectCardModel() {
    }

    public SelectCardModel(String cardType, String cardNumber) {
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
