package com.example.login;

public class House {
    private String type;
    private String price;
    private String address;

    public House(String type, String price, String address) {
        this.type = type;
        this.price = price;
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
