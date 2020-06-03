package com.example.myapplication.GetData;

import com.example.myapplication.InfoAdmin;
import com.example.myapplication.InformationOrder;
import com.example.myapplication.Product.Product;
import com.example.myapplication.Seller.InfoSeller;
import com.example.myapplication.Seller.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("getdata.php")
    Call<List<Product>> getAllProduct();

    @GET("getseller.php")
    Call<List<Seller>> getAllSeller();

    @GET("getallorder.php")
    Call<List<InformationOrder>> getAllOrder();

    @GET("getpayment.php")
    Call<List<InformationOrder>> getAllPayment();

    @GET("getinfoseller.php")
    Call<List<InfoSeller>> getInfoSeller();

    @GET("getinfoadmin.php")
    Call<List<InfoAdmin>> getInfoAdmin();
}
