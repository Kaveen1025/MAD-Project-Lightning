package com.example.mad_project_design_phase;

public class SelectCardModel {
    private String cardtype;
    private String cardnumber;


    public SelectCardModel() {
    }

    public SelectCardModel(String cardtype, String cardnumber, String cardcvc) {
        this.cardtype = cardtype;
        this.cardnumber = cardnumber;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

}
