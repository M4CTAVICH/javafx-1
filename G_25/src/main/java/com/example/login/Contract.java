package com.example.login;

public class Contract {
    private String address;
    private String client;
    private String date;
    private String status;
    private String availability;

    public Contract(String address, String client, String date, String status, String availability) {
        this.address = address;
        this.client = client;
        this.date = date;
        this.status = status;
        this.availability = availability;
    }
    public String getAddress() {
        return address;

    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client) {
        this.client = client;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;

    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;

    }
    public String getAvailability() {
        return availability;

    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }

}



