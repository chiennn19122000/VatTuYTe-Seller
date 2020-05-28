package com.example.myapplication.SendDataToServer;

import com.example.myapplication.DataLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<DataLogin> loginacc(@Field("username") String user, @Field("password") String pass);

    @FormUrlEncoded
    @POST("payment.php")
    Call<Payment> pay(@Field("OrderId") int order, @Field("username") String username, @Field("amount") Integer amount);
}
