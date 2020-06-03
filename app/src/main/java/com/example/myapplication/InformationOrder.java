package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InformationOrder implements Serializable {
    @SerializedName("OrderId")
    private int OrderId;

    @SerializedName("ProductId")
    private int ProductId;

    @SerializedName("Username")
    private String Username;

    @SerializedName("Quantity")
    private int Quantity;

    @SerializedName("Pay")
    private int Pay;

    @SerializedName("Place")
    private String Place;

    @SerializedName("NameProduct")
    private String NameProduct;

    @SerializedName("PriceProduct")
    private int PriceProduct;

    @SerializedName("InformationProduct")
    private String InformationProduct;

    @SerializedName("ImageProduct")
    private String ImageProduct;

    @SerializedName("name")
    private String name;

    @SerializedName("sdt")
    private String sdt;

    public InformationOrder(int orderId, int productId, String username, int quantity, int pay, String place, String nameProduct, int priceProduct, String informationProduct, String imageProduct, String name, String sdt) {
        OrderId = orderId;
        ProductId = productId;
        Username = username;
        Quantity = quantity;
        Pay = pay;
        Place = place;
        NameProduct = nameProduct;
        PriceProduct = priceProduct;
        InformationProduct = informationProduct;
        ImageProduct = imageProduct;
        this.name = name;
        this.sdt = sdt;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getPay() {
        return Pay;
    }

    public void setPay(int pay) {
        Pay = pay;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public int getPriceProduct() {
        return PriceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        PriceProduct = priceProduct;
    }

    public String getInformationProduct() {
        return InformationProduct;
    }

    public void setInformationProduct(String informationProduct) {
        InformationProduct = informationProduct;
    }

    public String getImageProduct() {
        return ImageProduct;
    }

    public void setImageProduct(String imageProduct) {
        ImageProduct = imageProduct;
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

    @Override
    public String toString() {
        return "Mã đơn hàng: " + getOrderId() + "\n" +
                "Tên khách hàng: " + getName() + "   " +"SĐT: " + getSdt() + "\n" +
                "Tên SP: " + getNameProduct() + " x " + getQuantity() + "\n" ;
    }
    public String toStringAll() {
        return "Mã đơn hàng: " + getOrderId() + "   Đã thanh toán: " + getPay() + " VNĐ\n" +
                "Tên khách hàng: " + getName() + "   " + "SĐT: " + getSdt() + "\n" +
                "Tên SP: " + getNameProduct() + " x " + getQuantity() + "\n";
    }
}