package com.bawp.myapplication.Classes;
import com.google.gson.annotations.SerializedName;

public class AddItems {

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



    public AddItems(String item_name, String price, String item_img_url, String quantity)
    {
        this.setItem_name(item_name);
        this.setPrice(price);
        this.setItem_img_url(item_img_url);
        this.setQuantity(quantity);
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

    public String getItem_img_url() {
        return item_img_url;
    }

    public void setItem_img_url(String item_img_url) {
        this.item_img_url = item_img_url;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
