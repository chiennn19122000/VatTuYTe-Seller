package com.example.myapplication.GetProduct;

import com.example.myapplication.InformationOrder;
import com.example.myapplication.Product.Product;
import com.example.myapplication.Seller.Seller;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface APIService {

    @GET("getdata.php")
    Call<List<Product>> getAllProduct();

    @GET("getseller.php")
    Call<List<Seller>> getAllSeller();

    @GET("getallorder.php")
    Call<List<InformationOrder>> getAllOrder();
}
