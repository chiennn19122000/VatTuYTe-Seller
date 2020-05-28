package com.example.myapplication.SendDataToServer;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Payment implements Serializable {

    @SerializedName("OrderId")
    private int OrderId;

    @SerializedName("username")
    private String username;

    @SerializedName("amount")
    private int amount;

    @SerializedName("response")
    private String Response;

    public String getResponse() {
        return Response;
    }
}
