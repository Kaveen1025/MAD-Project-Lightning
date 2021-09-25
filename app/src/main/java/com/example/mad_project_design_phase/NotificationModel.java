package com.example.mad_project_design_phase;

public class NotificationModel {
    private String title;
    private String description;

    public NotificationModel() {
    }

    public NotificationModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
