package com.example.myapplication.Seller;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InfoSeller implements Serializable {
    @SerializedName("username")
    private String username;

    @SerializedName("name")
    private String name;

    @SerializedName("sdt")
    private String sdt;

    @SerializedName("email")
    private String email;

    @SerializedName("place")
    private String place;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }

    public InfoSeller(String username, String name, String sdt, String email, String place) {
        this.username = username;
        this.name = name;
        this.sdt = sdt;
        this.email = email;
        this.place = place;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
