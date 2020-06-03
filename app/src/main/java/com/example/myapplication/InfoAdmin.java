package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InfoAdmin implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("sdt")
    private String sdt;

    @SerializedName("email")
    private String email;

    public InfoAdmin(String name, String sdt, String email) {
        this.name = name;
        this.sdt = sdt;
        this.email = email;
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
}
