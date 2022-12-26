package com.example.rentit.regisrationData;

public class UserModel {
    String name ;
    String email;
    String address;
    String password;
    String phone;

     public UserModel()
     {

     }

    public UserModel(String name, String email, String address, String password, String phone) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
