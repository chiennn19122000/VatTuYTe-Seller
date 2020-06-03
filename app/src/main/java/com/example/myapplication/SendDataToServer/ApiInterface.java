package com.example.myapplication.SendDataToServer;

import com.example.myapplication.DataLogin;
import com.example.myapplication.Seller.InfoSeller;

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

    @FormUrlEncoded
    @POST("updateinfoseller.php")
    Call<InfoSeller> updateinfo(@Field("username_seller") String username, @Field("name_seller") String name, @Field("sdt_seller") String sdt,@Field("email_seller") String email);

    @FormUrlEncoded
    @POST("updatepass.php")
    Call<DataLogin> updatepass(@Field("username") String user, @Field("password") String pass);
}
