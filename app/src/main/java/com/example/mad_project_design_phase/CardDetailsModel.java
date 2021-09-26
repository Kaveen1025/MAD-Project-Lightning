package com.example.mad_project_design_phase;

public class CardDetailsModel {
    private String cardBank;
    private String cardType;
    private String cardHolder;
    private String cardDate;
    private String cardNumber;

    public CardDetailsModel() {
    }

    public CardDetailsModel(String cardBank, String cardType, String cardHolder, String cardDate, String cardNumber) {
        this.cardBank = cardBank;
        this.cardType = cardType;
        this.cardHolder = cardHolder;
        this.cardDate = cardDate;
        this.cardNumber = cardNumber;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
