package com.example.rentit.models;

public class PopulerModel {

    String name;
    String description;
    String rating;
    String rentPrice;
    String type;
    String img_Url;

    public PopulerModel(String name, String description, String rating, String rentPrice, String type, String img_Url) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.rentPrice = rentPrice;
        this.type = type;
        this.img_Url = img_Url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_Url() {
        return img_Url;
    }

    public void setImg_Url(String img_Url) {
        this.img_Url = img_Url;
    }
}
