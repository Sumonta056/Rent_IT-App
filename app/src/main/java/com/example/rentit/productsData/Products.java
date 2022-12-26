package com.example.rentit.productsData;

public class Products {

    String name;
    String description;
    String rating;
    String price;
    String image;

    public Products()
    {

    }

    public Products(String name, String description, String rating, String price , String image) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.price = price;
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

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
