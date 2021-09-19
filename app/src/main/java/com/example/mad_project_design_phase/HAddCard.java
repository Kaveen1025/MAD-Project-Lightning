package com.example.mad_project_design_phase;

public class HAddCard {
    private String CardType;
    private String CardNumber;
    private String CardHolder;
    private String CardDate;

    public HAddCard() {
    }

    public String getCardType() {
        return CardType;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public String getCardHolder() {
        return CardHolder;
    }

    public String getCardDate() {
        return CardDate;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public void setCardHolder(String cardHolder) {
        CardHolder = cardHolder;
    }

    public void setCardDate(String cardDate) {
        CardDate = cardDate;
    }
}
