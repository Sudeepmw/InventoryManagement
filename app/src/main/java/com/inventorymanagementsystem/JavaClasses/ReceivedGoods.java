package com.inventorymanagementsystem.JavaClasses;

import com.google.gson.annotations.SerializedName;

public class ReceivedGoods {

@SerializedName("id")
private String id;


@SerializedName("items")
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

@SerializedName("restaurant_name")
private String restaurant_name;





public ReceivedGoods(String id, String items, String status, String order_date, String order_created_by, String price, String restaurant_name) {
        this.setId(id);
        this.setItems(items);
        this.setStatus(status);
        this.setOrder_date(order_date);
        this.setOrder_created_by(order_created_by);
        this.setPrice(price);
        this.setRestaurant_name(restaurant_name);



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

        public String getRestaurant_name() {
                return restaurant_name;
        }

        public void setRestaurant_name(String restaurant_name) {
                this.restaurant_name = restaurant_name;
        }
}
/*
public class ReceivedGoods {
    @SerializedName("goods_name")
    private String goods_name;

    @SerializedName("quantity")
    private String quantity;

    @SerializedName("status")
    private String status;

    @SerializedName("deliver_date")
    private String deliver_date;



    public ReceivedGoods(String goods_name, String quantity, String status, String deliver_date) {
       this.setGoods_name(goods_name);
        this.setQuantity(quantity);
        this.setStatus(status);
        this.setDeliver_date(deliver_date);


    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliver_date() {
        return deliver_date;
    }

    public void setDeliver_date(String deliver_date) {
        this.deliver_date = deliver_date;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }
}
*/
