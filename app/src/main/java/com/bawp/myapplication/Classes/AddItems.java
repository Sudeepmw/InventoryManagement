package com.bawp.myapplication.Classes;

import com.google.gson.annotations.SerializedName;

public class AddItems {

    @SerializedName("item_name")
    private
    String item_name;

    @SerializedName("price")
    public
    String price;


    public AddItems(String item_name)
    {
        this.setItem_name(item_name);
    }


    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}
