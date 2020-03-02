package com.inventorymanagementsystem.JavaClasses;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("id")
    private
    String id;

    @SerializedName("item_name")
    private
    String item_name;

    @SerializedName("price")
    private
    String price;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("item_img_url")
    private String item_img_url;



    public Items(String id, String item_name, String price, String quantity, String item_img_url) {

        this.setId(id);
        this.setItem_name(item_name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setItem_img_url(item_img_url);
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItem_img_url() {
        return item_img_url;
    }

    public void setItem_img_url(String item_img_url) {
        this.item_img_url = item_img_url;
    }
}


