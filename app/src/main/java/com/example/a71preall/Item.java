package com.example.a71preall;

public class Item {

    private int id;
    private String title;
    private String description;
    private String date;
    private String location;
    private Boolean islost;



    public Item(int id, String title, String description, String date, String location, Boolean islost) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.location = location;
        this.islost = islost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", islost=" + islost +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getIslost() {
        return islost;
    }

    public void setIslost(Boolean islost) {
        this.islost = islost;
    }
}
