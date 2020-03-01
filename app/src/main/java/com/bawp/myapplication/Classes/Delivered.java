package com.bawp.myapplication.Classes;

import com.google.gson.annotations.SerializedName;

public class Delivered {
    @SerializedName("name")
    private String name;


    @SerializedName("phno")
    private String phno;


    @SerializedName("goods_name")
    private String goods_name;

    @SerializedName("quantity")
    private String quantity;

    public Delivered(String goods_name, String quantity, String name, String phno) {
        this.setGoods_name(goods_name);
        this.setQuantity(quantity);
        this.setName(name);
        this.setPhno(phno);



    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }
}
