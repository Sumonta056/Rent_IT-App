package com.example.rentit.productsData;

public class Free {
    String name ;
    String description;
    String rating ;
    String address;
    String image;

    public Free()
    {

    }

    public Free(String name, String description, String rating, String address, String image) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }
}
