package com.example.mad_project_design_phase;

public class HAddDetails {
    private String CdType;
    private String CdNumber;
    private String CdHolder;
    private String CdDate;
    private String CdCVC;

    public HAddDetails() {
    }

    public HAddDetails(String cdType, String cdNumber, String cdHolder, String cdDate, String cdCVC) {
        CdType = cdType;
        CdNumber = cdNumber;
        CdHolder = cdHolder;
        CdDate = cdDate;
        CdCVC = cdCVC;
    }

    public String getCdType() {
        return CdType;
    }

    public String getCdNumber() {
        return CdNumber;
    }

    public String getCdHolder() {
        return CdHolder;
    }

    public String getCdDate() {
        return CdDate;
    }

    public String getCdCVC() {
        return CdCVC;
    }

    public void setCdType(String cdType) {
        CdType = cdType;
    }

    public void setCdNumber(String cdNumber) {
        CdNumber = cdNumber;
    }

    public void setCdHolder(String cdHolder) {
        CdHolder = cdHolder;
    }

    public void setCdDate(String cdDate) {
        CdDate = cdDate;
    }

    public void setCdCVC(String cdCVC) {
        CdCVC = cdCVC;
    }
}
