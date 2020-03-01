package com.bawp.myapplication.Classes;


import com.google.gson.annotations.SerializedName;

public class NotReceivedGoods {
    @SerializedName("id")
    private String id;


    @SerializedName("Items")
    private String items;


    @SerializedName("status")
    private String status;

    @SerializedName("order_date")
    private String order_date;

    @SerializedName("order_created_by")
    private String order_created_by;

    @SerializedName("price")
    private String price;
    @SerializedName("quantity")
    public String quantity;


    public NotReceivedGoods(String id, String items,String status,String order_date,String order_created_by,String price) {
        this.setId(id);
        this.setItems(items);
        this.setStatus(status);
        this.setOrder_date(order_date);
        this.setOrder_created_by(order_created_by);
        this.setPrice(price);



    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_created_by() {
        return order_created_by;
    }

    public void setOrder_created_by(String order_created_by) {
        this.order_created_by = order_created_by;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
