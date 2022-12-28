package com.example.rentit.productsData;

public class UserProductTrack {

    String head;
    String name ;
    String rating ;
    String description;
    String type ;
    String image;
    String address;

    public UserProductTrack()
    {

    }

    public UserProductTrack(String head, String name, String rating, String description, String type, String image, String address) {
        this.head = head;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.type = type;
        this.image = image;
        this.address = address;
    }

    public String getHead() {
        return head;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
